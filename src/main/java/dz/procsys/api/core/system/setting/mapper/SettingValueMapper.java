/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingValueCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueResponse;
import dz.procsys.api.core.system.setting.model.SettingValue;

/**
 * MapStruct mapper for the SettingValue entity.
 * The 'masked' field in the response is set by the service layer after encryption check.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingValueMapper {

    /**
     * Converts a SettingValue entity to its response DTO.
     * Note: the 'masked' flag is not set here — the service layer is responsible for masking.
     *
     * @param entity the source entity
     * @return the populated response DTO (value may still be raw; service applies masking)
     */
    @Mapping(source = "settingDefinition.id",   target = "settingDefinitionId")
    @Mapping(source = "settingDefinition.code", target = "settingDefinitionCode")
    @Mapping(source = "scopeType.id",           target = "scopeTypeId")
    @Mapping(source = "scopeType.code",         target = "scopeTypeCode")
    @Mapping(target = "masked",                 ignore = true)
    SettingValueResponse toResponse(SettingValue entity);

    /**
     * Converts a list of SettingValue entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingValueResponse> toResponseList(List<SettingValue> entities);

    /**
     * Converts a create request DTO to a new SettingValue entity.
     * Associations are resolved by the service layer.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "scopeType",         ignore = true)
    SettingValue toEntity(SettingValueCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingValue entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "settingDefinition", ignore = true)
    @Mapping(target = "scopeType",         ignore = true)
    void updateEntity(SettingValueUpdateRequest request, @MappingTarget SettingValue entity);
}
