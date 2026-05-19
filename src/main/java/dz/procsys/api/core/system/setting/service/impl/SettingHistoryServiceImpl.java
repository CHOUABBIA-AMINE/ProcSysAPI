/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistoryServiceImpl
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Service / Impl
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.response.SettingHistoryResponse;
import dz.procsys.api.core.system.setting.mapper.SettingHistoryMapper;
import dz.procsys.api.core.system.setting.model.SettingChangeType;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.model.SettingHistory;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.repository.SettingHistoryRepository;
import dz.procsys.api.core.system.setting.service.SettingHistoryService;

/**
 * Production implementation of {@link SettingHistoryService}.
 * Manages the immutable audit trail for all setting changes.
 */
@Service
@Transactional(readOnly = true)
public class SettingHistoryServiceImpl implements SettingHistoryService {

    private static final Logger log = LoggerFactory.getLogger(SettingHistoryServiceImpl.class);

    private final SettingHistoryRepository repository;
    private final SettingDefinitionRepository definitionRepository;
    private final SettingHistoryMapper mapper;

    public SettingHistoryServiceImpl(
            final SettingHistoryRepository repository,
            final SettingDefinitionRepository definitionRepository,
            final SettingHistoryMapper mapper) {
        this.repository = repository;
        this.definitionRepository = definitionRepository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void record(
            final SettingDefinition definition,
            final SettingChangeType changeType,
            final String scopeReference,
            final String oldValue,
            final String newValue,
            final String reason,
            final String changedBy) {
        log.info("[SettingHistoryService] Recording history for definition '{}', changeType '{}', scope '{}'",
            definition.getCode(), changeType.getCode(), scopeReference);
        final SettingHistory history = SettingHistory.builder()
            .settingDefinition(definition)
            .changeType(changeType)
            .scopeReference(scopeReference)
            .oldValue(oldValue)
            .newValue(newValue)
            .reason(reason)
            .createdBy(changedBy)
            .build();
        repository.save(history);
        log.info("[SettingHistoryService] History recorded with Id: {}", history.getId());
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingHistoryResponse> findByDefinition(final Long definitionUuid, final Pageable pageable) {
        final SettingDefinition definition = definitionRepository.findById(definitionUuid)
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with Id: " + definitionUuid));
        return repository
            .findBySettingDefinitionIdOrderByCreatedAtDesc(definition.getId(), pageable)
            .map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingHistoryResponse> findByDefinitionAndScope(
            final Long definitionUuid,
            final String scopeReference,
            final Pageable pageable) {
        final SettingDefinition definition = definitionRepository.findById(definitionUuid)
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with Id: " + definitionUuid));
        return repository
            .findBySettingDefinitionIdAndScopeReferenceOrderByCreatedAtDesc(
                definition.getId(), scopeReference, pageable)
            .map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public SettingHistoryResponse findById(final Long id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingHistory not found with Id: " + id));
    }
}
