/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingCategoryServiceImpl
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.request.SettingCategoryCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingCategoryUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingCategoryResponse;
import dz.procsys.api.core.system.setting.mapper.SettingCategoryMapper;
import dz.procsys.api.core.system.setting.model.SettingCategory;
import dz.procsys.api.core.system.setting.repository.SettingCategoryRepository;
import dz.procsys.api.core.system.setting.service.SettingCategoryService;

/**
 * Production implementation of {@link SettingCategoryService}.
 */
@Service
@Transactional(readOnly = true)
public class SettingCategoryServiceImpl implements SettingCategoryService {

    private static final Logger log = LoggerFactory.getLogger(SettingCategoryServiceImpl.class);

    private final SettingCategoryRepository repository;
    private final SettingCategoryMapper mapper;

    /**
     * Constructs a new SettingCategoryServiceImpl.
     *
     * @param repository the SettingCategory JPA repository
     * @param mapper     the MapStruct mapper
     */
    public SettingCategoryServiceImpl(
            final SettingCategoryRepository repository,
            final SettingCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingCategoryResponse create(final SettingCategoryCreateRequest request) {
        log.info("[SettingCategoryService] Creating SettingCategory with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A SettingCategory with code '" + request.getCode() + "' already exists."
            );
        }
        final SettingCategory entity = mapper.toEntity(request);
        if (request.getParentCategoryId() != null) {
            final SettingCategory parent = repository.findById(request.getParentCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "Parent SettingCategory not found with ID: " + request.getParentCategoryId()
                ));
            entity.setParentCategory(parent);
        }
        final SettingCategory saved = repository.save(entity);
        log.info("[SettingCategoryService] SettingCategory created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingCategoryResponse update(final Long id, final SettingCategoryUpdateRequest request) {
        log.info("[SettingCategoryService] Updating SettingCategory with Id: {}", id);
        final SettingCategory entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + id));
        mapper.updateEntity(request, entity);
        if (request.getParentCategoryId() != null) {
            if (request.getParentCategoryId().equals(entity.getId())) {
                throw new IllegalArgumentException("A category cannot be its own parent.");
            }
            final SettingCategory parent = repository.findById(request.getParentCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "Parent SettingCategory not found with ID: " + request.getParentCategoryId()
                ));
            entity.setParentCategory(parent);
        } else {
            entity.setParentCategory(null);
        }
        final SettingCategory saved = repository.save(entity);
        log.info("[SettingCategoryService] SettingCategory updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingCategoryResponse activate(final Long id) {
        log.info("[SettingCategoryService] Activating SettingCategory with Id: {}", id);
        final SettingCategory entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + id));
        entity.setIsActive(Boolean.TRUE);
        return mapper.toResponse(repository.save(entity));
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingCategoryResponse deactivate(final Long id) {
        log.info("[SettingCategoryService] Deactivating SettingCategory with Id: {}", id);
        final SettingCategory entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + id));
        entity.setIsActive(Boolean.FALSE);
        return mapper.toResponse(repository.save(entity));
    }

    /** {@inheritDoc} */
    @Override
    public SettingCategoryResponse findById(final Long id) {
        log.debug("[SettingCategoryService] Fetching SettingCategory with Id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public SettingCategoryResponse findByCode(final String code) {
        log.debug("[SettingCategoryService] Fetching SettingCategory with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public List<SettingCategoryResponse> findRootCategories() {
        log.debug("[SettingCategoryService] Fetching root SettingCategories");
        return mapper.toResponseList(repository.findByParentCategoryIsNull());
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingCategoryResponse> findAll(final Pageable pageable) {
        log.debug("[SettingCategoryService] Fetching all SettingCategories, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingCategoryResponse> findAllActive(final Pageable pageable) {
        log.debug("[SettingCategoryService] Fetching active SettingCategories, page: {}", pageable.getPageNumber());
        return repository.findByIsActive(Boolean.TRUE, pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingCategoryService] Deleting SettingCategory with Id: {}", id);
        final SettingCategory entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingCategory not found with Id: " + id));
        if (entity.getSubCategories() != null && !entity.getSubCategories().isEmpty()) {
            throw new IllegalStateException(
                "Cannot delete SettingCategory '" + entity.getCode() + "': it has sub-categories."
            );
        }
        if (entity.getSettings() != null && !entity.getSettings().isEmpty()) {
            throw new IllegalStateException(
                "Cannot delete SettingCategory '" + entity.getCode() + "': it contains setting definitions."
            );
        }
        repository.delete(entity);
        log.info("[SettingCategoryService] SettingCategory deleted with Id: {}", id);
    }
}
