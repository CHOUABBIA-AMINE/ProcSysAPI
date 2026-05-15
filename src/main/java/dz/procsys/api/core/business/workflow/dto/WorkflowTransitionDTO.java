/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowTransitionDTO
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import dz.procsys.api.core.business.workflow.model.WorkflowTransition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowTransitionDTO {

    private Long id;
    private Long fromStepId;
    private Long toStepId;
    private String actionCode;
    private String labelFr;
    private String labelEn;
    private String guardCode;
    private boolean terminal;

    public static WorkflowTransitionDTO fromEntity(WorkflowTransition entity) {
        if (entity == null) return null;
        return WorkflowTransitionDTO.builder()
            .id(entity.getId())
            .fromStepId(entity.getFromStep() != null ? entity.getFromStep().getId() : null)
            .toStepId(entity.getToStep() != null ? entity.getToStep().getId() : null)
            .actionCode(entity.getActionCode())
            .labelFr(entity.getLabelFr())
            .labelEn(entity.getLabelEn())
            .guardCode(entity.getGuardCode())
            .terminal(entity.isTerminal())
            .build();
    }
}
