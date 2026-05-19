/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditDiff
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
 * Represents a field-level or entity-level difference recorded for an auditable state change.
 * Supports FIELD_LEVEL (per-field tracking), ENTITY_LEVEL (full JSON snapshot), and hybrid modes.
 * Linked to an AuditRecord for forensic reconstruction of state transitions.
 * Immutable; never updated after persistence.
 */
@Schema(
    name = "AuditDiff",
    description = "Field-level or entity-level diff record capturing state transition between before and after states"
)
@Setter
@Getter
@ToString(exclude = {"auditRecord"})
@EqualsAndHashCode(callSuper = true, exclude = {"auditRecord"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditDiff")
@Table(
    name = "T_00_04_09",
    indexes = {
        @Index(name = "IDX__T_00_04_09__RECORD", columnList = "F_01"),
        @Index(name = "IDX__T_00_04_09__FIELD", columnList = "F_02"),
        @Index(name = "IDX__T_00_04_09__MASKED", columnList = "F_07")
    }
)
public class AuditDiff extends GenericModel {

    /**
     * The parent AuditRecord this diff belongs to.
     */
    @Schema(
        description = "The parent audit record this diff is associated with",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Audit record reference is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_09__AUDIT_RECORD"),
        nullable = false
    )
    private AuditRecord auditRecord;

    /**
     * Name of the field that changed. Null if this is an entity-level diff.
     */
    @Schema(
        description = "Name of the field that changed (null for entity-level diff)",
        example = "defaultValue",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Field name must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String fieldName;

    /**
     * String representation of the field value before the change.
     */
    @Schema(
        description = "String representation of the field value before the change",
        example = "30",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03", columnDefinition = "TEXT")
    private String beforeValue;

    /**
     * String representation of the field value after the change.
     */
    @Schema(
        description = "String representation of the field value after the change",
        example = "60",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04", columnDefinition = "TEXT")
    private String afterValue;

    /**
     * JSON-formatted diff representation for hybrid and entity-level diffs.
     */
    @Schema(
        description = "JSON diff representation for hybrid or entity-level diffs",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_05", columnDefinition = "TEXT")
    private String jsonDiff;

    /**
     * Diff granularity mode: FIELD_LEVEL, ENTITY_LEVEL, or HYBRID.
     */
    @Schema(
        description = "Granularity mode of this diff entry",
        example = "FIELD_LEVEL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Diff mode is mandatory")
    @Size(max = 50, message = "Diff mode must not exceed 50 characters")
    @Column(name = "F_06", length = 50, nullable = false)
    private String diffMode;

    /**
     * Indicates if this diff field contains sensitive data that should be masked in responses.
     */
    @Schema(
        description = "Indicates if this diff field contains sensitive/masked data",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Masked flag is mandatory")
    @Column(name = "F_07", nullable = false)
    private Boolean isMasked;
}