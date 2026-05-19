/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditAction
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
 * Represents the specific action performed within an auditable event.
 * Combines an action type classification with contextual metadata at time of execution.
 * Immutable; never updated after persistence.
 */
@Schema(
    name = "AuditAction",
    description = "Record of the specific action performed in an auditable event, with contextual metadata"
)
@Setter
@Getter
@ToString(exclude = {"actionType"})
@EqualsAndHashCode(callSuper = true, exclude = {"actionType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditAction")
@Table(
    name = "T_00_03_07",
    indexes = {
        @Index(name = "IDX_T_00_03_07_01", columnList = "F_05"),
        @Index(name = "IDX_T_00_03_07_02", columnList = "F_01"),
        @Index(name = "IDX_T_00_03_07_03", columnList = "F_03")
    }
)
public class AuditAction extends GenericModel {

    /**
     * Execution status of the action: SUCCESS, FAILED, PARTIAL.
     */
    @Schema(
        description = "Execution status of the action",
        example = "SUCCESS",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Status is mandatory")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String status;

    /**
     * Duration of the action in milliseconds.
     */
    @Schema(
        description = "Duration of the action in milliseconds",
        example = "142",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_02")
    private Long durationMs;

    /**
     * Human-readable outcome description for compliance documentation.
     */
    @Schema(
        description = "Human-readable outcome description of the action",
        example = "Setting auth.session.timeout updated from 30 to 60",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 1000
    )
    @Size(max = 1000, message = "Outcome description must not exceed 1000 characters")
    @Column(name = "F_03", length = 1000)
    private String outcomeDescription;

    /**
     * Error message if the action failed.
     */
    @Schema(
        description = "Error message if the action execution failed",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04", columnDefinition = "TEXT")
    private String errorMessage;

    /**
     * Classification of the action performed (FK to AuditActionType).
     */
    @Schema(
        description = "Classification of the action performed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Action type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_03_07_FK_01"),
        nullable = false
    )
    private AuditActionType actionType;
}