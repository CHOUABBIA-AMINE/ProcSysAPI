/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowTransitionRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.WorkflowTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowTransitionRepository extends JpaRepository<WorkflowTransition, Long> {

    List<WorkflowTransition> findByFromStepId(Long fromStepId);

    Optional<WorkflowTransition> findByFromStepIdAndActionCode(Long fromStepId, String actionCode);

    List<WorkflowTransition> findByFromStepIdAndTerminalFalse(Long fromStepId);
}
