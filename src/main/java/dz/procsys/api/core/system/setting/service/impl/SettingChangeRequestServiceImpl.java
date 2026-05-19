/**
 *	@Author		: System
 *	@Name		: SettingChangeRequestServiceImpl
 *	@CreatedOn	: 05-19-2026
 *	@Type		: Class / Service Impl
 **/

package dz.procsys.api.core.system.setting.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.request.SettingChangeRequestCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingChangeRequestDecisionRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueCreateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeRequestResponse;
import dz.procsys.api.core.system.setting.mapper.SettingChangeRequestMapper;
import dz.procsys.api.core.system.setting.model.SettingChangeRequest;
import dz.procsys.api.core.system.setting.model.SettingChangeType;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.repository.SettingChangeRequestRepository;
import dz.procsys.api.core.system.setting.repository.SettingChangeTypeRepository;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.repository.SettingScopeTypeRepository;
import dz.procsys.api.core.system.setting.service.SettingChangeRequestService;
import dz.procsys.api.core.system.setting.service.SettingHistoryService;
import dz.procsys.api.core.system.setting.service.SettingValueService;

/**
 * Production implementation of {@link SettingChangeRequestService}.
 * Orchestrates the full approval workflow: submission, decision, value application, and audit.
 */
@Service
@Transactional(readOnly = true)
public class SettingChangeRequestServiceImpl implements SettingChangeRequestService {

    private static final Logger log = LoggerFactory.getLogger(SettingChangeRequestServiceImpl.class);
    private static final String STATUS_PENDING  = "PENDING";
    private static final String STATUS_APPROVED = "APPROVED";
    //private static final String STATUS_REJECTED = "REJECTED";

    private final SettingChangeRequestRepository repository;
    private final SettingDefinitionRepository definitionRepository;
    private final SettingChangeTypeRepository changeTypeRepository;
    private final SettingScopeTypeRepository scopeTypeRepository;
    private final SettingValueService settingValueService;
    private final SettingHistoryService historyService;
    private final SettingChangeRequestMapper mapper;

    public SettingChangeRequestServiceImpl(
            final SettingChangeRequestRepository repository,
            final SettingDefinitionRepository definitionRepository,
            final SettingChangeTypeRepository changeTypeRepository,
            final SettingScopeTypeRepository scopeTypeRepository,
            final SettingValueService settingValueService,
            final SettingHistoryService historyService,
            final SettingChangeRequestMapper mapper) {
        this.repository = repository;
        this.definitionRepository = definitionRepository;
        this.changeTypeRepository = changeTypeRepository;
        this.scopeTypeRepository = scopeTypeRepository;
        this.settingValueService = settingValueService;
        this.historyService = historyService;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingChangeRequestResponse submit(final SettingChangeRequestCreateRequest request, final String requester) {
        log.info("[SettingChangeRequestService] Submitting change request by '{}' for definition ID: {}",
            requester, request.getSettingDefinitionId());
        final SettingDefinition definition = definitionRepository.findById(request.getSettingDefinitionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with ID: " + request.getSettingDefinitionId()));
        if (Boolean.FALSE.equals(definition.getRequiresApproval())) {
            throw new IllegalStateException(
                "SettingDefinition '" + definition.getCode() + "' does not require approval. Use direct update.");
        }
        final long pendingCount = repository.countBySettingDefinitionIdAndStatus(definition.getId(), STATUS_PENDING);
        if (pendingCount > 0) {
            throw new IllegalStateException(
                "A pending change request already exists for definition '" + definition.getCode() + "'.");
        }
        final SettingChangeType changeType = changeTypeRepository.findById(request.getChangeTypeId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingChangeType not found with ID: " + request.getChangeTypeId()));
        final SettingChangeRequest entity = mapper.toEntity(request);
        entity.setSettingDefinition(definition);
        entity.setChangeType(changeType);
        entity.setStatus(STATUS_PENDING);
        entity.setCreatedBy(requester);
        final SettingChangeRequest saved = repository.save(entity);
        log.info("[SettingChangeRequestService] Change request submitted with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingChangeRequestResponse decide(final Long id,
            final SettingChangeRequestDecisionRequest decision, final String approver) {
        log.info("[SettingChangeRequestService] Decision '{}' by '{}' on request Long: {}",
            decision.getDecision(), approver, id);
        final SettingChangeRequest entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeRequest not found with Id: " + id));
        if (!STATUS_PENDING.equals(entity.getStatus())) {
            throw new IllegalStateException(
                "Change request Long '" + id + "' is not in PENDING status. Current: " + entity.getStatus());
        }
        entity.setApproverRemarks(decision.getApproverRemarks());
        entity.setDecidedAt(new Date());
        entity.setDecidedBy(approver);
        entity.setStatus(decision.getDecision());
        if (STATUS_APPROVED.equals(decision.getDecision())) {
            applyApprovedChange(entity, approver);
        }
        final SettingChangeRequest saved = repository.save(entity);
        log.info("[SettingChangeRequestService] Change request Long: {} decided as '{}'", id, decision.getDecision());
        return mapper.toResponse(saved);
    }

    /**
     * Applies the approved change by delegating to SettingValueService and recording history.
     *
     * @param request  the approved change request entity
     * @param approver the username of the approver
     */
    private void applyApprovedChange(final SettingChangeRequest request, final String approver) {
        final SettingDefinition definition = request.getSettingDefinition();
        log.info("[SettingChangeRequestService] Applying approved change for definition '{}', scope: '{}'",
            definition.getCode(), request.getScopeReference());
        final Long scopeTypeId = scopeTypeRepository
            .findByCode(request.getScopeReference().contains(":")
                ? request.getScopeReference().split(":")[0]
                : request.getScopeReference())
            .map(st -> st.getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Cannot resolve scope type from reference: " + request.getScopeReference()));
        final Long scopeRefId = request.getScopeReference().contains(":")
            ? Long.parseLong(request.getScopeReference().split(":")[1])
            : null;
        final SettingValueCreateRequest valueRequest = SettingValueCreateRequest.builder()
            .settingDefinitionId(definition.getId())
            .scopeTypeId(scopeTypeId)
            .scopeReferenceId(scopeRefId)
            .value(request.getProposedValue())
            .build();
        settingValueService.create(valueRequest);
        historyService.record(definition, request.getChangeType(),
            request.getScopeReference(), null, request.getProposedValue(),
            "Approved change request: " + request.getJustification(), approver);
    }

    /** {@inheritDoc} */
    @Override
    public SettingChangeRequestResponse findById(final Long id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeRequest not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingChangeRequestResponse> findByStatus(final String status, final Pageable pageable) {
        return repository.findByStatusOrderByCreatedAtDesc(status, pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingChangeRequestResponse> findByDefinition(final Long definitionUuid, final Pageable pageable) {
        final SettingDefinition definition = definitionRepository.findById(definitionUuid)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + definitionUuid));
        return repository.findBySettingDefinitionIdOrderByCreatedAtDesc(definition.getId(), pageable)
            .map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingChangeRequestResponse> findByRequester(final String requester, final Pageable pageable) {
        return repository.findByCreatedByOrderByCreatedAtDesc(requester, pageable).map(mapper::toResponse);
    }
}
