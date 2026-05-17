package dz.procsys.api.core.system.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import dz.procsys.api.core.system.security.dto.request.CreateRoleRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateRoleRequest;
import dz.procsys.api.core.system.security.dto.response.RoleResponse;
import dz.procsys.api.core.system.security.dto.response.RoleSummary;
import dz.procsys.api.core.system.security.model.Role;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper extends BaseMapper<CreateRoleRequest, UpdateRoleRequest, RoleResponse, RoleSummary, Role> {
    
    @Override
    @Mapping(target = "permissions", ignore = true)
    Role toEntity(CreateRoleRequest request);

    @Override
    @Mapping(target = "permissions", ignore = true)
    void updateEntityFromRequest(UpdateRoleRequest request, @MappingTarget Role entity);
}
