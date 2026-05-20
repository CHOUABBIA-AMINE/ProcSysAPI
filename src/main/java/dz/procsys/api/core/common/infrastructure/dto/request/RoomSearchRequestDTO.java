/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : RoomSearchRequestDTO
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
 * Request DTO for searching rooms.
 */
@Schema(
    name = "RoomSearchRequestDTO",
    description = "Request parameters for searching rooms by building and code filters"
)
@Setter
@Getter
@ToString
public class RoomSearchRequestDTO {

    /**
     * Building identifier to which rooms belong.
     */
    @Schema(
        description = "Building identifier to which rooms belong",
        example = "1"
    )
    @NotNull(message = "Building id is mandatory")
    private Long buildingId;

    /**
     * Filter by room code (contains, case-insensitive).
     */
    @Schema(
        description = "Filter by room code (contains, case-insensitive)",
        example = "A",
        maxLength = 20
    )
    @Size(max = 20, message = "Room code filter must not exceed 20 characters")
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