/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.WorkflowInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowInstanceRepository extends JpaRepository<WorkflowInstance, Long> {

    Optional<WorkflowInstance> findByBusinessContextAndBusinessKey(String businessContext, String businessKey);

    List<WorkflowInstance> findByBusinessContextAndInstanceStatus(String businessContext, String instanceStatus);

    Page<WorkflowInstance> findByInstanceStatus(String instanceStatus, Pageable pageable);

    boolean existsByBusinessContextAndBusinessKeyAndInstanceStatus(
        String businessContext, String businessKey, String instanceStatus);
}
