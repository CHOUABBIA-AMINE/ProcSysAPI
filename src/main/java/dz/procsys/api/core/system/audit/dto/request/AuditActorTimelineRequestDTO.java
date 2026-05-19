/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActorTimelineRequestDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Request
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.request;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for retrieving a chronological audit timeline for a specific actor.
 */
@Schema(
    name = "AuditActorTimelineRequestDto",
    description = "Request parameters for retrieving a chronological audit timeline for a specific actor"
)
@Setter
@Getter
@ToString
public class AuditActorTimelineRequestDTO {

    @Schema(
        description = "External actor reference ID whose timeline is requested",
        example = "1001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Actor reference ID is mandatory")
    private Long actorReferenceId;

    @Schema(
        description = "Start of the event timestamp range (inclusive)",
        example = "2026-05-01T00:00:00Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Date fromTimestamp;

    @Schema(
        description = "End of the event timestamp range (inclusive)",
        example = "2026-05-31T23:59:59Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Date toTimestamp;

    @Schema(
        description = "Page number (zero-based) for pagination",
        example = "0",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        defaultValue = "0"
    )
    @Min(value = 0, message = "Page index must be zero or positive")
    private Integer page = 0;

    @Schema(
        description = "Page size for pagination",
        example = "50",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        defaultValue = "50"
    )
    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 200, message = "Page size must not exceed 200")
    private Integer size = 50;
}