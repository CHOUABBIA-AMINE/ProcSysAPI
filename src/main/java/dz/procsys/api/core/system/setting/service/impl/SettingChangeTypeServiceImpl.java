/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeTypeServiceImpl
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

import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeTypeResponse;
import dz.procsys.api.core.system.setting.mapper.SettingChangeTypeMapper;
import dz.procsys.api.core.system.setting.model.SettingChangeType;
import dz.procsys.api.core.system.setting.repository.SettingChangeTypeRepository;
import dz.procsys.api.core.system.setting.service.SettingChangeTypeService;

/**
 * Production implementation of {@link SettingChangeTypeService}.
 */
@Service
@Transactional(readOnly = true)
public class SettingChangeTypeServiceImpl implements SettingChangeTypeService {

    private static final Logger log = LoggerFactory.getLogger(SettingChangeTypeServiceImpl.class);

    private final SettingChangeTypeRepository repository;
    private final SettingChangeTypeMapper mapper;

    /**
     * Constructs a new SettingChangeTypeServiceImpl.
     *
     * @param repository the SettingChangeType JPA repository
     * @param mapper     the MapStruct mapper
     */
    public SettingChangeTypeServiceImpl(
            final SettingChangeTypeRepository repository,
            final SettingChangeTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingChangeTypeResponse create(final SettingChangeTypeCreateRequest request) {
        log.info("[SettingChangeTypeService] Creating SettingChangeType with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A SettingChangeType with code '" + request.getCode() + "' already exists."
            );
        }
        final SettingChangeType entity = mapper.toEntity(request);
        final SettingChangeType saved = repository.save(entity);
        log.info("[SettingChangeTypeService] SettingChangeType created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingChangeTypeResponse update(final Long id, final SettingChangeTypeUpdateRequest request) {
        log.info("[SettingChangeTypeService] Updating SettingChangeType with Id: {}", id);
        final SettingChangeType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeType not found with Id: " + id));
        mapper.updateEntity(request, entity);
        final SettingChangeType saved = repository.save(entity);
        log.info("[SettingChangeTypeService] SettingChangeType updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    public SettingChangeTypeResponse findById(final Long id) {
        log.debug("[SettingChangeTypeService] Fetching SettingChangeType with Id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeType not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public SettingChangeTypeResponse findByCode(final String code) {
        log.debug("[SettingChangeTypeService] Fetching SettingChangeType with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeType not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingChangeTypeResponse> findAll(final Pageable pageable) {
        log.debug("[SettingChangeTypeService] Fetching all SettingChangeTypes, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingChangeTypeService] Deleting SettingChangeType with Id: {}", id);
        final SettingChangeType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingChangeType not found with Id: " + id));
        repository.delete(entity);
        log.info("[SettingChangeTypeService] SettingChangeType deleted with Id: {}", id);
    }
}
