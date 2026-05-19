/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ConstraintTypeMapper
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

import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.ConstraintTypeResponse;
import dz.procsys.api.core.system.setting.model.ConstraintType;

/**
 * MapStruct mapper for the ConstraintType entity.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ConstraintTypeMapper {

    /**
     * Converts a ConstraintType entity to its response DTO.
     *
     * @param entity the source entity
     * @return the populated response DTO
     */
    ConstraintTypeResponse toResponse(ConstraintType entity);

    /**
     * Converts a list of ConstraintType entities to a list of response DTOs.
     *
     * @param entities the source entity list
     * @return list of response DTOs
     */
    List<ConstraintTypeResponse> toResponseList(List<ConstraintType> entities);

    /**
     * Converts a create request DTO to a new ConstraintType entity.
     *
     * @param request the create request DTO
     * @return the new entity
     */
    ConstraintType toEntity(ConstraintTypeCreateRequest request);

    /**
     * Applies changes from an update request DTO to an existing ConstraintType entity.
     *
     * @param request the update request DTO
     * @param entity  the target entity to update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ConstraintTypeUpdateRequest request, @MappingTarget ConstraintType entity);
}
