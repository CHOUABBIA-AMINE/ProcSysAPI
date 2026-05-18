/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EscalationService
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Escalation
 *
 *	Description : Domain service contract for SLA evaluation and escalation
 *	              of workflow step instances.
 *
 **/

package dz.procsys.api.core.workflow.escalation.service;

import java.util.List;

import dz.procsys.api.core.workflow.escalation.model.SLARecord;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * EscalationService — manages SLA records and escalation triggers.
 */
public interface EscalationService {

    /**
     * Creates an SLARecord when a workflow step is entered.
     *
     * @param workflowInstanceId     ID of the WorkflowInstance
     * @param workflowStepInstanceId ID of the WorkflowStepInstance
     * @param stepDefinitionCode     code of the WorkflowStepDefinition
     * @param processTypeCode        code of the WorkflowProcessType
     * @return created SLARecord
     */
    @Schema(description = "Create an SLARecord for an entered workflow step")
    SLARecord createSlaForStep(Long workflowInstanceId,
                               Long workflowStepInstanceId,
                               String stepDefinitionCode,
                               String processTypeCode);

    /**
     * Marks a step as closed in its SLARecord and updates duration.
     *
     * @param workflowStepInstanceId ID of the WorkflowStepInstance
     */
    @Schema(description = "Close an SLARecord when the step is completed")
    void closeSlaForStep(Long workflowStepInstanceId);

    /**
     * Evaluates all open SLARecords and triggers escalation logic where needed.
     *
     * @return list of IDs of SLARecord entries that were escalated
     */
    @Schema(description = "Evaluate open SLA records and trigger escalations when breached")
    List<Long> evaluateAndEscalate();
}
