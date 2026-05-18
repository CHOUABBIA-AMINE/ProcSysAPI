/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetRubric
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
 * BudgetRubric Entity - Extends GenericModel.
 * Represents a budget rubric within a budget domain.
 */
@Schema(
    name = "BudgetRubric",
    description = "Reference entity describing budget rubrics belonging to a budget domain"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BudgetRubric")
@Table(
    name = "T_02_02_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_02_05_UK_01", columnNames = { "F_03" })
    }
)
public class BudgetRubric extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the budget rubric",
        example = "باب الميزانية 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the budget rubric",
        example = "Budget rubric 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the budget rubric (primary, unique label)",
        example = "Rubrique budgétaire 1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Budget domain this rubric belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Budget domain is mandatory for budget rubric")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_05_FK_01"),
        nullable = false
    )
    private BudgetDomain budgetDomain;
	
    @Schema(
        description = "Budget items associated with this rubric",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "rubric")
    private List<BudgetItem> budgetItems;
}