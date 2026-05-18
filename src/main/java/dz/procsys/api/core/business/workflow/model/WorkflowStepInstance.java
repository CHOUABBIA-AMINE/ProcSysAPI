/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepInstance
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Records the execution of one step inside a WorkflowInstance.
 *	              Each transition creates a new WorkflowStepInstance with actor,
 *	              timestamp, action taken, and optional decision reason.
 *
 **/

package dz.procsys.api.core.business.workflow.model;

import dz.procsys.api.platform.kernel.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * WorkflowStepInstance — Execution record of one step in a WorkflowInstance.
 *
 * Database table : T_03_02_02
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowInstance (FK → T_03_02_01.F_00)
 *  - F_02 : stepDefinition   (FK → T_03_01_02.F_00)
 *  - F_03 : stepStatus       (FK → T_03_00_03.F_00)  ← replaces hardcoded String
 *  - F_04 : actorUsername    — username of the actor who executed this step
 *  - F_05 : actionTaken      — action code from the WorkflowTransition
 *  - F_06 : startedAt        — when this step was entered
 *  - F_07 : completedAt      — when this step was exited
 *  - F_08 : slaDueDate       — SLA deadline for this step instance
 *  - F_09 : decisionReason   — free text reason (mandatory for REJECT / RETURN)
 *  - F_10 : observation      — additional optional observation
 */
@Setter
@Getter
@ToString(exclude = {"workflowInstance", "stepDefinition"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowStepInstance")
@Table(name = "T_03_02_02")
@Schema(name = "WorkflowStepInstance",
        description = "Execution record of one step inside a workflow instance")
public class WorkflowStepInstance extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_02_FK_01"),
        nullable = false
    )
    @Schema(description = "Owning workflow instance")
    private WorkflowInstance workflowInstance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_02_FK_02"),
        nullable = false
    )
    @Schema(description = "Static step definition this execution refers to")
    private WorkflowStepDefinition stepDefinition;

    // ── REFACTORED: was String stepStatus = "PENDING" (hardcoded literal) ────
    // Now a FK to T_03_00_03 — status values managed in the reference table.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_02_FK_03"),
        nullable = false
    )
    @Schema(description = "Execution status of this step — resolved from reference table T_03_00_03")
    private WorkflowStepStatus stepStatus;
    // ─────────────────────────────────────────────────────────────────────────

    // NB: actorUsername kept as String for now; direct User FK deferred to a later commit.
    @Column(name = "F_04", length = 150)
    @Schema(description = "Username of the actor who executed this step")
    private String actorUsername;

    @Column(name = "F_05", length = 40)
    @Schema(description = "Action code from the WorkflowTransition that led to completion "
                        + "(e.g. APPROVE, REJECT, SUBMIT, RETURN, ESCALATE, CLOSE)")
    private String actionTaken;

    @Column(name = "F_06")
    @Schema(description = "Timestamp when this step was entered")
    private LocalDateTime startedAt;

    @Column(name = "F_07")
    @Schema(description = "Timestamp when this step was exited / completed")
    private LocalDateTime completedAt;

    @Column(name = "F_08")
    @Schema(description = "SLA deadline for this step instance")
    private LocalDateTime slaDueDate;

    @Column(name = "F_09", length = 1000)
    @Schema(description = "Free text reason — mandatory when actionTaken is REJECT or RETURN")
    private String decisionReason;

    @Column(name = "F_10", length = 1000)
    @Schema(description = "Additional optional observation left by the actor")
    private String observation;
}
