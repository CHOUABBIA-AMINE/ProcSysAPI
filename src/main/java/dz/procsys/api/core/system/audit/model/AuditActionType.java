/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditActionType
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
 * Reference entity representing the type of action performed and recorded in an audit record.
 * Examples: CREATE, UPDATE, DELETE, APPROVE, REJECT, AUTHENTICATE, REVOKE.
 * Configurable master data; not hardcoded.
 */
@Schema(
    name = "AuditActionType",
    description = "Reference classification of the action performed in an audited operation (e.g. CREATE, UPDATE)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditActionType")
@Table(
    name = "T_00_04_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_04_02__CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_04_02__CODE", columnList = "F_01"),
        @Index(name = "IDX__T_00_04_02__ACTIVE", columnList = "F_04")
    }
)
public class AuditActionType extends GenericModel {

    /**
     * Unique technical code for the action type.
     * Example values: CREATE, UPDATE, DELETE, APPROVE, REVOKE.
     */
    @Schema(
        description = "Unique technical code of the action type",
        example = "UPDATE",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    /**
     * Human-readable label for audit search and display.
     */
    @Schema(
        description = "Display label for this action type",
        example = "Update",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Label is mandatory")
    @Size(max = 200, message = "Label must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String label;

    /**
     * Detailed description for compliance documentation.
     */
    @Schema(
        description = "Description of the action type",
        example = "Represents a modification to an existing resource",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String description;

    /**
     * Indicates whether this action type is currently in active use.
     */
    @Schema(
        description = "Indicates if this action type is active",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Column(name = "F_04", nullable = false)
    private Boolean isActive;
}