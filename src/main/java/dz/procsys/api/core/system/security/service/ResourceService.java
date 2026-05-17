package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreateResourceRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface ResourceService extends BaseService<CreateResourceRequest, UpdateResourceRequest, ResourceResponse, ResourceSummary> {
}
