/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveBoxMapper
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.mapper;

import dz.procsys.api.core.common.archive.dto.response.ArchiveBoxResponseDTO;
import dz.procsys.api.core.common.archive.model.ArchiveBox;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting ArchiveBox entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ArchiveBoxMapper {

    @Mapping(source = "shelf.id", target = "shelfId")
    ArchiveBoxResponseDTO toDTO(ArchiveBox entity);

    List<ArchiveBoxResponseDTO> toDTOList(List<ArchiveBox> entities);
}