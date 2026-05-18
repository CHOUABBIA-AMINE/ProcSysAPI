/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowPermission
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Security
 *
 *	Description : Fine-grained permission binding a Role to a specific
 *	              workflow action (actionCode) within a WorkflowProcessType scope
 *	              and optionally a specific WorkflowStepDefinition.
 *	              Used by the WorkflowEngine to validate that an actor may
 *	              execute a given action on a given step before advancing the instance.
 *
 **/

package dz.procsys.api.core.workflow.security.model;

import dz.procsys.api.core.system.security.model.Role;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowPermission — fine-grained action permission for a Role within a workflow step.
 *
 * Database table : T_03_06_01
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : role                  — FK → T_00_02_03 (Role, LAZY)
 *  - F_02 : processTypeCode       — code of the WorkflowProcessType this permission applies to
 *  - F_03 : stepDefinitionCode    — code of the WorkflowStepDefinition; null = all steps
 *  - F_04 : actionCode            — action code permitted (e.g. APPROVE, REJECT, SUBMIT)
 *  - F_05 : active                — whether this permission is currently enforced
 *  - F_06 : description           — optional human-readable explanation
 */
@Setter
@Getter
@ToString(exclude = {"role"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowPermission")
@Table(
    name = "T_03_06_01",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "T_03_06_01_UK_01",
            columnNames = {"F_01", "F_02", "F_03", "F_04"}
        )
    }
)
@Schema(name = "WorkflowPermission",
        description = "Fine-grained permission binding a Role to a workflow action within a process type and step scope")
public class WorkflowPermission extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_06_01_FK_01"),
        nullable = false
    )
    @Schema(description = "Role that holds this workflow permission; loaded LAZY")
    private Role role;

    @Column(name = "F_02", length = 60, nullable = false)
    @Schema(description = "Code of the WorkflowProcessType this permission applies to",
            example = "CONSULTATION")
    private String processTypeCode;

    @Column(name = "F_03", length = 60)
    @Schema(description = "Code of the WorkflowStepDefinition; null means permission applies to all steps",
            example = "STEP_EVALUATION", nullable = true)
    private String stepDefinitionCode;

    @Column(name = "F_04", length = 40, nullable = false)
    @Schema(description = "Action code this permission grants (e.g. APPROVE, REJECT, SUBMIT)",
            example = "APPROVE")
    private String actionCode;

    @Column(name = "F_05", nullable = false)
    @Schema(description = "Whether this permission is currently active and enforced")
    private boolean active = true;

    @Column(name = "F_06", length = 500)
    @Schema(description = "Optional human-readable explanation of this permission", nullable = true)
    private String description;
}
