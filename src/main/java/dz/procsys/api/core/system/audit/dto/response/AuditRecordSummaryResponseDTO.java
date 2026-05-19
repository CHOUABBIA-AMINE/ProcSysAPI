/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditRecordSummaryResponseDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.response;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing a summary view of an audit record for list and timeline screens.
 * Designed for Page<AuditRecordSummaryResponseDto> responses.
 */
@Schema(
    name = "AuditRecordSummaryResponseDto",
    description = "Summary view of an audit record for search and timeline listing"
)
@Setter
@Getter
@ToString
public class AuditRecordSummaryResponseDTO {

    @Schema(
        description = "Technical identifier of the audit record",
        example = "12345"
    )
    private Long id;

    @Schema(
        description = "Correlation ID of the logical transaction this record belongs to",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f"
    )
    private String correlationId;

    @Schema(
        description = "Event timestamp when the audited action occurred",
        example = "2026-05-19T16:00:00Z"
    )
    private Date eventTimestamp;

    @Schema(
        description = "Owning module of the targeted resource",
        example = "system.setting"
    )
    private String module;

    @Schema(
        description = "Resource identifier (e.g. setting code, submission ID)",
        example = "auth.session.timeout"
    )
    private String resourceIdentifier;

    @Schema(
        description = "Resource type code (e.g. SETTING_DEFINITION, USER_ACCOUNT)",
        example = "SETTING_DEFINITION"
    )
    private String resourceTypeCode;

    @Schema(
        description = "Username of the actor who performed the action",
        example = "achouabbia"
    )
    private String actorUsername;

    @Schema(
        description = "Action type code (e.g. CREATE, UPDATE, DELETE)",
        example = "UPDATE"
    )
    private String actionTypeCode;

    @Schema(
        description = "Execution status of the action (e.g. SUCCESS, FAILED, PARTIAL)",
        example = "SUCCESS"
    )
    private String status;

    @Schema(
        description = "Indicates whether any diff entries for this record contain masked fields",
        example = "false"
    )
    private Boolean hasMaskedFields;
}