/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceDTO
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Workflow / Runtime
 *
 *	Description : Flat read DTO representing the current state of a WorkflowInstance.
 *	              Contains only IDs and codes — no @ManyToOne, no nested entity objects.
 *	              Returned by WorkflowEngine command methods.
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
 * WorkflowInstanceDTO — flat representation of a running or completed WorkflowInstance.
 *
 * All FK fields are carried as IDs or codes — never as nested entity objects.
 *
 * Fields:
 *  - id                    : PK of the WorkflowInstance (F_00)
 *  - workflowDefinitionId  : FK id of the bound WorkflowDefinition
 *  - workflowDefinitionCode: code of the bound WorkflowDefinition
 *  - processTypeCode       : code of the WorkflowProcessType reference
 *  - businessKey           : owning aggregate key
 *  - instanceStatusCode    : code of the WorkflowInstanceStatus reference
 *  - currentStepCode       : code of the current WorkflowStepDefinition (null if terminal)
 *  - startedBy             : username of the initiating actor
 *  - startedAt             : instance creation timestamp
 *  - completedAt           : terminal state timestamp (null if still active)
 *  - slaDueDate            : SLA deadline for the current step
 *  - observation           : last recorded observation
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "WorkflowInstanceDTO",
        description = "Flat read DTO for a WorkflowInstance — IDs and codes only, no entity relations")
public class WorkflowInstanceDTO {

    @Schema(description = "Primary key of the WorkflowInstance", example = "101")
    private Long id;

    @Schema(description = "Primary key of the bound WorkflowDefinition", example = "5")
    private Long workflowDefinitionId;

    @Schema(description = "Unique code of the bound WorkflowDefinition", example = "CONS_PROCESS")
    private String workflowDefinitionCode;

    @Schema(description = "Code of the WorkflowProcessType reference", example = "CONSULTATION")
    private String processTypeCode;

    @Schema(description = "String representation of the owning aggregate primary key", example = "42")
    private String businessKey;

    @Schema(description = "Code of the current WorkflowInstanceStatus", example = "ACTIVE")
    private String instanceStatusCode;

    @Schema(description = "Code of the current step; null when instance is in a terminal state",
            example = "STEP_EVALUATION", nullable = true)
    private String currentStepCode;

    @Schema(description = "Username of the actor who started the instance", example = "a.chouabbia")
    private String startedBy;

    @Schema(description = "Timestamp when the instance was started")
    private LocalDateTime startedAt;

    @Schema(description = "Timestamp when the instance reached a terminal state; null if still active",
            nullable = true)
    private LocalDateTime completedAt;

    @Schema(description = "SLA deadline for the current step; null if not computed", nullable = true)
    private LocalDateTime slaDueDate;

    @Schema(description = "Last recorded free-text observation", nullable = true)
    private String observation;
}
