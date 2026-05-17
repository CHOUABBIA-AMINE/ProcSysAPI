package dz.procsys.api.core.system.security.service;

import dz.procsys.api.core.system.security.dto.request.CreateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateResourceTypeRequest;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeResponse;
import dz.procsys.api.core.system.security.dto.response.ResourceTypeSummary;
import dz.procsys.api.platform.kernel.BaseService;

public interface ResourceTypeService extends BaseService<CreateResourceTypeRequest, UpdateResourceTypeRequest, ResourceTypeResponse, ResourceTypeSummary> {
}
