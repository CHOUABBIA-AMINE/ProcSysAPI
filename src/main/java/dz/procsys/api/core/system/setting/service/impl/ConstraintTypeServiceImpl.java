/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ConstraintTypeServiceImpl
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

import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.ConstraintTypeResponse;
import dz.procsys.api.core.system.setting.mapper.ConstraintTypeMapper;
import dz.procsys.api.core.system.setting.model.ConstraintType;
import dz.procsys.api.core.system.setting.repository.ConstraintTypeRepository;
import dz.procsys.api.core.system.setting.service.ConstraintTypeService;

/**
 * Production implementation of {@link ConstraintTypeService}.
 */
@Service
@Transactional(readOnly = true)
public class ConstraintTypeServiceImpl implements ConstraintTypeService {

    private static final Logger log = LoggerFactory.getLogger(ConstraintTypeServiceImpl.class);

    private final ConstraintTypeRepository repository;
    private final ConstraintTypeMapper mapper;

    /**
     * Constructs a new ConstraintTypeServiceImpl.
     *
     * @param repository the ConstraintType JPA repository
     * @param mapper     the MapStruct mapper
     */
    public ConstraintTypeServiceImpl(
            final ConstraintTypeRepository repository,
            final ConstraintTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public ConstraintTypeResponse create(final ConstraintTypeCreateRequest request) {
        log.info("[ConstraintTypeService] Creating ConstraintType with code: {}", request.getCode());
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                "A ConstraintType with code '" + request.getCode() + "' already exists."
            );
        }
        final ConstraintType entity = mapper.toEntity(request);
        final ConstraintType saved = repository.save(entity);
        log.info("[ConstraintTypeService] ConstraintType created with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public ConstraintTypeResponse update(final Long id, final ConstraintTypeUpdateRequest request) {
        log.info("[ConstraintTypeService] Updating ConstraintType with id: {}", id);
        final ConstraintType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ConstraintType not found with id: " + id));
        mapper.updateEntity(request, entity);
        final ConstraintType saved = repository.save(entity);
        log.info("[ConstraintTypeService] ConstraintType updated with Id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    /** {@inheritDoc} */
    @Override
    public ConstraintTypeResponse findById(final Long id) {
        log.debug("[ConstraintTypeService] Fetching ConstraintType with id: {}", id);
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("ConstraintType not found with id: " + id));
    }

    /** {@inheritDoc} */
    @Override
    public ConstraintTypeResponse findByCode(final String code) {
        log.debug("[ConstraintTypeService] Fetching ConstraintType with code: {}", code);
        return repository.findByCode(code)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("ConstraintType not found with code: " + code));
    }

    /** {@inheritDoc} */
    @Override
    public Page<ConstraintTypeResponse> findAll(final Pageable pageable) {
        log.debug("[ConstraintTypeService] Fetching all ConstraintTypes, page: {}", pageable.getPageNumber());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("[ConstraintTypeService] Deleting ConstraintType with id: {}", id);
        final ConstraintType entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ConstraintType not found with id: " + id));
        repository.delete(entity);
        log.info("[ConstraintTypeService] ConstraintType deleted with id: {}", id);
    }
}
