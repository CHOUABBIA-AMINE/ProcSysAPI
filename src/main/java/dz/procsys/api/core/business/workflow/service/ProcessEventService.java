/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEventService
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Service
 *	@Layer		: Business / Workflow / Service
 *	@Package	: Business / Workflow / Service
 *
 *	Description : Read-only query service over ProcessEvent.
 *	              Used for audit, reporting, and future AI process analytics.
 *
 **/

package dz.procsys.api.core.business.workflow.service;

import dz.procsys.api.core.business.workflow.dto.ProcessEventDTO;
import dz.procsys.api.core.business.workflow.model.ProcessEvent;
import dz.procsys.api.core.business.workflow.repository.ProcessEventRepository;
import dz.procsys.api.platform.kernel.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProcessEventService
        extends GenericService<ProcessEvent, ProcessEventDTO, Long> {

    private final ProcessEventRepository processEventRepository;

    @Override
    protected JpaRepository<ProcessEvent, Long> getRepository() {
        return processEventRepository;
    }

    @Override
    protected String getEntityName() {
        return "ProcessEvent";
    }

    @Override
    protected ProcessEventDTO toDTO(ProcessEvent entity) {
        return ProcessEventDTO.fromEntity(entity);
    }

    @Override
    protected ProcessEvent toEntity(ProcessEventDTO dto) {
        throw new UnsupportedOperationException("ProcessEvent is immutable — use WorkflowInstanceService.");
    }

    @Override
    protected void updateEntityFromDTO(ProcessEvent entity, ProcessEventDTO dto) {
        throw new UnsupportedOperationException("ProcessEvent is immutable — cannot be updated.");
    }

    public List<ProcessEventDTO> getEventsByInstance(Long workflowInstanceId) {
        log.debug("Loading events for workflowInstanceId={}", workflowInstanceId);
        return processEventRepository
            .findByWorkflowInstanceIdOrderByOccurredAtAsc(workflowInstanceId)
            .stream()
            .map(ProcessEventDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public List<ProcessEventDTO> getAuditTrail(String businessContext, String businessKey) {
        log.debug("Audit trail for context={}, key={}", businessContext, businessKey);
        return processEventRepository
            .findByBusinessContextAndBusinessKeyOrderByOccurredAtAsc(businessContext, businessKey)
            .stream()
            .map(ProcessEventDTO::fromEntity)
            .collect(Collectors.toList());
    }
}
