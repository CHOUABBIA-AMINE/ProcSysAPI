/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStep
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.procsys.api.core.business.contract.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ContractStep Entity - Extends GenericModel.
 * Represents a detailed step within a contract phase.
 */
@Schema(
    name = "ContractStep",
    description = "Reference entity describing a detailed step within a contract phase"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ContractStep")
@Table(
    name = "T_02_05_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_05_03_UK_01", columnNames = { "F_03" })
    }
)
public class ContractStep extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the contract step",
        example = "إشعار المتعامل المتعاقد",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the contract step",
        example = "Contractor notification",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the contract step (primary, unique label)",
        example = "Notification du titulaire",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Phase this contract step belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Contract phase is mandatory for contract step")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_03_FK_01"),
        nullable = false
    )
    private ContractPhase contractPhase;
}