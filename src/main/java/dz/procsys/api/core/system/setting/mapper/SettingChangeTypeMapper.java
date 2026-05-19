/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeTypeMapper
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Mapper
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeTypeResponse;
import dz.procsys.api.core.system.setting.model.SettingChangeType;

/**
 * MapStruct mapper for the SettingChangeType entity.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingChangeTypeMapper {

    /**
     * Converts a SettingChangeType entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    SettingChangeTypeResponse toResponse(SettingChangeType entity);

    /**
     * Converts a list of SettingChangeType entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingChangeTypeResponse> toResponseList(List<SettingChangeType> entities);

    /**
     * Converts a create request DTO to a new SettingChangeType entity.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    SettingChangeType toEntity(SettingChangeTypeCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingChangeType entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(SettingChangeTypeUpdateRequest request, @MappingTarget SettingChangeType entity);
}
