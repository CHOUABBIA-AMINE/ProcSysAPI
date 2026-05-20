/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveFolderResponseDTO
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing an archive folder.
 */
@Schema(
    name = "ArchiveFolderResponseDTO",
    description = "Represents an archive folder stored inside an archive box"
)
@Setter
@Getter
@ToString
public class ArchiveFolderResponseDTO {

    /**
     * Technical identifier of the archive folder.
     */
    @Schema(
        description = "Technical identifier of the archive folder",
        example = "100"
    )
    private Long id;

    /**
     * Unique code of the archive folder.
     */
    @Schema(
        description = "Unique code of the archive folder",
        example = "FOLDER-2026-001"
    )
    private String code;

    /**
     * Optional description of the folder content.
     */
    @Schema(
        description = "Optional description of the folder content",
        example = "Tender dossier - Project XYZ"
    )
    private String description;

    /**
     * Identifier of the archive box where this folder is stored.
     */
    @Schema(
        description = "Identifier of the archive box where this folder is stored",
        example = "20"
    )
    private Long archiveBoxId;
}