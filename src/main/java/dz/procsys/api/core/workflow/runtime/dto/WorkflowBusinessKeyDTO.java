/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowBusinessKeyDTO
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Workflow / Runtime
 *
 *	Description : Flat input DTO used as parameter to WorkflowEngine#startInstance.
 *	              Carries only scalar identifiers and codes — no entity references,
 *	              no @ManyToOne, no nested objects.
 *
 **/

package dz.procsys.api.core.workflow.runtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowBusinessKeyDTO — start-instance command parameters.
 *
 * Fields:
 *  - processTypeCode : code of the WorkflowProcessType reference entity
 *  - businessKey     : string representation of the owning aggregate PK
 *  - startedBy       : username of the actor who triggers the start
 *  - observation     : optional free-text observation for the first step
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "WorkflowBusinessKeyDTO",
        description = "Input DTO for WorkflowEngine#startInstance — flat scalars only")
public class WorkflowBusinessKeyDTO {

    @Schema(description = "Code of the WorkflowProcessType reference (e.g. CONSULTATION, CONTRACT)",
            example = "CONSULTATION", requiredMode = Schema.RequiredMode.REQUIRED)
    private String processTypeCode;

    @Schema(description = "String representation of the owning aggregate primary key",
            example = "42", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessKey;

    @Schema(description = "Username of the actor starting the workflow",
            example = "a.chouabbia", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startedBy;

    @Schema(description = "Optional free-text observation recorded on the first step")
    private String observation;
}
