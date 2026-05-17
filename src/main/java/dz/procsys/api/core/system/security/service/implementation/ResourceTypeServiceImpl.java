package dz.procsys.api.core.system.security.service.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import dz.procsys.api.core.system.security.dto.request.CreateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeSummary;
import dz.procsys.api.core.system.security.mapper.ResourceTypeMapper;
import dz.procsys.api.core.system.security.model.ResourceType;
import dz.procsys.api.core.system.security.repository.ResourceTypeRepository;
import dz.procsys.api.core.system.security.service.ResourceTypeService;
import dz.procsys.api.platform.kernel.AbstractCrudService;
import dz.procsys.api.platform.kernel.ReferenceResolver;

@Service
public class ResourceTypeServiceImpl extends AbstractCrudService<CreateResourceTypeRequest, UpdateResourceTypeRequest, ResourceTypeResponse, ResourceTypeSummary, ResourceType> implements ResourceTypeService {

    public ResourceTypeServiceImpl(ResourceTypeRepository repository, ResourceTypeMapper mapper, ReferenceResolver referenceResolver, ApplicationEventPublisher eventPublisher) {
        super(repository, mapper, referenceResolver, eventPublisher);
    }

    @Override
    protected Class<ResourceType> getEntityClass() {
        return ResourceType.class;
    }
}
