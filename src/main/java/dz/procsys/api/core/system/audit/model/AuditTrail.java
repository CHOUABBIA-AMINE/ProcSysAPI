/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditTrail
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import java.util.Date;
import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
 * Groups a set of AuditRecords into a logical forensic reconstruction trail.
 * A trail represents a complete traceable lifecycle: a workflow execution,
 * a multi-step submission process, a configuration governance sequence, etc.
 * Immutable once sealed; append-only records are linked to the trail.
 */
@Schema(
    name = "AuditTrail",
    description = "Logical grouping of AuditRecords representing a complete traceable lifecycle sequence"
)
@Setter
@Getter
@ToString(exclude = {"records"})
@EqualsAndHashCode(callSuper = true, exclude = {"records"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditTrail")
@Table(
    name = "T_00_03_12",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_03_12__TRAIL_CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_03_12__CODE", columnList = "F_01"),
        @Index(name = "IDX__T_00_03_12__STATUS", columnList = "F_04"),
        @Index(name = "IDX__T_00_03_12__MODULE", columnList = "F_05"),
        @Index(name = "IDX__T_00_03_12__OPENED", columnList = "F_06")
    }
)
public class AuditTrail extends GenericModel {

    /**
     * Unique code identifying this audit trail.
     * Example: TRAIL-SETTING-CHANGE-2026-0001.
     */
    @Schema(
        description = "Unique code identifying this audit trail",
        example = "TRAIL-SETTING-CHANGE-2026-0001",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Trail code is mandatory")
    @Size(max = 200, message = "Trail code must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String trailCode;

    /**
     * Human-readable description of what this trail represents.
     */
    @Schema(
        description = "Description of what this audit trail represents",
        example = "Full lifecycle of setting auth.session.timeout governance",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 1000
    )
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "F_02", length = 1000)
    private String description;

    /**
     * Platform module that initiated this trail.
     */
    @Schema(
        description = "Platform module that initiated this trail",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Module is mandatory")
    @Size(max = 100, message = "Module must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String module;

    /**
     * Current status of the trail: OPEN, SEALED, ARCHIVED.
     */
    @Schema(
        description = "Current status of the audit trail",
        example = "OPEN",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Trail status is mandatory")
    @Size(max = 50, message = "Trail status must not exceed 50 characters")
    @Column(name = "F_04", length = 50, nullable = false)
    private String status;

    /**
     * Correlation ID representing the logical transaction this trail covers.
     */
    @Schema(
        description = "Correlation ID of the logical transaction this trail covers",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Correlation ID is mandatory")
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    @Column(name = "F_05", length = 200, nullable = false)
    private String correlationId;

    /**
     * Timestamp when this trail was opened.
     */
    @Schema(
        description = "Timestamp when this audit trail was opened",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Opened at timestamp is mandatory")
    @Column(name = "F_06", nullable = false)
    private Date openedAt;

    /**
     * Timestamp when this trail was sealed (null if still open).
     */
    @Schema(
        description = "Timestamp when this audit trail was sealed (null if still open)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_07")
    private Date sealedAt;

    /**
     * All AuditRecords belonging to this trail.
     */
    @Schema(
        description = "All audit records grouped under this trail",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "auditTrail", cascade = CascadeType.ALL)
    private List<AuditRecord> records;
}