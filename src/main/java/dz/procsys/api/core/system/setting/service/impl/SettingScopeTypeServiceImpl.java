/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingScopeTypeServiceImpl
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

import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingScopeTypeResponse;
import dz.procsys.api.core.system.setting.mapper.SettingScopeTypeMapper;
import dz.procsys.api.core.system.setting.model.SettingScopeType;
import dz.procsys.api.core.system.setting.repository.SettingScopeTypeRepository;
import dz.procsys.api.core.system.setting.service.SettingScopeTypeService;

/**
 * Production implementation of {@link SettingScopeTypeService}.
 */
@Service
@Transactional(readOnly = true)
public class SettingScopeTypeServiceImpl implements SettingScopeTypeService {

    private static final Logger log = LoggerFactory.getLogger(SettingScopeTypeServiceImpl.class);

    private final SettingScopeTypeRepository repository;
    private final SettingScopeTypeMapper mapper;

    /**
     * Constructs a new SettingScopeTypeServiceImpl.
     *
     * @param repository the SettingScopeType JPA repository
     * @param mapper     the MapStruct mapper
     */
    public SettingScopeTypeServiceImpl(
            final SettingScopeTypeRepository repository,
            final SettingScopeTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingScopeTypeResponse create(final SettingScopeTypeCreateRequest request) {
        log.info("[SettingScopeTypeService] Creating SettingScopeType with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A SettingScopeType with code '" + request.getCode() + "' already exists."
            );
        }
        final SettingScopeType entity = mapper.toEntity(request);
        final SettingScopeType saved = repository.save(entity);
        log.info("[SettingScopeTypeService] SettingScopeType created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public SettingScopeTypeResponse update(final Long id, final SettingScopeTypeUpdateRequest request) {
        log.info("[SettingScopeTypeService] Updating SettingScopeType with Id: {}", id);
        final SettingScopeType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingScopeType not found with Id: " + id));
        mapper.updateEntity(request, entity);
        final SettingScopeType saved = repository.save(entity);
        log.info("[SettingScopeTypeService] SettingScopeType updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    public SettingScopeTypeResponse findById(final Long id) {
        log.debug("[SettingScopeTypeService] Fetching SettingScopeType with Id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingScopeType not found with Id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public SettingScopeTypeResponse findByCode(final String code) {
        log.debug("[SettingScopeTypeService] Fetching SettingScopeType with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingScopeType not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public List<SettingScopeTypeResponse> findAllOrderedByPriority() {
        log.debug("[SettingScopeTypeService] Fetching all SettingScopeTypes ordered by priority");
        return mapper.toResponseList(repository.findAllByOrderByPriorityAsc());
    }

    /** {@inheritDoc} */
    @Override
    public Page<SettingScopeTypeResponse> findAll(final Pageable pageable) {
        log.debug("[SettingScopeTypeService] Fetching all SettingScopeTypes, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[SettingScopeTypeService] Deleting SettingScopeType with Id: {}", id);
        final SettingScopeType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingScopeType not found with Id: " + id));
        repository.delete(entity);
        log.info("[SettingScopeTypeService] SettingScopeType deleted with Id: {}", id);
    }
}
