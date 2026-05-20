/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : BuildingMapper
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.mapper;

import dz.procsys.api.core.common.infrastructure.dto.response.BuildingResponseDTO;
import dz.procsys.api.core.common.infrastructure.model.Building;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting Building entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BuildingMapper {

    BuildingResponseDTO toDTO(Building entity);

    List<BuildingResponseDTO> toDTOList(List<Building> entities);
}