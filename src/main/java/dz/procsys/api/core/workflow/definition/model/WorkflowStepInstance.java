/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepInstance
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
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

package dz.procsys.api.core.workflow.definition.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
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
 * WorkflowStepInstance - Execution record of one step in a WorkflowInstance.
 *
 * Database table: T_03_02_02
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowInstance (FK → T_03_02_01.F_00)
 *  - F_02 : stepDefinition   (FK → T_03_01_02.F_00)
 *  - F_03 : stepStatus       – PENDING | IN_PROGRESS | COMPLETED | SKIPPED | REJECTED
 *  - F_04 : actorUsername    – user who executed this step
 *  - F_05 : actionTaken      – action code from WorkflowTransition
 *  - F_06 : startedAt        – when this step was entered
 *  - F_07 : completedAt      – when this step was exited
 *  - F_08 : slaDueDate       – SLA deadline for this step instance
 *  - F_09 : decisionReason   – free text reason (mandatory for REJECT/RETURN)
 *  - F_10 : observation      – additional optional observation
 */
@Setter
@Getter
@ToString(exclude = {"workflowInstance", "stepDefinition"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowStepInstance")
@Table(name = "T_03_02_02")
public class WorkflowStepInstance extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_01", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_02_FK_01"), nullable = false)
    private WorkflowInstance workflowInstance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_02", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_02_FK_02"), nullable = false)
    private WorkflowStepDefinition stepDefinition;

    /**
     * Step execution status: PENDING | IN_PROGRESS | COMPLETED | SKIPPED | REJECTED
     */
    @Column(name = "F_03", length = 20, nullable = false)
    private String stepStatus = "PENDING";

    @Column(name = "F_04", length = 150)
    private String actorUsername;

    /**
     * Action code from the WorkflowTransition that led to completion.
     * APPROVE | REJECT | SUBMIT | RETURN | ESCALATE | CLOSE
     */
    @Column(name = "F_05", length = 40)
    private String actionTaken;

    @Column(name = "F_06")
    private LocalDateTime startedAt;

    @Column(name = "F_07")
    private LocalDateTime completedAt;

    @Column(name = "F_08")
    private LocalDateTime slaDueDate;

    @Column(name = "F_09", length = 1000)
    private String decisionReason;

    @Column(name = "F_10", length = 1000)
    private String observation;
}
