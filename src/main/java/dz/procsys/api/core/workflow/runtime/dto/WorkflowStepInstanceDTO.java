/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepInstanceDTO
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Workflow / Runtime
 *
 *	Description : Flat read DTO representing one executed step inside a WorkflowInstance.
 *	              Contains only IDs and codes — no @ManyToOne, no nested entity objects.
 *
 **/

package dz.procsys.api.core.workflow.runtime.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowStepInstanceDTO — flat representation of one WorkflowStepInstance execution record.
 *
 * All FK fields are carried as IDs or codes — never as nested entity objects.
 *
 * Fields:
 *  - id                 : PK of the WorkflowStepInstance (F_00)
 *  - workflowInstanceId : FK id of the parent WorkflowInstance
 *  - stepDefinitionId   : FK id of the bound WorkflowStepDefinition
 *  - stepDefinitionCode : code of the bound WorkflowStepDefinition
 *  - stepStatusCode     : code of the WorkflowStepStatus reference
 *  - actorUsername      : user who executed this step
 *  - actionTaken        : transition action code that closed this step
 *  - startedAt          : when this step was entered
 *  - completedAt        : when this step was exited (null if still open)
 *  - slaDueDate         : SLA deadline for this step
 *  - decisionReason     : mandatory reason for REJECT / RETURN actions
 *  - observation        : additional optional observation
 *  - durationSeconds    : seconds spent in this step (-1 if not yet closed)
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "WorkflowStepInstanceDTO",
        description = "Flat read DTO for one WorkflowStepInstance — IDs and codes only, no entity relations")
public class WorkflowStepInstanceDTO {

    @Schema(description = "Primary key of the WorkflowStepInstance", example = "201")
    private Long id;

    @Schema(description = "Primary key of the parent WorkflowInstance", example = "101")
    private Long workflowInstanceId;

    @Schema(description = "Primary key of the bound WorkflowStepDefinition", example = "12")
    private Long stepDefinitionId;

    @Schema(description = "Unique code of the bound WorkflowStepDefinition", example = "STEP_EVALUATION")
    private String stepDefinitionCode;

    @Schema(description = "Code of the current WorkflowStepStatus", example = "IN_PROGRESS")
    private String stepStatusCode;

    @Schema(description = "Username of the actor who executed this step", example = "b.director",
            nullable = true)
    private String actorUsername;

    @Schema(description = "Action code from the WorkflowTransition that closed this step",
            example = "APPROVE", nullable = true)
    private String actionTaken;

    @Schema(description = "Timestamp when this step was entered", nullable = true)
    private LocalDateTime startedAt;

    @Schema(description = "Timestamp when this step was exited; null if still open", nullable = true)
    private LocalDateTime completedAt;

    @Schema(description = "SLA deadline for this step; null if not computed", nullable = true)
    private LocalDateTime slaDueDate;

    @Schema(description = "Free-text reason; mandatory for REJECT or RETURN actions", nullable = true)
    private String decisionReason;

    @Schema(description = "Additional optional free-text observation", nullable = true)
    private String observation;

    @Schema(description = "Duration in seconds spent in this step; -1 if not yet closed",
            example = "3600")
    private long durationSeconds = -1L;
}
