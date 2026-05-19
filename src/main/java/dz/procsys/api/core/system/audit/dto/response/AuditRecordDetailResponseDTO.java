/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditRecordDetailResponseDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.response;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing a full forensic view of a single audit record,
 * including actor, resource, action, context, diffs and hash chain metadata.
 */
@Schema(
    name = "AuditRecordDetailResponseDTO",
    description = "Full forensic view of a single audit record, including diffs and hash chain metadata"
)
@Setter
@Getter
@ToString
public class AuditRecordDetailResponseDTO {

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
        description = "Granularity level applied to this audit record",
        example = "FIELD_LEVEL"
    )
    private String granularityCode;

    @Schema(
        description = "JSON snapshot of the entity state before the action (may be partially masked)",
        example = "{\"code\":\"auth.session.timeout\",\"defaultValue\":30}"
    )
    private String beforeStateJson;

    @Schema(
        description = "JSON snapshot of the entity state after the action (may be partially masked)",
        example = "{\"code\":\"auth.session.timeout\",\"defaultValue\":60}"
    )
    private String afterStateJson;

    @Schema(
        description = "SHA-256 hash chain value of this audit record",
        example = "a3f2c9d1e8b4..."
    )
    private String hashChainValue;

    @Schema(
        description = "Hash chain value of the previous audit record in the chain (if any)",
        example = "8e2a1c9b..."
    )
    private String previousHashValue;

    @Schema(
        description = "Actor snapshot associated with this record"
    )
    private AuditActorResponseDTO actor;

    @Schema(
        description = "Resource snapshot associated with this record"
    )
    private AuditResourceResponseDTO resource;

    @Schema(
        description = "Action metadata associated with this record"
    )
    private AuditActionResponseDTO action;

    @Schema(
        description = "Execution context associated with this record"
    )
    private AuditContextResponseDTO context;

    @Schema(
        description = "List of diff entries describing the state changes"
    )
    private List<AuditDiffResponseDTO> diffs;
}