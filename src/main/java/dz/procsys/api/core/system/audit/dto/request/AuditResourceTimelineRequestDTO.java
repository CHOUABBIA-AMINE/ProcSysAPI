/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditResourceTimelineRequestDTO
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for retrieving a chronological audit timeline for a specific resource.
 */
@Schema(
    name = "AuditResourceTimelineRequestDto",
    description = "Request parameters for retrieving a chronological audit timeline for a specific resource instance"
)
@Setter
@Getter
@ToString
public class AuditResourceTimelineRequestDTO {

    @Schema(
        description = "External resource reference ID whose timeline is requested",
        example = "2048",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Resource reference ID is mandatory")
    private Long resourceReferenceId;

    @Schema(
        description = "Owning platform module of the resource",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Module is mandatory")
    @Size(max = 100, message = "Module must not exceed 100 characters")
    private String module;

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