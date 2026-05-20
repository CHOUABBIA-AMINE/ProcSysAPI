/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveFolderMapper
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.mapper;

import dz.procsys.api.core.common.archive.dto.response.ArchiveFolderResponseDTO;
import dz.procsys.api.core.common.archive.model.ArchiveFolder;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting ArchiveFolder entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ArchiveFolderMapper {

    @Mapping(source = "archiveBox.id", target = "archiveBoxId")
    ArchiveFolderResponseDTO toDTO(ArchiveFolder entity);

    List<ArchiveFolderResponseDTO> toDTOList(List<ArchiveFolder> entities);
}