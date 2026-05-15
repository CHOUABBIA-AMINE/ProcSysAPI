/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceDTO
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import dz.procsys.api.core.business.workflow.model.WorkflowInstance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowInstanceDTO {

    private Long id;
    private Long workflowDefinitionId;
    private String workflowDefinitionCode;
    private Long currentStepId;
    private String currentStepCode;
    private String businessContext;
    private String businessKey;
    private String instanceStatus;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private String startedBy;
    private LocalDateTime slaDueDate;
    private String observation;

    public static WorkflowInstanceDTO fromEntity(WorkflowInstance entity) {
        if (entity == null) return null;
        return WorkflowInstanceDTO.builder()
            .id(entity.getId())
            .workflowDefinitionId(
                entity.getWorkflowDefinition() != null ? entity.getWorkflowDefinition().getId() : null)
            .workflowDefinitionCode(
                entity.getWorkflowDefinition() != null ? entity.getWorkflowDefinition().getCode() : null)
            .currentStepId(
                entity.getCurrentStep() != null ? entity.getCurrentStep().getId() : null)
            .currentStepCode(
                entity.getCurrentStep() != null ? entity.getCurrentStep().getCode() : null)
            .businessContext(entity.getBusinessContext())
            .businessKey(entity.getBusinessKey())
            .instanceStatus(entity.getInstanceStatus())
            .startedAt(entity.getStartedAt())
            .completedAt(entity.getCompletedAt())
            .startedBy(entity.getStartedBy())
            .slaDueDate(entity.getSlaDueDate())
            .observation(entity.getObservation())
            .build();
    }

    public WorkflowInstance toEntity() {
        WorkflowInstance entity = new WorkflowInstance();
        entity.setBusinessContext(this.businessContext);
        entity.setBusinessKey(this.businessKey);
        entity.setInstanceStatus(this.instanceStatus != null ? this.instanceStatus : "ACTIVE");
        entity.setStartedAt(this.startedAt);
        entity.setStartedBy(this.startedBy);
        entity.setObservation(this.observation);
        return entity;
    }

    public void updateEntity(WorkflowInstance entity) {
        entity.setInstanceStatus(this.instanceStatus);
        entity.setObservation(this.observation);
        entity.setCompletedAt(this.completedAt);
        entity.setSlaDueDate(this.slaDueDate);
    }
}
