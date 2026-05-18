/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalService
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Approval
 *
 *	Description : Domain service contract for managing approval chains
 *	              and recording approval decisions for workflow instances.
 *	              No CRUD methods — only explicit domain commands.
 *
 **/

package dz.procsys.api.core.workflow.approval.service;

import dz.procsys.api.core.workflow.approval.model.ApprovalChain;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ApprovalService — explicit approval-related commands.
 */
public interface ApprovalService {

    /**
     * Creates and initializes an ApprovalChain for a given WorkflowInstance.
     *
     * @param workflowInstanceId ID of the workflow instance
     * @param businessRefId      ID of the WorkflowBusinessRef
     * @param policyCode         code of the ApprovalPolicy to apply
     * @return created ApprovalChain aggregate
     */
    @Schema(description = "Create and initialize an approval chain for a workflow instance")
    ApprovalChain createApprovalChain(Long workflowInstanceId, Long businessRefId, String policyCode);

    /**
     * Records an approval decision on a specific approval step.
     *
     * @param approvalStepId ID of the ApprovalStep
     * @param decisionCode   APPROVED | REJECTED | RETURNED
     * @param decidedById    ID of the User who decides
     * @param justification  mandatory for REJECTED or RETURNED
     * @param observation    optional additional comment
     */
    @Schema(description = "Record a decision on an approval step")
    void recordApprovalDecision(Long approvalStepId,
                                String decisionCode,
                                Long decidedById,
                                String justification,
                                String observation);
}
