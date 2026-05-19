/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditTimelineItemResponseDTO
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
 * Response DTO representing a single timeline entry for actor or resource timelines.
 */
@Schema(
    name = "AuditTimelineItemResponseDto",
    description = "Single timeline item representing one audited event for actor or resource timelines"
)
@Setter
@Getter
@ToString
public class AuditTimelineItemResponseDTO {

    @Schema(
        description = "Technical identifier of the underlying audit record",
        example = "12345"
    )
    private Long recordId;

    @Schema(
        description = "Event timestamp when the audited action occurred",
        example = "2026-05-19T16:00:00Z"
    )
    private Date eventTimestamp;

    @Schema(
        description = "Action type code (e.g. CREATE, UPDATE, DELETE)",
        example = "UPDATE"
    )
    private String actionTypeCode;

    @Schema(
        description = "Execution status of the action",
        example = "SUCCESS"
    )
    private String status;

    @Schema(
        description = "Resource identifier (e.g. setting code, submission ID)",
        example = "auth.session.timeout"
    )
    private String resourceIdentifier;

    @Schema(
        description = "Resource type code",
        example = "SETTING_DEFINITION"
    )
    private String resourceTypeCode;

    @Schema(
        description = "Short textual description of the outcome for timeline visualization",
        example = "Changed defaultValue from 30 to 60"
    )
    private String shortOutcomeDescription;
}