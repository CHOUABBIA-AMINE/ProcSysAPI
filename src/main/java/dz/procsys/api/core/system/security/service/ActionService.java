package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreateActionRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateActionRequest;
import dz.procsys.api.core.system.security.dto.response.ActionResponse;
import dz.procsys.api.core.system.security.dto.response.ActionSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface ActionService extends BaseService<CreateActionRequest, UpdateActionRequest, ActionResponse, ActionSummary> {
}
