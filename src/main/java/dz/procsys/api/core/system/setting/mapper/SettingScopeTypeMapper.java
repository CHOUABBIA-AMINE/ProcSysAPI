/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingScopeTypeMapper
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

import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingScopeTypeResponse;
import dz.procsys.api.core.system.setting.model.SettingScopeType;

/**
 * MapStruct mapper for the SettingScopeType entity.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SettingScopeTypeMapper {

    /**
     * Converts a SettingScopeType entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    SettingScopeTypeResponse toResponse(SettingScopeType entity);

    /**
     * Converts a list of SettingScopeType entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<SettingScopeTypeResponse> toResponseList(List<SettingScopeType> entities);

    /**
     * Converts a create request DTO to a new SettingScopeType entity.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    SettingScopeType toEntity(SettingScopeTypeCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing SettingScopeType entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(SettingScopeTypeUpdateRequest request, @MappingTarget SettingScopeType entity);
}
