package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreateGroupRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateGroupRequest;
import dz.procsys.api.core.system.security.dto.response.GroupResponse;
import dz.procsys.api.core.system.security.dto.response.GroupSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface GroupService extends BaseService<CreateGroupRequest, UpdateGroupRequest, GroupResponse, GroupSummary> {
    
    void assignRole(Long groupId, Long roleId);
    
    void removeRole(Long groupId, Long roleId);
}
