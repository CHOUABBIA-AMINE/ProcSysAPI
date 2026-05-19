/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingCategoryMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingCategoryCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingCategoryUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingCategoryResponse;
import dz.procsys.api.core.system.setting.model.SettingCategory;

/**
 * MapStruct mapper for the SettingCategory entity.
 * Parent category is flattened to ID + code for the response to avoid circular nesting.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingCategoryMapper {

    /**
     * Converts a SettingCategory entity to its response DTO.
     * Parent association is projected to parentCategoryId and parentCategoryCode.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    @Mapping(source = "parentCategory.id",   target = "parentCategoryId")
    @Mapping(source = "parentCategory.code", target = "parentCategoryCode")
    SettingCategoryResponse toResponse(SettingCategory entity);

    /**
     * Converts a list of SettingCategory entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingCategoryResponse> toResponseList(List<SettingCategory> entities);

    /**
     * Converts a create request DTO to a new SettingCategory entity.
     * The parentCategory association is resolved by the service layer using parentCategoryId.
     *
     * @param request the create request DTO
     * @return the new entity (parentCategory not set; set by service)
     */
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories",  ignore = true)
    @Mapping(target = "settings",       ignore = true)
    SettingCategory toEntity(SettingCategoryCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingCategory entity.
     * The parentCategory association is handled by the service layer.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories",  ignore = true)
    @Mapping(target = "settings",       ignore = true)
    void updateEntity(SettingCategoryUpdateRequest request, @MappingTarget SettingCategory entity);
}
