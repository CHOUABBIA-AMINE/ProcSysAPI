/**
 *	@Author		: System
 *	@Name		: SettingAccessPolicyServiceImpl
 *	@CreatedOn	: 05-19-2026
 *	@Type		: Class / Service Impl
 **/

package dz.procsys.api.core.system.setting.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingAccessPolicyResponse;
import dz.procsys.api.core.system.setting.mapper.SettingAccessPolicyMapper;
import dz.procsys.api.core.system.setting.model.SettingAccessPolicy;
import dz.procsys.api.core.system.setting.model.SettingDefinition;
import dz.procsys.api.core.system.setting.repository.SettingAccessPolicyRepository;
import dz.procsys.api.core.system.setting.repository.SettingDefinitionRepository;
import dz.procsys.api.core.system.setting.service.SettingAccessPolicyService;

@Service
@Transactional(readOnly = true)
public class SettingAccessPolicyServiceImpl implements SettingAccessPolicyService {

    private static final Logger log = LoggerFactory.getLogger(SettingAccessPolicyServiceImpl.class);

    private final SettingAccessPolicyRepository repository;
    private final SettingDefinitionRepository definitionRepository;
    private final SettingAccessPolicyMapper mapper;

    public SettingAccessPolicyServiceImpl(
            final SettingAccessPolicyRepository repository,
            final SettingDefinitionRepository definitionRepository,
            final SettingAccessPolicyMapper mapper) {
        this.repository = repository;
        this.definitionRepository = definitionRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public SettingAccessPolicyResponse create(final SettingAccessPolicyCreateRequest request) {
        final SettingDefinition definition = definitionRepository.findById(request.getSettingDefinitionId())
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with ID: " + request.getSettingDefinitionId()));
        if (repository.existsBySettingDefinitionIdAndRoleCode(definition.getId(), request.getRoleCode())) {
            throw new IllegalArgumentException("Access policy for role '" + request.getRoleCode() + "' on '" + definition.getCode() + "' already exists.");
        }
        final SettingAccessPolicy entity = mapper.toEntity(request);
        entity.setSettingDefinition(definition);
        final SettingAccessPolicy saved = repository.save(entity);
        log.info("[SettingAccessPolicyService] Created policy Id : {}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SettingAccessPolicyResponse update(final Long id, final SettingAccessPolicyUpdateRequest request) {
        final SettingAccessPolicy entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingAccessPolicy not found with Id: " + id));
        mapper.updateEntity(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public SettingAccessPolicyResponse findById(final Long id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("SettingAccessPolicy not found with Id: " + id));
    }

    @Override
    public List<SettingAccessPolicyResponse> findByDefinition(final Long definitionId) {
        final SettingDefinition definition = definitionRepository.findById(definitionId)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + definitionId));
        return mapper.toResponseList(repository.findBySettingDefinitionId(definition.getId()));
    }

    @Override
    public Page<SettingAccessPolicyResponse> findByRole(final String roleCode, final Pageable pageable) {
        return repository.findByRoleCode(roleCode, pageable).map(mapper::toResponse);
    }

    @Override
    public boolean canRead(final Long definitionId, final String roleCode) {
        final SettingDefinition definition = definitionRepository.findById(definitionId)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + definitionId));
        return repository.findBySettingDefinitionIdAndRoleCode(definition.getId(), roleCode)
            .map(p -> Boolean.TRUE.equals(p.getCanRead())).orElse(false);
    }

    @Override
    public boolean canWrite(final Long definitionId, final String roleCode) {
        final SettingDefinition definition = definitionRepository.findById(definitionId)
            .orElseThrow(() -> new IllegalArgumentException("SettingDefinition not found with Id: " + definitionId));
        return repository.findBySettingDefinitionIdAndRoleCode(definition.getId(), roleCode)
            .map(p -> Boolean.TRUE.equals(p.getCanWrite())).orElse(false);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        final SettingAccessPolicy entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SettingAccessPolicy not found with Id: " + id));
        repository.delete(entity);
        log.info("[SettingAccessPolicyService] Deleted policy Long: {}", id);
    }
}
