/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DelegationResolver
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: Workflow / Delegation
 *
 *	Description : Domain service contract responsible for resolving effective
 *	              actors when delegation rules apply.
 *
 **/

package dz.procsys.api.core.workflow.delegation.service;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DelegationResolver — computes effective actor IDs given a delegator context.
 */
public interface DelegationResolver {

    /**
     * Resolves all user IDs that may act on behalf of the given delegator
     * for a specific process type at the current time.
     *
     * @param delegatorUserId ID of the delegating User
     * @param processTypeCode code of WorkflowProcessType
     * @return list of user IDs allowed to act (may include delegator and delegates)
     */
    @Schema(description = "Resolve effective actor IDs considering delegation rules")
    List<Long> resolveEffectiveActors(Long delegatorUserId, String processTypeCode);
}
