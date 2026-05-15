/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceService
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Service
 *	@Layer		: Business / Workflow / Service
 *	@Package	: Business / Workflow / Service
 *
 *	Description : Core orchestration service.
 *	              Starts workflow instances and executes transitions between steps.
 *	              Writes ProcessEvent records on every state change for full audit.
 *
 **/

package dz.procsys.api.core.business.workflow.service;

import dz.procsys.api.core.business.workflow.dto.WorkflowActionRequest;
import dz.procsys.api.core.business.workflow.dto.WorkflowInstanceDTO;
import dz.procsys.api.core.business.workflow.model.ProcessEvent;
import dz.procsys.api.core.business.workflow.model.WorkflowDefinition;
import dz.procsys.api.core.business.workflow.model.WorkflowInstance;
import dz.procsys.api.core.business.workflow.model.WorkflowStepDefinition;
import dz.procsys.api.core.business.workflow.model.WorkflowStepInstance;
import dz.procsys.api.core.business.workflow.model.WorkflowTransition;
import dz.procsys.api.core.business.workflow.repository.ProcessEventRepository;
import dz.procsys.api.core.business.workflow.repository.WorkflowDefinitionRepository;
import dz.procsys.api.core.business.workflow.repository.WorkflowInstanceRepository;
import dz.procsys.api.core.business.workflow.repository.WorkflowStepDefinitionRepository;
import dz.procsys.api.core.business.workflow.repository.WorkflowStepInstanceRepository;
import dz.procsys.api.core.business.workflow.repository.WorkflowTransitionRepository;
import dz.procsys.api.exception.business.ResourceNotFoundException;
import dz.procsys.api.platform.kernel.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class WorkflowInstanceService
        extends GenericService<WorkflowInstance, WorkflowInstanceDTO, Long> {

    private final WorkflowInstanceRepository workflowInstanceRepository;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;
    private final WorkflowStepDefinitionRepository stepDefinitionRepository;
    private final WorkflowStepInstanceRepository stepInstanceRepository;
    private final WorkflowTransitionRepository transitionRepository;
    private final ProcessEventRepository processEventRepository;

    @Override
    protected JpaRepository<WorkflowInstance, Long> getRepository() {
        return workflowInstanceRepository;
    }

    @Override
    protected String getEntityName() {
        return "WorkflowInstance";
    }

    @Override
    protected WorkflowInstanceDTO toDTO(WorkflowInstance entity) {
        return WorkflowInstanceDTO.fromEntity(entity);
    }

    @Override
    protected WorkflowInstance toEntity(WorkflowInstanceDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(WorkflowInstance entity, WorkflowInstanceDTO dto) {
        dto.updateEntity(entity);
    }

    // -----------------------------------------------------------------------
    // START — create a new workflow instance from a definition
    // -----------------------------------------------------------------------

    /**
     * Start a new WorkflowInstance for a business aggregate.
     *
     * @param definitionId  ID of the WorkflowDefinition to instantiate
     * @param businessContext domain context (CONSULTATION | CONTRACT | AMENDMENT | PLAN)
     * @param businessKey   String PK of the owning aggregate (e.g. "42")
     * @param startedBy     username of the actor who initiated the workflow
     * @return DTO of the created instance positioned on the first step
     */
    @Transactional
    public WorkflowInstanceDTO startWorkflow(Long definitionId,
                                             String businessContext,
                                             String businessKey,
                                             String startedBy) {

        log.info("Starting workflow: definitionId={}, context={}, key={}, actor={}",
            definitionId, businessContext, businessKey, startedBy);

        WorkflowDefinition definition = workflowDefinitionRepository.findById(definitionId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "WorkflowDefinition not found with ID: " + definitionId));

        // Resolve first step (stepOrder == 1 or minimum order)
        WorkflowStepDefinition firstStep = stepDefinitionRepository
            .findByWorkflowDefinitionIdOrderByStepOrderAsc(definitionId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(
                "No steps found for WorkflowDefinition ID: " + definitionId));

        LocalDateTime now = LocalDateTime.now();

        // Build the instance
        WorkflowInstance instance = new WorkflowInstance();
        instance.setWorkflowDefinition(definition);
        instance.setCurrentStep(firstStep);
        instance.setBusinessContext(businessContext);
        instance.setBusinessKey(businessKey);
        instance.setInstanceStatus("ACTIVE");
        instance.setStartedAt(now);
        instance.setStartedBy(startedBy);
        if (firstStep.getSlaDays() > 0) {
            instance.setSlaDueDate(now.plusDays(firstStep.getSlaDays()));
        }

        WorkflowInstance saved = workflowInstanceRepository.save(instance);

        // Create first step instance (IN_PROGRESS)
        WorkflowStepInstance stepInstance = new WorkflowStepInstance();
        stepInstance.setWorkflowInstance(saved);
        stepInstance.setStepDefinition(firstStep);
        stepInstance.setStepStatus("IN_PROGRESS");
        stepInstance.setStartedAt(now);
        if (firstStep.getSlaDays() > 0) {
            stepInstance.setSlaDueDate(now.plusDays(firstStep.getSlaDays()));
        }
        stepInstanceRepository.save(stepInstance);

        // Record WORKFLOW_STARTED and STEP_ENTERED events
        writeEvent(saved, "WORKFLOW_STARTED", null, firstStep.getCode(),
            "SUBMIT", startedBy, now, null, -1L);
        writeEvent(saved, "STEP_ENTERED", null, firstStep.getCode(),
            null, startedBy, now, null, -1L);

        log.info("Workflow instance {} started. First step: {}", saved.getId(), firstStep.getCode());
        return WorkflowInstanceDTO.fromEntity(saved);
    }

    // -----------------------------------------------------------------------
    // EXECUTE ACTION — fire a transition on an active instance
    // -----------------------------------------------------------------------

    /**
     * Execute a workflow action on a live instance.
     *
     * @param instanceId the WorkflowInstance primary key
     * @param request    contains actionCode, actorUsername, decisionReason, observation
     * @return updated WorkflowInstanceDTO
     */
    @Transactional
    public WorkflowInstanceDTO executeAction(Long instanceId, WorkflowActionRequest request) {

        log.info("Executing action '{}' on instance {} by {}",
            request.getActionCode(), instanceId, request.getActorUsername());

        WorkflowInstance instance = workflowInstanceRepository.findById(instanceId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "WorkflowInstance not found with ID: " + instanceId));

        if (!"ACTIVE".equals(instance.getInstanceStatus())) {
            throw new IllegalStateException(
                "Workflow instance " + instanceId + " is not ACTIVE (status=" +
                    instance.getInstanceStatus() + ")");
        }

        WorkflowStepDefinition currentStep = instance.getCurrentStep();
        if (currentStep == null) {
            throw new IllegalStateException(
                "Workflow instance " + instanceId + " has no current step.");
        }

        // Resolve transition
        WorkflowTransition transition = transitionRepository
            .findByFromStepIdAndActionCode(currentStep.getId(), request.getActionCode())
            .orElseThrow(() -> new ResourceNotFoundException(
                "No transition with action '" + request.getActionCode() +
                    "' from step '" + currentStep.getCode() + "'"));

        LocalDateTime now = LocalDateTime.now();
        String fromStepCode = currentStep.getCode();

        // Close current step instance
        stepInstanceRepository
            .findByWorkflowInstanceIdAndStepStatus(instanceId, "IN_PROGRESS")
            .ifPresent(si -> {
                long duration = si.getStartedAt() != null
                    ? java.time.Duration.between(si.getStartedAt(), now).getSeconds()
                    : -1L;
                si.setStepStatus("COMPLETED");
                si.setActionTaken(request.getActionCode());
                si.setActorUsername(request.getActorUsername());
                si.setCompletedAt(now);
                si.setDecisionReason(request.getDecisionReason());
                si.setObservation(request.getObservation());
                stepInstanceRepository.save(si);

                writeEvent(instance, "STEP_COMPLETED", fromStepCode, null,
                    request.getActionCode(), request.getActorUsername(), now,
                    request.getDecisionReason(), duration);
            });

        // Apply transition
        writeEvent(instance, "TRANSITION_FIRED", fromStepCode,
            transition.getToStep() != null ? transition.getToStep().getCode() : null,
            request.getActionCode(), request.getActorUsername(), now,
            request.getDecisionReason(), -1L);

        if (transition.isTerminal()) {
            instance.setCurrentStep(null);
            instance.setInstanceStatus("COMPLETED");
            instance.setCompletedAt(now);

            writeEvent(instance, "WORKFLOW_COMPLETED", fromStepCode, null,
                request.getActionCode(), request.getActorUsername(), now,
                request.getDecisionReason(), -1L);

            log.info("Workflow instance {} COMPLETED via action '{}'",
                instanceId, request.getActionCode());

        } else {
            WorkflowStepDefinition nextStep = transition.getToStep();
            instance.setCurrentStep(nextStep);
            instance.setSlaDueDate(
                nextStep.getSlaDays() > 0 ? now.plusDays(nextStep.getSlaDays()) : null);

            // Open next step instance
            WorkflowStepInstance nextStepInstance = new WorkflowStepInstance();
            nextStepInstance.setWorkflowInstance(instance);
            nextStepInstance.setStepDefinition(nextStep);
            nextStepInstance.setStepStatus("IN_PROGRESS");
            nextStepInstance.setStartedAt(now);
            if (nextStep.getSlaDays() > 0) {
                nextStepInstance.setSlaDueDate(now.plusDays(nextStep.getSlaDays()));
            }
            stepInstanceRepository.save(nextStepInstance);

            writeEvent(instance, "STEP_ENTERED", null, nextStep.getCode(),
                null, request.getActorUsername(), now, null, -1L);

            log.info("Workflow instance {} moved to step '{}'", instanceId, nextStep.getCode());
        }

        WorkflowInstance updated = workflowInstanceRepository.save(instance);
        return WorkflowInstanceDTO.fromEntity(updated);
    }

    // -----------------------------------------------------------------------
    // CANCEL
    // -----------------------------------------------------------------------

    @Transactional
    public WorkflowInstanceDTO cancelWorkflow(Long instanceId, String actorUsername, String reason) {
        log.info("Cancelling workflow instance {} by {}", instanceId, actorUsername);

        WorkflowInstance instance = workflowInstanceRepository.findById(instanceId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "WorkflowInstance not found with ID: " + instanceId));

        LocalDateTime now = LocalDateTime.now();
        instance.setInstanceStatus("CANCELLED");
        instance.setCompletedAt(now);

        writeEvent(instance, "WORKFLOW_CANCELLED",
            instance.getCurrentStep() != null ? instance.getCurrentStep().getCode() : null,
            null, "CLOSE", actorUsername, now, reason, -1L);

        instance.setCurrentStep(null);
        return WorkflowInstanceDTO.fromEntity(workflowInstanceRepository.save(instance));
    }

    // -----------------------------------------------------------------------
    // QUERIES
    // -----------------------------------------------------------------------

    public WorkflowInstanceDTO findByBusinessContextAndKey(String businessContext, String businessKey) {
        return workflowInstanceRepository
            .findByBusinessContextAndBusinessKey(businessContext, businessKey)
            .map(WorkflowInstanceDTO::fromEntity)
            .orElseThrow(() -> new ResourceNotFoundException(
                "No WorkflowInstance for context=" + businessContext + ", key=" + businessKey));
    }

    public List<WorkflowInstanceDTO> getByContextAndStatus(String businessContext, String status) {
        return workflowInstanceRepository
            .findByBusinessContextAndInstanceStatus(businessContext, status)
            .stream()
            .map(WorkflowInstanceDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public Page<WorkflowInstanceDTO> getByStatus(String status, Pageable pageable) {
        return workflowInstanceRepository.findByInstanceStatus(status, pageable)
            .map(WorkflowInstanceDTO::fromEntity);
    }

    // -----------------------------------------------------------------------
    // PRIVATE HELPER
    // -----------------------------------------------------------------------

    private void writeEvent(WorkflowInstance instance,
                            String eventType,
                            String fromStepCode,
                            String toStepCode,
                            String actionCode,
                            String actor,
                            LocalDateTime occurredAt,
                            String reason,
                            long durationSeconds) {
        ProcessEvent event = new ProcessEvent();
        event.setWorkflowInstance(instance);
        event.setBusinessContext(instance.getBusinessContext());
        event.setBusinessKey(instance.getBusinessKey());
        event.setEventType(eventType);
        event.setFromStepCode(fromStepCode);
        event.setToStepCode(toStepCode);
        event.setActionCode(actionCode);
        event.setActorUsername(actor);
        event.setOccurredAt(occurredAt);
        event.setDecisionReason(reason);
        event.setDurationSeconds(durationSeconds);
        processEventRepository.save(event);
    }
}
