/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistoryMapper
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

import dz.procsys.api.core.system.setting.dto.response.SettingHistoryResponse;
import dz.procsys.api.core.system.setting.model.SettingHistory;

/**
 * MapStruct mapper for the SettingHistory entity.
 * History is read-only — no create/update mapping methods are defined.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingHistoryMapper {

    /**
     * Converts a SettingHistory entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "settingDefinition.id",   target = "settingDefinitionId")
    @Mapping(source = "settingDefinition.code", target = "settingDefinitionCode")
    @Mapping(source = "changeType.id",          target = "changeTypeId")
    @Mapping(source = "changeType.code",        target = "changeTypeCode")
    SettingHistoryResponse toResponse(SettingHistory entity);

    /**
     * Converts a list of SettingHistory entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingHistoryResponse> toResponseList(List<SettingHistory> entities);
}
