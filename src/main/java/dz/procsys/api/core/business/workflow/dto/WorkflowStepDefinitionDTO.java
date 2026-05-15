/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepDefinitionDTO
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import dz.procsys.api.core.business.workflow.model.WorkflowStepDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowStepDefinitionDTO {

    private Long id;
    private Long workflowDefinitionId;
    private int stepOrder;
    private String code;
    private String designationFr;
    private String designationEn;
    private String designationAr;
    private String stepType;
    private String requiredRole;
    private int slaDays;
    private boolean mandatory;
    private String description;

    public static WorkflowStepDefinitionDTO fromEntity(WorkflowStepDefinition entity) {
        if (entity == null) return null;
        return WorkflowStepDefinitionDTO.builder()
            .id(entity.getId())
            .workflowDefinitionId(
                entity.getWorkflowDefinition() != null ? entity.getWorkflowDefinition().getId() : null)
            .stepOrder(entity.getStepOrder())
            .code(entity.getCode())
            .designationFr(entity.getDesignationFr())
            .designationEn(entity.getDesignationEn())
            .designationAr(entity.getDesignationAr())
            .stepType(entity.getStepType())
            .requiredRole(entity.getRequiredRole())
            .slaDays(entity.getSlaDays())
            .mandatory(entity.isMandatory())
            .description(entity.getDescription())
            .build();
    }

    public void updateEntity(WorkflowStepDefinition entity) {
        entity.setStepOrder(this.stepOrder);
        entity.setCode(this.code);
        entity.setDesignationFr(this.designationFr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationAr(this.designationAr);
        entity.setStepType(this.stepType);
        entity.setRequiredRole(this.requiredRole);
        entity.setSlaDays(this.slaDays);
        entity.setMandatory(this.mandatory);
        entity.setDescription(this.description);
    }
}
