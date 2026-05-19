/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditResourceType
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
 * Reference entity classifying the type of domain resource targeted by an audited action.
 * Examples: SETTING, USER, ROLE, WORKFLOW_INSTANCE, SUBMISSION, DOCUMENT.
 * Configurable and data-driven; never hardcoded.
 */
@Schema(
    name = "AuditResourceType",
    description = "Reference classification of the resource type targeted in an audit record (e.g. SETTING, USER, WORKFLOW)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditResourceType")
@Table(
    name = "T_00_04_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_04_03__CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_04_03__CODE", columnList = "F_01"),
        @Index(name = "IDX__T_00_04_03__MODULE", columnList = "F_04")
    }
)
public class AuditResourceType extends GenericModel {

    /**
     * Unique technical code identifying the resource type.
     * Example: SETTING_DEFINITION, USER_ACCOUNT, WORKFLOW_INSTANCE.
     */
    @Schema(
        description = "Unique technical code of the resource type",
        example = "SETTING_DEFINITION",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    /**
     * Display label of the resource type for audit consoles.
     */
    @Schema(
        description = "Display label of the resource type",
        example = "Setting Definition",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Label is mandatory")
    @Size(max = 200, message = "Label must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String label;

    /**
     * Description of the resource type for compliance documentation.
     */
    @Schema(
        description = "Description of what this resource type represents",
        example = "Represents a configuration setting definition in the system",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String description;

    /**
     * Platform module this resource type belongs to (e.g. system.setting, iam, workflow).
     */
    @Schema(
        description = "Platform module owning this resource type",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Module must not exceed 100 characters")
    @Column(name = "F_04", length = 100)
    private String module;

    /**
     * Indicates whether this resource type is active in the audit system.
     */
    @Schema(
        description = "Indicates if this resource type is active",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Column(name = "F_05", nullable = false)
    private Boolean isActive;
}