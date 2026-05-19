/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditResourceResponseDTO
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
 * Response DTO representing an immutable snapshot of the resource targeted by an audit record.
 */
@Schema(
    name = "AuditResourceResponseDto",
    description = "Immutable resource snapshot associated with an audit record"
)
@Setter
@Getter
@ToString
public class AuditResourceResponseDTO {

    @Schema(
        description = "External ID of the targeted resource in its origin domain",
        example = "2048"
    )
    private Long resourceReferenceId;

    @Schema(
        description = "Human-readable identifier of the resource (e.g. setting code, submission ID)",
        example = "auth.session.timeout"
    )
    private String resourceIdentifier;

    @Schema(
        description = "Resource type code (e.g. SETTING_DEFINITION, USER_ACCOUNT)",
        example = "SETTING_DEFINITION"
    )
    private String resourceTypeCode;

    @Schema(
        description = "Platform module that owns this resource",
        example = "system.setting"
    )
    private String module;

    @Schema(
        description = "Display label of the resource at time of the event",
        example = "Session Timeout (Minutes)"
    )
    private String resourceLabel;
}