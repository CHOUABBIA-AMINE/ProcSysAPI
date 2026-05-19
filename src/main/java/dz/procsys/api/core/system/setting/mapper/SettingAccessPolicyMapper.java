/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingAccessPolicyResponse;
import dz.procsys.api.core.system.setting.model.SettingAccessPolicy;

/**
 * MapStruct mapper for the SettingAccessPolicy entity.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingAccessPolicyMapper {

    /**
     * Converts a SettingAccessPolicy entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "settingDefinition.id",   target = "settingDefinitionId")
    @Mapping(source = "settingDefinition.code", target = "settingDefinitionCode")
    SettingAccessPolicyResponse toResponse(SettingAccessPolicy entity);

    /**
     * Converts a list of SettingAccessPolicy entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingAccessPolicyResponse> toResponseList(List<SettingAccessPolicy> entities);

    /**
     * Converts a create request DTO to a new SettingAccessPolicy entity.
     * The settingDefinition association is resolved by the service layer.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    @Mapping(target = "settingDefinition", ignore = true)
    SettingAccessPolicy toEntity(SettingAccessPolicyCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingAccessPolicy entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "roleCode",          ignore = true)
    void updateEntity(SettingAccessPolicyUpdateRequest request, @MappingTarget SettingAccessPolicy entity);
}
