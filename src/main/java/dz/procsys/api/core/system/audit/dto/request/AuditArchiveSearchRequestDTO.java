/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveSearchRequestDTO
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
 * Request DTO for searching audit archive batches with filtering and pagination.
 */
@Schema(
    name = "AuditArchiveSearchRequestDto",
    description = "Request parameters for searching audit archive batches"
)
@Setter
@Getter
@ToString
public class AuditArchiveSearchRequestDTO {

    @Schema(
        description = "Archive status to filter on (e.g. COMPLETED, FAILED, PENDING_VERIFICATION)",
        example = "COMPLETED",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status;

    @Schema(
        description = "Start of the coverage date range (earliest audit record included)",
        example = "2026-01-01T00:00:00Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Date fromCoverageDate;

    @Schema(
        description = "End of the coverage date range (latest audit record included)",
        example = "2026-12-31T23:59:59Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Date toCoverageDate;

    @Schema(
        description = "Retention policy code that triggered the archival",
        example = "POLICY-SETTING-CHANGE-10Y",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Retention policy code must not exceed 100 characters")
    private String retentionPolicyCode;

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