/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItem
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.procsys.api.core.business.contract.model;

import java.math.BigDecimal;

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
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ContractItem Entity - Extends GenericModel.
 * Represents a line item of a contract with quantity and unit price.
 */
@Schema(
    name = "ContractItem",
    description = "Represents a contract line item with designation, reference, quantity, unit price and link to its contract"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ContractItem")
@Table(
    name = "T_02_05_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_05_05_UK_01", columnNames = { "F_02", "F_06" })
    }
)
public class ContractItem extends GenericModel {
	
    @Schema(
        description = "Designation/description of the contract item",
        example = "Supply of laptops",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Designation is mandatory")
    @Column(name = "F_01", nullable = false)
    private String designation;
	
    @Schema(
        description = "Reference or code of the contract item (unique within the contract)",
        example = "IT-LAPTOP-01",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Reference is mandatory")
    @Column(name = "F_02", nullable = false)
    private String reference;
	
    @Schema(
        description = "Quantity contracted for this item",
        example = "25",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Quantity must be positive or zero")
    @Column(name = "F_03")
    private double quantity;
	
    @Schema(
        description = "Unit price of this item in contract currency",
        example = "150000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Unit price must be positive or zero")
    @Column(name = "F_04", precision = 18, scale = 2)
    private BigDecimal unitPrice;
	
    @Schema(
        description = "Additional notes about this contract item",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Observation must not exceed 500 characters")
    @Column(name = "F_05")
    private String observation;
	
    @Schema(
        description = "Contract to which this item belongs",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Contract is mandatory for contract item")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_05_FK_01"),
        nullable = false
    )
    private Contract contract;
}