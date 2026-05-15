/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepDefinitionRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.WorkflowStepDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowStepDefinitionRepository extends JpaRepository<WorkflowStepDefinition, Long> {

    List<WorkflowStepDefinition> findByWorkflowDefinitionIdOrderByStepOrderAsc(Long workflowDefinitionId);

    Optional<WorkflowStepDefinition> findByWorkflowDefinitionIdAndCode(Long workflowDefinitionId, String code);

    Optional<WorkflowStepDefinition> findByWorkflowDefinitionIdAndStepOrder(Long workflowDefinitionId, int stepOrder);
}
