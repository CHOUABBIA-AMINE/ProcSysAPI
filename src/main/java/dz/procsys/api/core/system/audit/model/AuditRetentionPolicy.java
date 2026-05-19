/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditRetentionPolicy
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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Defines a configurable retention policy governing how long audit records of a given
 * resource type and action type are kept before archival or purge.
 * Retention policies are data-driven and configurable; never hardcoded.
 * Does NOT own security decisions about who can access archived data.
 */
@Schema(
    name = "AuditRetentionPolicy",
    description = "Configurable policy defining the retention duration and archival strategy for audit records"
)
@Setter
@Getter
@ToString(exclude = {"resourceType", "actionType"})
@EqualsAndHashCode(callSuper = true, exclude = {"resourceType", "actionType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditRetentionPolicy")
@Table(
    name = "T_00_04_13",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_04_13__CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_04_13__RESOURCE_TYPE", columnList = "F_04"),
        @Index(name = "IDX__T_00_04_13__ACTION_TYPE", columnList = "F_05"),
        @Index(name = "IDX__T_00_04_13__ACTIVE", columnList = "F_07")
    }
)
public class AuditRetentionPolicy extends GenericModel {

    /**
     * Unique code of this retention policy.
     */
    @Schema(
        description = "Unique code of this retention policy",
        example = "POLICY-SETTING-CHANGE-10Y",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    /**
     * Human-readable name of this retention policy.
     */
    @Schema(
        description = "Human-readable name of this retention policy",
        example = "Setting Change 10-Year Retention",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 300
    )
    @NotBlank(message = "Name is mandatory")
    @Size(max = 300, message = "Name must not exceed 300 characters")
    @Column(name = "F_02", length = 300, nullable = false)
    private String name;

    /**
     * Description of the compliance rationale for this retention policy.
     */
    @Schema(
        description = "Compliance rationale and description",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 1000
    )
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "F_03", length = 1000)
    private String description;

    /**
     * Resource type this policy applies to.
     */
    @Schema(
        description = "Resource type this policy applies to",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_13__RESOURCE_TYPE")
    )
    private AuditResourceType resourceType;

    /**
     * Action type this policy applies to (null = applies to all action types).
     */
    @Schema(
        description = "Action type this policy applies to (null means all action types)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_13__ACTION_TYPE")
    )
    private AuditActionType actionType;

    /**
     * Retention duration in days before archival is triggered.
     */
    @Schema(
        description = "Retention duration in days before archival",
        example = "3650",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Retention days is mandatory")
    @Positive(message = "Retention days must be positive")
    @Column(name = "F_06", nullable = false)
    private Integer retentionDays;

    /**
     * Indicates whether this retention policy is currently active.
     */
    @Schema(
        description = "Indicates if this retention policy is active",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Active flag is mandatory")
    @Column(name = "F_07", nullable = false)
    private Boolean isActive;
}