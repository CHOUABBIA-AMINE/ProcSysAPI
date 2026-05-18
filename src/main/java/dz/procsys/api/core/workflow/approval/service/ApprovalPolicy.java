/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalPolicy
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Approval
 *
 *	Description : Strategy interface for building ApprovalChain structures
 *	              based on a policy code and a business context.
 *
 **/

package dz.procsys.api.core.workflow.approval.service;

import dz.procsys.api.core.workflow.approval.model.ApprovalChain;

/**
 * ApprovalPolicy — builds an ApprovalChain from policy code and business context.
 */
public interface ApprovalPolicy {

    /**
     * Builds an ApprovalChain according to a specific policy.
     *
     * @param workflowInstanceId ID of the WorkflowInstance
     * @param businessRefId      ID of the WorkflowBusinessRef
     * @param policyCode         code that selects the policy implementation
     * @return initialized ApprovalChain (not yet persisted or already persisted depending on impl)
     */
    ApprovalChain buildChain(Long workflowInstanceId, Long businessRefId, String policyCode);
}
