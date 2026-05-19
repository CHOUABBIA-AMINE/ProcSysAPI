/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingDefinitionServiceImpl
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

import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingDefinitionResponse;
import dz.procsys.api.core.system.setting.mapper.SettingDefinitionMapper;
import dz.procsys.api.core.system.setting.model.SettingCategory;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.model.SettingValueType;
import dz.procsys.api.core.system.setting.repository.SettingCategoryRepository;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.repository.SettingValueTypeRepository;
import dz.procsys.api.core.system.setting.service.SettingDefinitionService;

/**
 * Production implementation of {@link SettingDefinitionService}.
 * SettingDefinition is the aggregate root of the settings domain.
 */
@Service
@Transactional(readOnly = true)
public class SettingDefinitionServiceImpl implements SettingDefinitionService {

    private static final Logger log = LoggerFactory.getLogger(SettingDefinitionServiceImpl.class);

    private final SettingDefinitionRepository repository;
    private final SettingCategoryRepository categoryRepository;
    private final SettingValueTypeRepository valueTypeRepository;
    private final SettingDefinitionMapper mapper;

    /**
     * Constructs a new SettingDefinitionServiceImpl.
     *
     * @param repository         the SettingDefinition JPA repository
     * @param categoryRepository the SettingCategory JPA repository
     * @param valueTypeRepository the SettingValueType JPA repository
     * @param mapper             the MapStruct mapper
     */
    public SettingDefinitionServiceImpl(
            final SettingDefinitionRepository repository,
            final SettingCategoryRepository categoryRepository,
            final SettingValueTypeRepository valueTypeRepository,
            final SettingDefinitionMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.valueTypeRepository = valueTypeRepository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingDefinitionResponse create(final SettingDefinitionCreateRequest request) {
        log.info("[SettingDefinitionService] Creating SettingDefinition with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A SettingDefinition with code '" + request.getCode() + "' already exists."
            );
        }
        final SettingCategory category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingCategory not found with ID: " + request.getCategoryId()
            ));
        final SettingValueType valueType = valueTypeRepository.findById(request.getValueTypeId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingValueType not found with ID: " + request.getValueTypeId()
            ));
        if (Boolean.TRUE.equals(request.getIsEncrypted()) && Boolean.FALSE.equals(valueType.getSupportsEncryption())) {
            throw new IllegalArgumentException(
                "SettingValueType '" + valueType.getCode() + "' does not support encryption."
            );
        }
        final SettingDefinition entity = mapper.toEntity(request);
        entity.setCategory(category);
        entity.setValueType(valueType);
        final SettingDefinition saved = repository.save(entity);
        log.info("[SettingDefinitionService] SettingDefinition created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingDefinitionResponse update(final Long id, final SettingDefinitionUpdateRequest request) {
        log.info("[SettingDefinitionService] Updating SettingDefinition with Id: {}", id);
        final SettingDefinition entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + id));
        if (Boolean.FALSE.equals(entity.getIsMutable())) {
            throw new IllegalStateException(
                "SettingDefinition '" + entity.getCode() + "' is immutable and cannot be modified."
            );
        }
        final SettingCategory category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException(
                "SettingCategory not found with ID: " + request.getCategoryId()
            ));
        mapper.updateEntity(request, entity);
        entity.setCategory(category);
        final SettingDefinition saved = repository.save(entity);
        log.info("[SettingDefinitionService] SettingDefinition updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingDefinitionResponse activate(final Long id) {
        log.info("[SettingDefinitionService] Activating SettingDefinition with Id: {}", id);
        final SettingDefinition entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + id));
        entity.setIsActive(Boolean.TRUE);
        return mapper.toResponse(repository.save(entity));
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingDefinitionResponse deactivate(final Long id) {
        log.info("[SettingDefinitionService] Deactivating SettingDefinition with Id: {}", id);
        final SettingDefinition entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + id));
        entity.setIsActive(Boolean.FALSE);
        return mapper.toResponse(repository.save(entity));
    }

    /** {@inheritDoc} */
    @Override
    public SettingDefinitionResponse findById(final Long id) {
        log.debug("[SettingDefinitionService] Fetching SettingDefinition with Id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public SettingDefinitionResponse findByCode(final String code) {
        log.debug("[SettingDefinitionService] Fetching SettingDefinition with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingDefinitionResponse> findAll(final Pageable pageable) {
        log.debug("[SettingDefinitionService] Fetching all SettingDefinitions, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingDefinitionResponse> findAllActive(final Pageable pageable) {
        log.debug("[SettingDefinitionService] Fetching active SettingDefinitions, page: {}", pageable.getPageNumber());
        return repository.findByIsActive(Boolean.TRUE, pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingDefinitionResponse> findByCategory(final Long categoryUuid, final Pageable pageable) {
        log.debug("[SettingDefinitionService] Fetching SettingDefinitions for category Long: {}", categoryUuid);
        final SettingCategory category = categoryRepository.findById(categoryUuid)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + categoryUuid));
        return repository.findByCategoryId(category.getId(), pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingDefinitionService] Deleting SettingDefinition with Id: {}", id);
        final SettingDefinition entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + id));
        if (entity.getValues() != null && !entity.getValues().isEmpty()) {
            throw new IllegalStateException(
                "Cannot delete SettingDefinition '" + entity.getCode() + "': it has associated values."
            );
        }
        repository.delete(entity);
        log.info("[SettingDefinitionService] SettingDefinition deleted with Id: {}", id);
    }
}
