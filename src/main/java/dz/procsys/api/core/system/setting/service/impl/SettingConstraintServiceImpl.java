/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingConstraintServiceImpl
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Service / Impl
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.request.SettingConstraintCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingConstraintUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingConstraintResponse;
import dz.procsys.api.core.system.setting.mapper.SettingConstraintMapper;
import dz.procsys.api.core.system.setting.model.ConstraintType;
import dz.procsys.api.core.system.setting.model.SettingConstraint;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.repository.ConstraintTypeRepository;
import dz.procsys.api.core.system.setting.repository.SettingConstraintRepository;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.service.SettingConstraintService;

/**
 * Production implementation of {@link SettingConstraintService}.
 */
@Service
@Transactional(readOnly = true)
public class SettingConstraintServiceImpl implements SettingConstraintService {

    private static final Logger log = LoggerFactory.getLogger(SettingConstraintServiceImpl.class);

    private final SettingConstraintRepository repository;
    private final SettingDefinitionRepository definitionRepository;
    private final ConstraintTypeRepository constraintTypeRepository;
    private final SettingConstraintMapper mapper;

    public SettingConstraintServiceImpl(
            final SettingConstraintRepository repository,
            final SettingDefinitionRepository definitionRepository,
            final ConstraintTypeRepository constraintTypeRepository,
            final SettingConstraintMapper mapper) {
        this.repository = repository;
        this.definitionRepository = definitionRepository;
        this.constraintTypeRepository = constraintTypeRepository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingConstraintResponse create(final SettingConstraintCreateRequest request) {
        log.info("[SettingConstraintService] Attaching constraint type ID {} to definition ID {}",
            request.getConstraintTypeId(), request.getSettingDefinitionId());
        final SettingDefinition definition = definitionRepository.findById(request.getSettingDefinitionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with ID: " + request.getSettingDefinitionId()));
        final ConstraintType constraintType = constraintTypeRepository.findById(request.getConstraintTypeId())
            .orElseThrow(() -> new IllegalArgumentException(
                "ConstraintType not found with ID: " + request.getConstraintTypeId()));
        if (repository.existsBySettingDefinitionIdAndConstraintTypeId(
                definition.getId(), constraintType.getId())) {
            throw new IllegalArgumentException(
                "Constraint of type '" + constraintType.getCode() + "' already exists on definition '"
                    + definition.getCode() + "'.");
        }
        final SettingConstraint entity = mapper.toEntity(request);
        entity.setSettingDefinition(definition);
        entity.setConstraintType(constraintType);
        final SettingConstraint saved = repository.save(entity);
        log.info("[SettingConstraintService] SettingConstraint created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingConstraintResponse update(final Long id, final SettingConstraintUpdateRequest request) {
        log.info("[SettingConstraintService] Updating SettingConstraint with Id: {}", id);
        final SettingConstraint entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingConstraint not found with Id: " + id));
        mapper.updateEntity(request, entity);
        final SettingConstraint saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    public SettingConstraintResponse findById(final Long id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingConstraint not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public List<SettingConstraintResponse> findByDefinition(final Long definitionUuid) {
        final SettingDefinition definition = definitionRepository.findById(definitionUuid)
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingDefinition not found with Id: " + definitionUuid));
        return mapper.toResponseList(repository.findBySettingDefinitionId(definition.getId()));
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingConstraintService] Deleting SettingConstraint with Id: {}", id);
        final SettingConstraint entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingConstraint not found with Id: " + id));
        repository.delete(entity);
        log.info("[SettingConstraintService] SettingConstraint deleted with Id: {}", id);
    }
}
