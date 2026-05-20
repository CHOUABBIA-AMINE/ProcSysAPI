/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : RoomMapper
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.mapper;

import dz.procsys.api.core.common.infrastructure.dto.response.RoomResponseDTO;
import dz.procsys.api.core.common.infrastructure.model.Room;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting Room entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RoomMapper {

    @Mapping(source = "building.id", target = "buildingId")
    RoomResponseDTO toDTO(Room entity);

    List<RoomResponseDTO> toDTOList(List<Room> entities);
}