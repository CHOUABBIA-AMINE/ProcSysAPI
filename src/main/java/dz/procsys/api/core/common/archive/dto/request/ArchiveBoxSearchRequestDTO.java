/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveBoxSearchRequestDTO
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Request
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for searching archive boxes.
 */
@Schema(
    name = "ArchiveBoxSearchRequestDTO",
    description = "Request parameters for searching archive boxes by code and shelf"
)
@Setter
@Getter
@ToString
public class ArchiveBoxSearchRequestDTO {

    /**
     * Filter by archive box code (contains, case-insensitive).
     */
    @Schema(
        description = "Filter by archive box code (contains, case-insensitive)",
        example = "BOX-2026",
        maxLength = 50
    )
    @Size(max = 50, message = "Archive box code filter must not exceed 50 characters")
    private String codeContains;

    /**
     * Shelf identifier where boxes are located.
     */
    @Schema(
        description = "Shelf identifier where boxes are located",
        example = "5"
    )
    private Long shelfId;

    /**
     * Page number (zero-based).
     */
    @Schema(
        description = "Page number (zero-based)",
        example = "0",
        defaultValue = "0"
    )
    @Min(value = 0, message = "Page index must be zero or positive")
    private Integer page = 0;

    /**
     * Page size.
     */
    @Schema(
        description = "Page size",
        example = "20",
        defaultValue = "20"
    )
    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 200, message = "Page size must not exceed 200")
    private Integer size = 20;
}