/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditResource
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
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Immutable snapshot of the domain resource targeted by an auditable action.
 * Captures the resource identity and type at the time of the event.
 * Does NOT own the resource itself; it only records traceability metadata.
 * Append-only; never updated after persistence.
 */
@Schema(
    name = "AuditResource",
    description = "Immutable snapshot of the resource targeted by an auditable event"
)
@Setter
@Getter
@ToString(exclude = {"resourceType"})
@EqualsAndHashCode(callSuper = true, exclude = {"resourceType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditResource")
@Table(
    name = "T_00_04_06",
    indexes = {
        @Index(name = "IDX__T_00_04_06__RESOURCE_REF", columnList = "F_01, F_03"),
        @Index(name = "IDX__T_00_04_06__TYPE", columnList = "F_03"),
        @Index(name = "IDX__T_00_04_06__MODULE", columnList = "F_04")
    }
)
public class AuditResource extends GenericModel {

    /**
     * External ID of the targeted resource in its origin domain.
     * Reference by ID only; no FK to the origin module.
     */
    @Schema(
        description = "ID of the targeted resource in its origin domain",
        example = "2048",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Resource reference ID is mandatory")
    @Column(name = "F_01", nullable = false)
    private Long resourceReferenceId;

    /**
     * Human-readable identifier of the resource (e.g. setting code, username).
     */
    @Schema(
        description = "Human-readable identifier of the resource at time of event",
        example = "auth.session.timeout",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Resource identifier must not exceed 300 characters")
    @Column(name = "F_02", length = 300)
    private String resourceIdentifier;

    /**
     * Classification of the resource type (FK to AuditResourceType).
     */
    @Schema(
        description = "Classification of the resource type",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Resource type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_06__RESOURCE_TYPE"),
        nullable = false
    )
    private AuditResourceType resourceType;

    /**
     * Platform module that owns this resource (e.g. system.setting, iam, workflow).
     */
    @Schema(
        description = "Platform module that owns this resource",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Module is mandatory")
    @Size(max = 100, message = "Module must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String module;

    /**
     * Display label of the resource at time of the event.
     */
    @Schema(
        description = "Display label of the resource at time of event",
        example = "Session Timeout (Minutes)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Resource label must not exceed 300 characters")
    @Column(name = "F_05", length = 300)
    private String resourceLabel;
}