/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditDiffResponseDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing a single diff entry for an audit record.
 * Designed to be traceability-safe, with explicit masking flag for sensitive fields.
 */
@Schema(
    name = "AuditDiffResponseDto",
    description = "Diff entry capturing changes between before and after states for an audit record"
)
@Setter
@Getter
@ToString
public class AuditDiffResponseDTO {

    @Schema(
        description = "Name of the field that changed (null for entity-level diff)",
        example = "defaultValue"
    )
    private String fieldName;

    @Schema(
        description = "String representation of the field value before the change (masked if sensitive)",
        example = "30"
    )
    private String beforeValue;

    @Schema(
        description = "String representation of the field value after the change (masked if sensitive)",
        example = "60"
    )
    private String afterValue;

    @Schema(
        description = "JSON diff representation for hybrid or entity-level diffs",
        example = "{\"defaultValue\":{\"from\":30,\"to\":60}}"
    )
    private String jsonDiff;

    @Schema(
        description = "Granularity mode of this diff entry (e.g. FIELD_LEVEL, ENTITY_LEVEL, HYBRID)",
        example = "FIELD_LEVEL"
    )
    private String diffMode;

    @Schema(
        description = "Indicates if this diff entry is masked due to sensitivity",
        example = "false"
    )
    private Boolean masked;
}