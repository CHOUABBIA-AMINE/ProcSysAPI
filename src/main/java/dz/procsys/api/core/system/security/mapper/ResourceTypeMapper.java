package dz.procsys.api.core.system.security.mapper;

import org.mapstruct.Mapper;

import dz.procsys.api.core.system.security.dto.request.CreateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeSummary;
import dz.procsys.api.core.system.security.model.ResourceType;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring")
public interface ResourceTypeMapper extends BaseMapper<CreateResourceTypeRequest, UpdateResourceTypeRequest, ResourceTypeResponse, ResourceTypeSummary, ResourceType> {
}
