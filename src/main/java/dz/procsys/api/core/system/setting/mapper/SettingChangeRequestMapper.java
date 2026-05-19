/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestMapper
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Mapper
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.setting.dto.request.SettingChangeRequestCreateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeRequestResponse;
import dz.procsys.api.core.system.setting.model.SettingChangeRequest;

/**
 * MapStruct mapper for the SettingChangeRequest entity.
 * Decisions (approve/reject) are handled directly in the service layer; no update mapper needed.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingChangeRequestMapper {

    /**
     * Converts a SettingChangeRequest entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "settingDefinition.id",   target = "settingDefinitionId")
    @Mapping(source = "settingDefinition.code", target = "settingDefinitionCode")
    @Mapping(source = "changeType.id",          target = "changeTypeId")
    @Mapping(source = "changeType.code",        target = "changeTypeCode")
    SettingChangeRequestResponse toResponse(SettingChangeRequest entity);

    /**
     * Converts a list of SettingChangeRequest entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingChangeRequestResponse> toResponseList(List<SettingChangeRequest> entities);

    /**
     * Converts a create request DTO to a new SettingChangeRequest entity.
     * Associations and status defaults are resolved by the service layer.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "changeType",        ignore = true)
    @Mapping(target = "status",            ignore = true)
    @Mapping(target = "approverRemarks",   ignore = true)
    @Mapping(target = "decidedAt",         ignore = true)
    @Mapping(target = "decidedBy",         ignore = true)
    SettingChangeRequest toEntity(SettingChangeRequestCreateRequest request);
}
