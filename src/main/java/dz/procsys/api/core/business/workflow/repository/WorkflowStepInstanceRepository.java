/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepInstanceRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.WorkflowStepInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowStepInstanceRepository extends JpaRepository<WorkflowStepInstance, Long> {

    List<WorkflowStepInstance> findByWorkflowInstanceIdOrderByStartedAtAsc(Long workflowInstanceId);

    Optional<WorkflowStepInstance> findByWorkflowInstanceIdAndStepStatus(
        Long workflowInstanceId, String stepStatus);

    List<WorkflowStepInstance> findByActorUsernameAndStepStatus(String actorUsername, String stepStatus);
}
