/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditSearchRequestDTO
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
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for generic audit search with filtering and pagination.
 * This DTO does not expose any entity classes and is safe for external consumers.
 */
@Schema(
    name = "AuditSearchRequestDto",
    description = "Request parameters for generic audit search with filters and pagination"
)
@Setter
@Getter
@ToString
public class AuditSearchRequestDTO {

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
        description = "Platform module that emitted the audited event (e.g. system.setting, iam, workflow)",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Module must not exceed 100 characters")
    private String module;

    @Schema(
        description = "Resource type code to filter on (e.g. SETTING_DEFINITION, USER_ACCOUNT)",
        example = "SETTING_DEFINITION",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Resource type code must not exceed 100 characters")
    private String resourceTypeCode;

    @Schema(
        description = "Action type code to filter on (e.g. CREATE, UPDATE, DELETE)",
        example = "UPDATE",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Action type code must not exceed 100 characters")
    private String actionTypeCode;

    @Schema(
        description = "External actor reference ID to filter by",
        example = "1001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long actorReferenceId;

    @Schema(
        description = "Correlation ID to restrict search to a specific logical transaction chain",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    private String correlationId;

    @Schema(
        description = "Granularity type code to filter on (e.g. FIELD_LEVEL, ENTITY_LEVEL, EVENT_ONLY)",
        example = "FIELD_LEVEL",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Granularity code must not exceed 100 characters")
    private String granularityCode;

    @Schema(
        description = "Execution status to filter on (e.g. SUCCESS, FAILED, PARTIAL)",
        example = "SUCCESS",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status;

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
        example = "20",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        defaultValue = "20"
    )
    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 200, message = "Page size must not exceed 200")
    private Integer size = 20;
}