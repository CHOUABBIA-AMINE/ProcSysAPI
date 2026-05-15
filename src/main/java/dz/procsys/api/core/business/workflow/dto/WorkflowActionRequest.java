/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowActionRequest
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO (Request)
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 *	Description : Payload sent by client when an actor executes a workflow action.
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowActionRequest {

    /** The action to fire (APPROVE | REJECT | SUBMIT | RETURN | ESCALATE | CLOSE). */
    private String actionCode;

    /** Username of the actor executing the action. */
    private String actorUsername;

    /** Optional reason / justification — mandatory for REJECT / RETURN actions. */
    private String decisionReason;

    /** Optional free-text observation. */
    private String observation;
}
