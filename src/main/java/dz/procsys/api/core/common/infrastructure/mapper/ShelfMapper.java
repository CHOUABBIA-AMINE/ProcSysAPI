/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ShelfMapper
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.mapper;

import dz.procsys.api.core.common.infrastructure.dto.response.ShelfResponseDTO;
import dz.procsys.api.core.common.infrastructure.model.Shelf;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting Shelf entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ShelfMapper {

    @Mapping(source = "room.id", target = "roomId")
    ShelfResponseDTO toDTO(Shelf entity);

    List<ShelfResponseDTO> toDTOList(List<Shelf> entities);
}