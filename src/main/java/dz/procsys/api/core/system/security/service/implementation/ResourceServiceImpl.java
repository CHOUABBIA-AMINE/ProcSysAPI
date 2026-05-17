package dz.procsys.api.core.system.security.service.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import dz.procsys.api.core.system.security.dto.request.CreateResourceRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceSummary;
import dz.procsys.api.core.system.security.mapper.ResourceMapper;
import dz.procsys.api.core.system.security.model.Resource;
import dz.procsys.api.core.system.security.model.ResourceType;
import dz.procsys.api.core.system.security.repository.ResourceRepository;
import dz.procsys.api.core.system.security.service.ResourceService;
import dz.procsys.api.platform.kernel.AbstractCrudService;
import dz.procsys.api.platform.kernel.ReferenceResolver;

@Service
public class ResourceServiceImpl extends AbstractCrudService<CreateResourceRequest, UpdateResourceRequest, ResourceResponse, ResourceSummary, Resource> implements ResourceService {

    public ResourceServiceImpl(ResourceRepository repository, ResourceMapper mapper, ReferenceResolver referenceResolver, ApplicationEventPublisher eventPublisher) {
        super(repository, mapper, referenceResolver, eventPublisher);
    }

    @Override
    protected Class<Resource> getEntityClass() {
        return Resource.class;
    }

    @Override
    protected void beforeCreate(CreateResourceRequest request, Resource entity) {
        if (request.resourceTypeId() != null) {
            entity.setResourceType(referenceResolver.resolve(request.resourceTypeId(), ResourceType.class));
        }
    }

    @Override
    protected void beforeUpdate(UpdateResourceRequest request, Resource entity) {
        if (request.resourceTypeId() != null) {
            entity.setResourceType(referenceResolver.resolve(request.resourceTypeId(), ResourceType.class));
        }
    }
}
