/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueTypeServiceImpl
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

import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueTypeResponse;
import dz.procsys.api.core.system.setting.mapper.SettingValueTypeMapper;
import dz.procsys.api.core.system.setting.model.SettingValueType;
import dz.procsys.api.core.system.setting.repository.SettingValueTypeRepository;
import dz.procsys.api.core.system.setting.service.SettingValueTypeService;

/**
 * Production implementation of {@link SettingValueTypeService}.
 */
@Service
@Transactional(readOnly = true)
public class SettingValueTypeServiceImpl implements SettingValueTypeService {

    private static final Logger log = LoggerFactory.getLogger(SettingValueTypeServiceImpl.class);

    private final SettingValueTypeRepository repository;
    private final SettingValueTypeMapper mapper;

    /**
     * Constructs a new SettingValueTypeServiceImpl.
     *
     * @param repository the SettingValueType JPA repository
     * @param mapper     the MapStruct mapper
     */
    public SettingValueTypeServiceImpl(
            final SettingValueTypeRepository repository,
            final SettingValueTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingValueTypeResponse create(final SettingValueTypeCreateRequest request) {
        log.info("[SettingValueTypeService] Creating SettingValueType with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A SettingValueType with code '" + request.getCode() + "' already exists."
            );
        }
        final SettingValueType entity = mapper.toEntity(request);
        final SettingValueType saved = repository.save(entity);
        log.info("[SettingValueTypeService] SettingValueType created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingValueTypeResponse update(final Long id, final SettingValueTypeUpdateRequest request) {
        log.info("[SettingValueTypeService] Updating SettingValueType with Id: {}", id);
        final SettingValueType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingValueType not found with Id: " + id));
        mapper.updateEntity(request, entity);
        final SettingValueType saved = repository.save(entity);
        log.info("[SettingValueTypeService] SettingValueType updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    public SettingValueTypeResponse findById(final Long id) {
        log.debug("[SettingValueTypeService] Fetching SettingValueType with Id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingValueType not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public SettingValueTypeResponse findByCode(final String code) {
        log.debug("[SettingValueTypeService] Fetching SettingValueType with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingValueType not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingValueTypeResponse> findAll(final Pageable pageable) {
        log.debug("[SettingValueTypeService] Fetching all SettingValueTypes, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingValueTypeService] Deleting SettingValueType with Id: {}", id);
        final SettingValueType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingValueType not found with Id: " + id));
        repository.delete(entity);
        log.info("[SettingValueTypeService] SettingValueType deleted with Id: {}", id);
    }
}
