/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowAuthorizationService
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Security
 *
 *	Description : Domain service contract for evaluating whether a given user
 *	              is authorized to perform a workflow action on a given step
 *	              within a process type.
 *
 **/

package dz.procsys.api.core.workflow.security.service;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * WorkflowAuthorizationService — evaluates workflow permissions and policies.
 */
public interface WorkflowAuthorizationService {

    /**
     * Checks if a user is allowed to execute a given action on a workflow step.
     *
     * @param userId             ID of the User attempting the action
     * @param roleId             ID of the acting Role (optional, may be null)
     * @param processTypeCode    code of the WorkflowProcessType
     * @param stepDefinitionCode code of the WorkflowStepDefinition (may be null)
     * @param actionCode         action code (APPROVE, REJECT, SUBMIT, ...)
     * @return true if action is authorized, false otherwise
     */
    @Schema(description = "Check whether the given user is authorized to perform an action on a workflow step")
    boolean isActionAuthorized(Long userId,
                               Long roleId,
                               String processTypeCode,
                               String stepDefinitionCode,
                               String actionCode);
}
