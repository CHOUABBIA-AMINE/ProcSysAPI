/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditActor
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Immutable snapshot of the actor who triggered a traceable audit event.
 * Captures the actor's identity as it existed at the moment of the action.
 * Does NOT own authorization rules, roles, or permissions — those belong to security.authorization.
 * This entity is append-only and must never be updated after persistence.
 */
@Schema(
    name = "AuditActor",
    description = "Immutable identity snapshot of the actor who triggered an auditable event"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditActor")
@Table(
    name = "T_00_03_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_03_05__ACTOR_REF", columnNames = {"F_01", "F_02"})
    },
    indexes = {
        @Index(name = "IDX__T_00_03_05__ACTOR_ID", columnList = "F_01"),
        @Index(name = "IDX__T_00_03_05__USERNAME", columnList = "F_03"),
        @Index(name = "IDX__T_00_03_05__SESSION", columnList = "F_06")
    }
)
public class AuditActor extends GenericModel {

    /**
     * External ID of the actor in the IAM/identity system.
     * This is a reference ID only; no FK to identity module.
     */
    @Schema(
        description = "External identity system reference ID of the actor",
        example = "1001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Column(name = "F_01", nullable = false)
    private Long actorReferenceId;

    /**
     * Type of actor (e.g. USER, SYSTEM, SERVICE_ACCOUNT, BATCH_JOB).
     * Stored as a plain string reference code; not an entity FK to keep audit self-contained.
     */
    @Schema(
        description = "Type classification of the actor",
        example = "USER",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Actor type is mandatory")
    @Size(max = 50, message = "Actor type must not exceed 50 characters")
    @Column(name = "F_02", length = 50, nullable = false)
    private String actorType;

    /**
     * Username snapshot at the time of the action.
     */
    @Schema(
        description = "Username of the actor at the time of the action",
        example = "achouabbia",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 150
    )
    @NotBlank(message = "Username is mandatory")
    @Size(max = 150, message = "Username must not exceed 150 characters")
    @Column(name = "F_03", length = 150, nullable = false)
    private String username;

    /**
     * Full display name of the actor at time of action.
     */
    @Schema(
        description = "Full display name of the actor",
        example = "Amine CHOUABBIA",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Full name must not exceed 300 characters")
    @Column(name = "F_04", length = 300)
    private String fullName;

    /**
     * IP address of the client at time of action.
     */
    @Schema(
        description = "IP address of the actor's client at time of action",
        example = "192.168.1.100",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 45
    )
    @Size(max = 45, message = "IP address must not exceed 45 characters")
    @Column(name = "F_05", length = 45)
    private String ipAddress;

    /**
     * Session identifier associated with the actor's authenticated session.
     */
    @Schema(
        description = "Session identifier of the actor at time of action",
        example = "sess-a3f2c9d1-...",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Session ID must not exceed 200 characters")
    @Column(name = "F_06", length = 200)
    private String sessionId;

    /**
     * User-agent string of the client at time of action.
     */
    @Schema(
        description = "User-agent string of the actor's client",
        example = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "User agent must not exceed 500 characters")
    @Column(name = "F_07", length = 500)
    private String userAgent;
}