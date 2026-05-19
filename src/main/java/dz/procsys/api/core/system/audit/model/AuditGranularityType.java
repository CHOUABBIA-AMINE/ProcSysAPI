/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditGranularityType
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
 * Reference entity representing the granularity level at which an audit record captures information.
 * Possible levels: FIELD_LEVEL, ENTITY_LEVEL, EVENT_ONLY, SECURITY_TRACE_ONLY.
 * Configurable master data, not hardcoded.
 */
@Schema(
    name = "AuditGranularityType",
    description = "Reference entity defining the granularity level of an audit record: FIELD_LEVEL, ENTITY_LEVEL, EVENT_ONLY, SECURITY_TRACE_ONLY"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditGranularityType")
@Table(
    name = "T_00_03_04",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_03_04__CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_03_04__CODE", columnList = "F_01")
    }
)
public class AuditGranularityType extends GenericModel {

    /**
     * Unique technical code for the granularity level.
     * Example: FIELD_LEVEL, ENTITY_LEVEL, EVENT_ONLY, SECURITY_TRACE_ONLY.
     */
    @Schema(
        description = "Unique technical code of the granularity level",
        example = "FIELD_LEVEL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    /**
     * Human-readable label for the granularity level.
     */
    @Schema(
        description = "Display label of the granularity type",
        example = "Field Level",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Label is mandatory")
    @Size(max = 200, message = "Label must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String label;

    /**
     * Description explaining the traceability depth this granularity provides.
     */
    @Schema(
        description = "Description of what this granularity level captures",
        example = "Captures individual field-level diffs between before and after states",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String description;

    /**
     * Flag indicating if diff computation is required at this granularity level.
     */
    @Schema(
        description = "Indicates if diff computation is required at this granularity",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "DiffRequired flag is mandatory")
    @Column(name = "F_04", nullable = false)
    private Boolean diffRequired;
}