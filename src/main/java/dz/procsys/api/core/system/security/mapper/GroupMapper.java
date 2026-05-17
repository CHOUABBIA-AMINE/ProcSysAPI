package dz.procsys.api.core.system.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import dz.procsys.api.core.system.security.dto.request.CreateGroupRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateGroupRequest;
import dz.procsys.api.core.system.security.dto.response.GroupResponse;
import dz.procsys.api.core.system.security.dto.response.GroupSummary;
import dz.procsys.api.core.system.security.model.Group;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface GroupMapper extends BaseMapper<CreateGroupRequest, UpdateGroupRequest, GroupResponse, GroupSummary, Group> {

    @Override
    @Mapping(target = "roles", ignore = true)
    Group toEntity(CreateGroupRequest request);

    @Override
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromRequest(UpdateGroupRequest request, @MappingTarget Group entity);
}
