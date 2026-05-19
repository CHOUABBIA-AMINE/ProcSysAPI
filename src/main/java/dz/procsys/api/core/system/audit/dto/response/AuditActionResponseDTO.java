/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActionResponseDTO
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
 * Response DTO describing the action performed in an audit record.
 */
@Schema(
    name = "AuditActionResponseDto",
    description = "Action metadata associated with an audit record"
)
@Setter
@Getter
@ToString
public class AuditActionResponseDTO {

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
        description = "Duration of the action in milliseconds",
        example = "142"
    )
    private Long durationMs;

    @Schema(
        description = "Human-readable outcome description of the action",
        example = "Setting auth.session.timeout updated from 30 to 60"
    )
    private String outcomeDescription;

    @Schema(
        description = "Error message if the action execution failed"
    )
    private String errorMessage;
}