/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingDefinitionMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingDefinitionResponse;
import dz.procsys.api.core.system.setting.model.SettingDefinition;

/**
 * MapStruct mapper for the SettingDefinition aggregate root entity.
 * All relational associations are flattened to ID+code projections in the response.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingDefinitionMapper {

    /**
     * Converts a SettingDefinition entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "category.id",     target = "categoryId")
    @Mapping(source = "category.code",   target = "categoryCode")
    @Mapping(source = "valueType.id",    target = "valueTypeId")
    @Mapping(source = "valueType.code",  target = "valueTypeCode")
    SettingDefinitionResponse toResponse(SettingDefinition entity);

    /**
     * Converts a list of SettingDefinition entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingDefinitionResponse> toResponseList(List<SettingDefinition> entities);

    /**
     * Converts a create request DTO to a new SettingDefinition entity.
     * Category and valueType associations are resolved and set by the service layer.
     *
     * @param request the create request DTO
     * @return the new entity (category, valueType, and collections not set; set by service)
     */
    @Mapping(target = "category",       ignore = true)
    @Mapping(target = "valueType",      ignore = true)
    @Mapping(target = "constraints",    ignore = true)
    @Mapping(target = "values",         ignore = true)
    @Mapping(target = "history",        ignore = true)
    @Mapping(target = "accessPolicies", ignore = true)
    SettingDefinition toEntity(SettingDefinitionCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingDefinition entity.
     * Category association is resolved by the service layer.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category",       ignore = true)
    @Mapping(target = "valueType",      ignore = true)
    @Mapping(target = "constraints",    ignore = true)
    @Mapping(target = "values",         ignore = true)
    @Mapping(target = "history",        ignore = true)
    @Mapping(target = "accessPolicies", ignore = true)
    void updateEntity(SettingDefinitionUpdateRequest request, @MappingTarget SettingDefinition entity);
}
