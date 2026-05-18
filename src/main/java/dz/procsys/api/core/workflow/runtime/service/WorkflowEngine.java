/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowEngine
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Runtime
 *
 *	Description : Domain-driven orchestration API for the workflow engine.
 *	              Defines explicit command methods that are the ONLY gateway
 *	              to creating and advancing workflow instances.
 *	              No CRUD inheritance — each method is an intentional business command.
 *
 **/

package dz.procsys.api.core.workflow.runtime.service;

import dz.procsys.api.core.workflow.runtime.dto.WorkflowBusinessKeyDTO;
import dz.procsys.api.core.workflow.runtime.dto.WorkflowInstanceDTO;

/**
 * WorkflowEngine — explicit command API for workflow lifecycle management.
 *
 * Implementations must:
 *  - validate that the workflowDefinition is active before starting
 *  - persist a WorkflowInstance and its first WorkflowStepInstance
 *  - fire a ProcessEvent for every state change
 *  - never expose generic CRUD operations
 */
public interface WorkflowEngine {

    /**
     * Starts a new WorkflowInstance for the given business aggregate.
     *
     * <p>Resolves the active WorkflowDefinition bound to the process type
     * carried in the DTO, creates the instance at the first step, and
     * fires a WORKFLOW_STARTED ProcessEvent.
     *
     * @param dto contains the processTypeCode, businessKey, and startedBy username
     * @return a flat DTO representing the newly created instance
     * @throws IllegalArgumentException if no active definition exists for the process type
     * @throws IllegalStateException    if a live instance already exists for that businessKey
     */
    WorkflowInstanceDTO startInstance(WorkflowBusinessKeyDTO dto);

    /**
     * Executes a named action on the current step of the given instance.
     *
     * <p>Resolves the matching WorkflowTransition for the current step and
     * actionCode, advances the instance to the next step, closes the current
     * WorkflowStepInstance, and fires a TRANSITION_FIRED ProcessEvent.
     *
     * @param instanceId unique identifier of the WorkflowInstance
     * @param actionCode transition action code (e.g. "APPROVE", "REJECT", "SUBMIT")
     * @return updated flat DTO after the transition
     * @throws jakarta.persistence.EntityNotFoundException if the instance is not found
     * @throws IllegalStateException if the actionCode is not valid for the current step,
     *                               or if the instance is already in a terminal state
     */
    WorkflowInstanceDTO executeStepAction(Long instanceId, String actionCode);

    /**
     * Cancels a running WorkflowInstance.
     *
     * <p>Sets the instance status to CANCELLED, closes any open
     * WorkflowStepInstance, records the cancellationReason, and fires
     * a WORKFLOW_CANCELLED ProcessEvent.
     *
     * @param instanceId         unique identifier of the WorkflowInstance
     * @param cancellationReason mandatory free-text reason for cancellation
     * @throws jakarta.persistence.EntityNotFoundException if the instance is not found
     * @throws IllegalStateException if the instance is already in a terminal state
     */
    void cancelInstance(Long instanceId, String cancellationReason);

    /**
     * Checks whether the given instance has reached a terminal step and,
     * if so, marks it as COMPLETED.
     *
     * <p>This method is idempotent: calling it on an already-completed
     * instance is a no-op. It fires a WORKFLOW_COMPLETED ProcessEvent
     * only on the first completion.
     *
     * @param instanceId unique identifier of the WorkflowInstance
     * @return {@code true} if the instance was completed by this call,
     *         {@code false} if it was already complete or not yet terminal
     * @throws jakarta.persistence.EntityNotFoundException if the instance is not found
     */
    boolean completeInstanceIfTerminal(Long instanceId);
}
