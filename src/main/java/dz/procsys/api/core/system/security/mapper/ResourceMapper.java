package dz.procsys.api.core.system.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import dz.procsys.api.core.system.security.dto.request.CreateResourceRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceSummary;
import dz.procsys.api.core.system.security.model.Resource;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring")
public interface ResourceMapper extends BaseMapper<CreateResourceRequest, UpdateResourceRequest, ResourceResponse, ResourceSummary, Resource> {

    @Override
    @Mapping(target = "resourceTypeName", source = "resourceType.name")
    ResourceResponse toResponse(Resource entity);

    @Override
    @Mapping(target = "resourceType", ignore = true)
    Resource toEntity(CreateResourceRequest request);

    @Override
    @Mapping(target = "resourceType", ignore = true)
    void updateEntityFromRequest(UpdateResourceRequest request, @MappingTarget Resource entity);
}
