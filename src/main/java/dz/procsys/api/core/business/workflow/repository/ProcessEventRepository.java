/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEventRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.ProcessEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessEventRepository extends JpaRepository<ProcessEvent, Long> {

    List<ProcessEvent> findByWorkflowInstanceIdOrderByOccurredAtAsc(Long workflowInstanceId);

    List<ProcessEvent> findByBusinessContextAndBusinessKeyOrderByOccurredAtAsc(
        String businessContext, String businessKey);

    Page<ProcessEvent> findByActorUsername(String actorUsername, Pageable pageable);

    Page<ProcessEvent> findByEventType(String eventType, Pageable pageable);
}
