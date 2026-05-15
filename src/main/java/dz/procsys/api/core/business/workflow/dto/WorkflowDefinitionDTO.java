/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowDefinitionDTO
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: DTO
 *	@Layer		: Transfer Object
 *	@Package	: Business / Workflow / DTO
 *
 **/

package dz.procsys.api.core.business.workflow.dto;

import dz.procsys.api.core.business.workflow.model.WorkflowDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDefinitionDTO {

    private Long id;
    private String code;
    private String designationFr;
    private String designationEn;
    private String designationAr;
    private String version;
    private String businessContext;
    private boolean active;
    private String description;

    // ---- conversion helpers -----------------------------------------------

    public static WorkflowDefinitionDTO fromEntity(WorkflowDefinition entity) {
        if (entity == null) return null;
        return WorkflowDefinitionDTO.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .designationFr(entity.getDesignationFr())
            .designationEn(entity.getDesignationEn())
            .designationAr(entity.getDesignationAr())
            .version(entity.getVersion())
            .businessContext(entity.getBusinessContext())
            .active(entity.isActive())
            .description(entity.getDescription())
            .build();
    }

    public WorkflowDefinition toEntity() {
        WorkflowDefinition entity = new WorkflowDefinition();
        entity.setCode(this.code);
        entity.setDesignationFr(this.designationFr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationAr(this.designationAr);
        entity.setVersion(this.version);
        entity.setBusinessContext(this.businessContext);
        entity.setActive(this.active);
        entity.setDescription(this.description);
        return entity;
    }

    public void updateEntity(WorkflowDefinition entity) {
        entity.setCode(this.code);
        entity.setDesignationFr(this.designationFr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationAr(this.designationAr);
        entity.setVersion(this.version);
        entity.setBusinessContext(this.businessContext);
        entity.setActive(this.active);
        entity.setDescription(this.description);
    }
}
