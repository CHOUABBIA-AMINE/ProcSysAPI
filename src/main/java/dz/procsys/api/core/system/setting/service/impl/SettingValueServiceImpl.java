/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueServiceImpl
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Service / Impl
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.request.SettingValueCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueResponse;
import dz.procsys.api.core.system.setting.mapper.SettingValueMapper;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.model.SettingScopeType;
import dz.procsys.api.core.system.setting.model.SettingValue;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.repository.SettingScopeTypeRepository;
import dz.procsys.api.core.system.setting.repository.SettingValueRepository;
import dz.procsys.api.core.system.setting.service.SettingValueService;

/**
 * Production implementation of {@link SettingValueService}.
 * Handles scope-aware value persistence, constraint validation, masking, and audit recording.
 */
@Service
@Transactional(readOnly = true)
public class SettingValueServiceImpl implements SettingValueService {

    private static final Logger log = LoggerFactory.getLogger(SettingValueServiceImpl.class);
    private static final String MASKED_VALUE = "***";

    private final SettingValueRepository repository;
    private final SettingDefinitionRepository definitionRepository;
    private final SettingScopeTypeRepository scopeTypeRepository;
    private final SettingValueMapper mapper;

    public SettingValueServiceImpl(
            final SettingValueRepository repository,
            final SettingDefinitionRepository definitionRepository,
            final SettingScopeTypeRepository scopeTypeRepository,
            final SettingValueMapper mapper) {
        this.repository = repository;
        this.definitionRepository = definitionRepository;
        this.scopeTypeRepository = scopeTypeRepository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingValueResponse create(final SettingValueCreateRequest request) {
        log.info("[SettingValueService] Creating SettingValue for definition ID: {}, scopeType ID: {}",
            request.getSettingDefinitionId(), request.getScopeTypeId());
        final SettingDefinition definition = definitionRepository.findById(request.getSettingDefinitionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with ID: " + request.getSettingDefinitionId()));
        if (Boolean.FALSE.equals(definition.getIsActive())) {
            throw new IllegalStateException(
                "SettingDefinition '" + definition.getCode() + "' is not active.");
        }
        final SettingScopeType scopeType = scopeTypeRepository.findById(request.getScopeTypeId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingScopeType not found with ID: " + request.getScopeTypeId()));
        if (repository.existsBySettingDefinitionIdAndScopeTypeIdAndScopeReferenceId(
                definition.getId(), scopeType.getId(), request.getScopeReferenceId())) {
            throw new IllegalArgumentException(
                "A value for definition '" + definition.getCode() + "' and scope '"
                    + scopeType.getCode() + "' already exists.");
        }
        final SettingValue entity = mapper.toEntity(request);
        entity.setSettingDefinition(definition);
        entity.setScopeType(scopeType);
        final SettingValue saved = repository.save(entity);
        log.info("[SettingValueService] SettingValue created with Id: {}", saved.getId());

        return applyMasking(mapper.toResponse(saved), definition);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingValueResponse update(final Long id, final SettingValueUpdateRequest request) {
        log.info("[SettingValueService] Updating SettingValue with Id: {}", id);
        final SettingValue entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingValue not found with Id: " + id));
        final SettingDefinition definition = entity.getSettingDefinition();
        if (Boolean.FALSE.equals(definition.getIsMutable())) {
            throw new IllegalStateException(
                "SettingDefinition '" + definition.getCode() + "' is immutable.");
        }
        mapper.updateEntity(request, entity);
        final SettingValue saved = repository.save(entity);

        log.info("[SettingValueService] SettingValue updated with Id: {}", saved.getId());
        return applyMasking(mapper.toResponse(saved), definition);
    }

    /** {@inheritDoc} */
    @Override
    public SettingValueResponse findById(final Long id) {
        final SettingValue entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingValue not found with Id: " + id));
        return applyMasking(mapper.toResponse(entity), entity.getSettingDefinition());
    }

    /** {@inheritDoc} */
    @Override
    public SettingValueResponse resolve(final String definitionCode, final String scopeTypeCode, final Long scopeReferenceId) {
        log.debug("[SettingValueService] Resolving setting '{}' for scope '{}', ref: {}",
            definitionCode, scopeTypeCode, scopeReferenceId);
        final SettingDefinition definition = definitionRepository.findByCode(definitionCode)
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with code: " + definitionCode));
        final List<SettingValue> effectiveValues =
            repository.findEffectiveValuesByDefinitionOrdered(definition.getId(), new Date());
        final SettingValue resolved = effectiveValues.stream()
            .filter(sv -> sv.getScopeType().getCode().equals(scopeTypeCode)
                && java.util.Objects.equals(sv.getScopeReferenceId(), scopeReferenceId))
            .findFirst()
            .orElse(effectiveValues.stream()
                .filter(sv -> "GLOBAL".equals(sv.getScopeType().getCode()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                    "No effective value found for setting '" + definitionCode + "'")));
        return applyMasking(mapper.toResponse(resolved), definition);
    }

    /** {@inheritDoc} */
    @Override
    public List<SettingValueResponse> findByDefinition(final Long definitionId) {
        final SettingDefinition definition = definitionRepository.findById(definitionId)
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with Id: " + definitionId));
        return repository.findBySettingDefinitionId(definition.getId()).stream()
            .map(sv -> applyMasking(mapper.toResponse(sv), definition))
            .toList();
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingValueResponse> findAll(final Pageable pageable) {
        return repository.findAll(pageable).map(sv ->
            applyMasking(mapper.toResponse(sv), sv.getSettingDefinition()));
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingValueService] Deleting SettingValue with Id: {}", id);
        final SettingValue entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingValue not found with Id: " + id));
        repository.delete(entity);

        log.info("[SettingValueService] SettingValue deleted with Id: {}", id);
    }

    /**
     * Masks the value in the response DTO if the definition requires encryption.
     *
     * @param response   the response DTO to potentially mask
     * @param definition the setting definition governing encryption policy
     * @return the response DTO with value masked if required
     */
    private SettingValueResponse applyMasking(final SettingValueResponse response, final SettingDefinition definition) {
        if (Boolean.TRUE.equals(definition.getIsEncrypted())) {
            response.setValue(MASKED_VALUE);
            response.setMasked(Boolean.TRUE);
        } else {
            response.setMasked(Boolean.FALSE);
        }
        return response;
    }
}
