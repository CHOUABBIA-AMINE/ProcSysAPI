package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreatePermissionRequest;
import dz.procsys.api.core.system.security.dto.request.UpdatePermissionRequest;
import dz.procsys.api.core.system.security.dto.response.PermissionResponse;
import dz.procsys.api.core.system.security.dto.response.PermissionSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface PermissionService extends BaseService<CreatePermissionRequest, UpdatePermissionRequest, PermissionResponse, PermissionSummary> {
}
