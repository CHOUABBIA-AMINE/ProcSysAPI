/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveBoxResponseDTO
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
 * Response DTO representing an archive box.
 */
@Schema(
    name = "ArchiveBoxResponseDTO",
    description = "Represents an archive box placed on a shelf"
)
@Setter
@Getter
@ToString
public class ArchiveBoxResponseDTO {

    /**
     * Technical identifier of the archive box.
     */
    @Schema(
        description = "Technical identifier of the archive box",
        example = "20"
    )
    private Long id;

    /**
     * Unique code of the archive box.
     */
    @Schema(
        description = "Unique code of the archive box",
        example = "BOX-2026-001"
    )
    private String code;

    /**
     * Optional description of the archive box content.
     */
    @Schema(
        description = "Optional description of the archive box content",
        example = "Procurement contracts - FY 2026"
    )
    private String description;

    /**
     * Identifier of the shelf where this box is located.
     */
    @Schema(
        description = "Identifier of the shelf where this box is located",
        example = "5"
    )
    private Long shelfId;
}