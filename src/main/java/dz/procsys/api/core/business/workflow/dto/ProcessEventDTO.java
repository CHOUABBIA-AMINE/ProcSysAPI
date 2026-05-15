/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEventDTO
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import dz.procsys.api.core.business.workflow.model.ProcessEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEventDTO {

    private Long id;
    private Long workflowInstanceId;
    private String businessContext;
    private String businessKey;
    private String eventType;
    private String fromStepCode;
    private String toStepCode;
    private String actionCode;
    private String actorUsername;
    private LocalDateTime occurredAt;
    private String decisionReason;
    private long durationSeconds;

    public static ProcessEventDTO fromEntity(ProcessEvent entity) {
        if (entity == null) return null;
        return ProcessEventDTO.builder()
            .id(entity.getId())
            .workflowInstanceId(
                entity.getWorkflowInstance() != null ? entity.getWorkflowInstance().getId() : null)
            .businessContext(entity.getBusinessContext())
            .businessKey(entity.getBusinessKey())
            .eventType(entity.getEventType())
            .fromStepCode(entity.getFromStepCode())
            .toStepCode(entity.getToStepCode())
            .actionCode(entity.getActionCode())
            .actorUsername(entity.getActorUsername())
            .occurredAt(entity.getOccurredAt())
            .decisionReason(entity.getDecisionReason())
            .durationSeconds(entity.getDurationSeconds())
            .build();
    }
}
