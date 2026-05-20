/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : BuildingSearchRequestDTO
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
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for searching buildings.
 */
@Schema(
    name = "BuildingSearchRequestDTO",
    description = "Request parameters for searching buildings using French designation and pagination"
)
@Setter
@Getter
@ToString
public class BuildingSearchRequestDTO {

    /**
     * Filter by French designation (contains, case-insensitive).
     */
    @Schema(
        description = "Filter by French designation (contains, case-insensitive)",
        example = "Bloc",
        maxLength = 100
    )
    @Size(max = 100, message = "Filter must not exceed 100 characters")
    private String designationFrContains;

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