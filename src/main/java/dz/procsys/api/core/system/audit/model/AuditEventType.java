/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditEventType
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
 * Reference entity representing the classification of an audit domain event.
 * Examples: SETTING_CHANGED, USER_AUTHENTICATED, WORKFLOW_TRANSITIONED.
 * This is a configurable master data entity; no enums are used.
 * Immutable after initial seeding in production environments.
 */
@Schema(
    name = "AuditEventType",
    description = "Reference classification of an audit domain event (e.g. SETTING_CHANGED, USER_AUTHENTICATED)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditEventType")
@Table(
    name = "T_00_03_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_03_01__CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_03_01__CODE", columnList = "F_01"),
        @Index(name = "IDX__T_00_03_01__ACTIVE", columnList = "F_02")
    }
)
public class AuditEventType extends GenericModel {

    /**
     * Unique technical code identifying the event type.
     * Example: SETTING_CHANGED, IAM_ROLE_ASSIGNED, WORKFLOW_STEP_EXECUTED.
     */
    @Schema(
        description = "Unique technical code of the audit event type",
        example = "SETTING_CHANGED",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    /**
     * Human-readable label for display in audit consoles.
     */
    @Schema(
        description = "Display label of the event type",
        example = "Setting Changed",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Label is mandatory")
    @Size(max = 200, message = "Label must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String label;

    /**
     * Description of what this event type represents in the traceability model.
     */
    @Schema(
        description = "Description of the event type",
        example = "Fired when a system setting value is changed",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String description;

    /**
     * Indicates whether this event type is currently active in the audit pipeline.
     */
    @Schema(
        description = "Indicates if this event type is active",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Column(name = "F_04", nullable = false)
    private Boolean isActive;
}