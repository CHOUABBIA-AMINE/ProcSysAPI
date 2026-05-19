/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueTypeMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueTypeResponse;
import dz.procsys.api.core.system.setting.model.SettingValueType;

/**
 * MapStruct mapper for the SettingValueType entity.
 * Handles conversion between entity, create/update request DTOs, and response DTO.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingValueTypeMapper {

    /**
     * Converts a SettingValueType entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    SettingValueTypeResponse toResponse(SettingValueType entity);

    /**
     * Converts a list of SettingValueType entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingValueTypeResponse> toResponseList(List<SettingValueType> entities);

    /**
     * Converts a create request DTO to a new SettingValueType entity.
     * Id and audit fields are populated by JPA lifecycle callbacks.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    SettingValueType toEntity(SettingValueTypeCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingValueType entity.
     * Null fields in the request are ignored (IGNORE strategy).
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(SettingValueTypeUpdateRequest request, @MappingTarget SettingValueType entity);
}
