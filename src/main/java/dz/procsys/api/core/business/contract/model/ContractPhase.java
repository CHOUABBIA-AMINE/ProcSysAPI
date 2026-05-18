/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhase
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.procsys.api.core.business.contract.model;

import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ContractPhase Entity - Extends GenericModel.
 * Represents a high-level phase in the contract lifecycle.
 */
@Schema(
    name = "ContractPhase",
    description = "Reference entity describing high-level phases of the contract lifecycle (e.g. preparation, execution, closure)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ContractPhase")
@Table(
    name = "T_02_05_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_05_02_UK_01", columnNames = { "F_03" })
    }
)
public class ContractPhase extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the contract phase",
        example = "مرحلة التنفيذ",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the contract phase",
        example = "Execution phase",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the contract phase (primary, unique label)",
        example = "Phase d'exécution",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Contract steps associated with this phase",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "contractPhase")
    private List<ContractStep> contractSteps;
}