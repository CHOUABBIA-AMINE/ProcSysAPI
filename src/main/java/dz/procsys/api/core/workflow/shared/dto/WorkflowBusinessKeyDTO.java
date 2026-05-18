/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowBusinessKeyDTO
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Workflow / Shared
 *
 *	Description : Flat DTO carrying the minimal information required to
 *	              identify a business aggregate when interacting with the
 *	              workflow module (start / query instances, etc.).
 *	              Avoids passing full aggregates across bounded contexts.
 *
 **/

package dz.procsys.api.core.workflow.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowBusinessKeyDTO {

	/**
	 * Technical identifier of the business aggregate.
	 * Example: "42" for a Consultation with id=42.
	 */
	private String businessId;

	/**
	 * Type of aggregate: CONSULTATION, CONTRACT, AMENDMENT, PLAN, ...
	 */
	private String businessType;

}
