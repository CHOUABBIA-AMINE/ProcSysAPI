/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActorResponseDTO
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
 * Response DTO representing an immutable snapshot of the actor who performed an action.
 */
@Schema(
    name = "AuditActorResponseDto",
    description = "Immutable actor snapshot associated with an audit record"
)
@Setter
@Getter
@ToString
public class AuditActorResponseDTO {

    @Schema(
        description = "External identity system reference ID of the actor",
        example = "1001"
    )
    private Long actorReferenceId;

    @Schema(
        description = "Type classification of the actor (e.g. USER, SYSTEM, SERVICE_ACCOUNT)",
        example = "USER"
    )
    private String actorType;

    @Schema(
        description = "Username of the actor at the time of the action",
        example = "achouabbia"
    )
    private String username;

    @Schema(
        description = "Full display name of the actor",
        example = "Amine CHOUABBIA"
    )
    private String fullName;

    @Schema(
        description = "IP address of the actor's client at time of action",
        example = "192.168.1.100"
    )
    private String ipAddress;

    @Schema(
        description = "Session identifier of the actor at time of action",
        example = "sess-a3f2c9d1-..."
    )
    private String sessionId;

    @Schema(
        description = "User-agent string of the actor's client",
        example = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
    )
    private String userAgent;
}