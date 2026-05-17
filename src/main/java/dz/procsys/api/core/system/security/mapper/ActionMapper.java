package dz.procsys.api.core.system.security.mapper;

import org.mapstruct.Mapper;

import ch.qos.logback.core.joran.action.Action;
import dz.procsys.api.core.system.security.dto.request.CreateActionRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateActionRequest;
import dz.procsys.api.core.system.security.dto.response.ActionResponse;
import dz.procsys.api.core.system.security.dto.response.ActionSummary;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring")
public interface ActionMapper extends BaseMapper<CreateActionRequest, UpdateActionRequest, ActionResponse, ActionSummary, Action> {
}
