/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SLARecord
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Escalation
 *
 *	Description : Tracks the SLA lifecycle for one WorkflowStepInstance.
 *	              Created when a step is entered; updated when it is exited
 *	              or when an escalation fires.
 *	              Breached flag is set by the escalation engine when
 *	              LocalDateTime.now() > dueAt and step is still open.
 *
 **/

package dz.procsys.api.core.workflow.escalation.model;

import java.time.LocalDateTime;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SLARecord — SLA lifecycle tracker for one WorkflowStepInstance.
 *
 * Database table : T_03_05_01
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowInstanceId      — FK id of the WorkflowInstance
 *  - F_02 : workflowStepInstanceId  — FK id of the WorkflowStepInstance
 *  - F_03 : stepDefinitionCode      — code of the WorkflowStepDefinition
 *  - F_04 : startedAt               — when the step was entered
 *  - F_05 : dueAt                   — computed SLA deadline
 *  - F_06 : closedAt                — when the step was exited (null if open)
 *  - F_07 : breached                — true if dueAt < now and step still open
 *  - F_08 : durationSeconds         — actual duration (-1 if step still open)
 *  - F_09 : observation             — optional free-text note
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SLARecord")
@Table(name = "T_03_05_01")
@Schema(name = "SLARecord",
        description = "SLA lifecycle tracker for one WorkflowStepInstance")
public class SLARecord extends GenericModel {

    @Column(name = "F_01", nullable = false)
    @Schema(description = "Primary key of the owning WorkflowInstance", example = "101")
    private Long workflowInstanceId;

    @Column(name = "F_02", nullable = false)
    @Schema(description = "Primary key of the tracked WorkflowStepInstance", example = "201")
    private Long workflowStepInstanceId;

    @Column(name = "F_03", length = 60, nullable = false)
    @Schema(description = "Code of the WorkflowStepDefinition for this step",
            example = "STEP_EVALUATION")
    private String stepDefinitionCode;

    @Column(name = "F_04", nullable = false)
    @Schema(description = "Timestamp when the step was entered")
    private LocalDateTime startedAt;

    @Column(name = "F_05", nullable = false)
    @Schema(description = "Computed SLA deadline for this step")
    private LocalDateTime dueAt;

    @Column(name = "F_06")
    @Schema(description = "Timestamp when the step was closed; null while step is still open",
            nullable = true)
    private LocalDateTime closedAt;

    @Column(name = "F_07", nullable = false)
    @Schema(description = "True if the SLA deadline was breached while the step was still open")
    private boolean breached = false;

    @Column(name = "F_08", nullable = false)
    @Schema(description = "Actual duration in seconds; -1 while the step is still open",
            example = "3600")
    private long durationSeconds = -1L;

    @Column(name = "F_09", length = 1000)
    @Schema(description = "Optional free-text observation", nullable = true)
    private String observation;
}
