package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreateRoleRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateRoleRequest;
import dz.procsys.api.core.system.security.dto.response.RoleResponse;
import dz.procsys.api.core.system.security.dto.response.RoleSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface RoleService extends BaseService<CreateRoleRequest, UpdateRoleRequest, RoleResponse, RoleSummary> {
    
    void assignPermission(Long roleId, Long permissionId);
    
    void removePermission(Long roleId, Long permissionId);
}
