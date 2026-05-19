/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingConstraintMapper
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
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.setting.dto.request.SettingConstraintCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingConstraintUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingConstraintResponse;
import dz.procsys.api.core.system.setting.model.SettingConstraint;

/**
 * MapStruct mapper for the SettingConstraint entity.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingConstraintMapper {

    /**
     * Converts a SettingConstraint entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "settingDefinition.id",   target = "settingDefinitionId")
    @Mapping(source = "settingDefinition.code", target = "settingDefinitionCode")
    @Mapping(source = "constraintType.id",      target = "constraintTypeId")
    @Mapping(source = "constraintType.code",    target = "constraintTypeCode")
    SettingConstraintResponse toResponse(SettingConstraint entity);

    /**
     * Converts a list of SettingConstraint entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingConstraintResponse> toResponseList(List<SettingConstraint> entities);

    /**
     * Converts a create request DTO to a new SettingConstraint entity.
     * Associations are resolved by the service layer.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "constraintType",    ignore = true)
    SettingConstraint toEntity(SettingConstraintCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingConstraint entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "constraintType",    ignore = true)
    void updateEntity(SettingConstraintUpdateRequest request, @MappingTarget SettingConstraint entity);
}
