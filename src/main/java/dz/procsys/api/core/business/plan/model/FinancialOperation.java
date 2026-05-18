/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperation
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * FinancialOperation Entity - Extends GenericModel.
 * Represents a financial operation for a given budget year and type.
 */
@Schema(
    name = "FinancialOperation",
    description = "Represents a financial operation associated with a specific budget year and budget type"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FinancialOperation")
@Table(
    name = "T_02_02_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_02_03_UK_01", columnNames = { "F_01" })
    }
)
public class FinancialOperation extends GenericModel {
	
    @Schema(
        description = "Label of the financial operation",
        example = "Budget initial 2025",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Operation is mandatory")
    @Size(max = 200, message = "Operation must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String operation;
	
    @Schema(
        description = "Budget year of the operation (YYYY)",
        example = "2025",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 4
    )
    @NotBlank(message = "Budget year is mandatory")
    @Size(min = 4, max = 4, message = "Budget year must be exactly 4 characters")
    @Pattern(regexp = "\\d{4}", message = "Budget year must be a 4-digit year")
    @Column(name = "F_02", length = 4, nullable = false)
    private String budgetYear;
	
    @Schema(
        description = "Budget type associated with this operation (initial, supplementary, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Budget type is mandatory for financial operation")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_03_FK_01"),
        nullable = false
    )
    private BudgetType budgetType;
}