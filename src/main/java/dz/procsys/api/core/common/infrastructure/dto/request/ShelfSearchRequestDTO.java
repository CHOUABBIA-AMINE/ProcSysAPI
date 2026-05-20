/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ShelfSearchRequestDTO
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Request
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for searching shelves.
 */
@Schema(
    name = "ShelfSearchRequestDTO",
    description = "Request parameters for searching shelves by room and code filters"
)
@Setter
@Getter
@ToString
public class ShelfSearchRequestDTO {

    /**
     * Room identifier to which shelves belong.
     */
    @Schema(
        description = "Room identifier to which shelves belong",
        example = "10"
    )
    @NotNull(message = "Room id is mandatory")
    private Long roomId;

    /**
     * Filter by shelf code (contains, case-insensitive).
     */
    @Schema(
        description = "Filter by shelf code (contains, case-insensitive)",
        example = "S",
        maxLength = 20
    )
    @Size(max = 20, message = "Shelf code filter must not exceed 20 characters")
    private String codeContains;

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