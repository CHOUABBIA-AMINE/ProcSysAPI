/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItem
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

import java.math.BigDecimal;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
 * PlannedItem Entity - Extends GenericModel.
 * Represents a planned budget item with quantities and amounts for a given financial operation.
 */
@Schema(
    name = "PlannedItem",
    description = "Represents a planned budget item with quantities and amounts linked to a budget item, status and financial operation"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PlannedItem")
@Table(name = "T_02_02_08")
public class PlannedItem extends GenericModel {
	
    @Schema(
        description = "Designation of the planned item",
        example = "Acquisition of IT equipment",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Designation is mandatory")
    @Size(max = 200, message = "Designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String designation;
	
    @Schema(
        description = "Unit cost of the planned item",
        example = "150000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Unit cost must be positive or zero")
    @Column(name = "F_02", precision = 18, scale = 2)
    private BigDecimal unitairCost;
	
    @Schema(
        description = "Planned quantity",
        example = "10",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @PositiveOrZero(message = "Planned quantity must be positive or zero")
    @Column(name = "F_03")
    private double planedQuantity;
	
    @Schema(
        description = "Allocated amount for this planned item",
        example = "1500000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Allocated amount must be positive or zero")
    @Column(name = "F_04", precision = 18, scale = 2)
    private BigDecimal allocatedAmount;
	
    @Schema(
        description = "Status of the planned item (planned, committed, closed, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Item status is mandatory for planned item")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_08_FK_01"),
        nullable = false
    )
    private ItemStatus itemStatus;
	
    @Schema(
        description = "Budget item to which this planned item refers",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Budget item is mandatory for planned item")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_08_FK_02"),
        nullable = false
    )
    private BudgetItem budgetItem;
	
    @Schema(
        description = "Financial operation (budget year and type) that carries this planned item",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Financial operation is mandatory for planned item")
    @ManyToOne
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_08_FK_03"),
        nullable = false
    )
    private FinancialOperation financialOperation;
}