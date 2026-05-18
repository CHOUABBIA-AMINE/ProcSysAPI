/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetItem
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

import java.util.HashSet;
import java.util.Set;

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
 * BudgetItem Entity - Extends GenericModel.
 * Represents a detailed budget item under a given budget rubric.
 */
@Schema(
    name = "BudgetItem",
    description = "Represents a detailed budget item belonging to a budget rubric and used by planned items"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BudgetItem")
@Table(
    name = "T_02_02_06",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_02_06_UK_01", columnNames = { "F_02" })
    }
)
public class BudgetItem extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the budget item",
        example = "بند ميزاني 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the budget item",
        example = "Budget item 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the budget item (primary label)",
        example = "Article budgétaire 1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Budget rubric this item belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Budget rubric is mandatory for budget item")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_06_FK_01"),
        nullable = false
    )
    private BudgetRubric budgetRubric;
	
    @Schema(
        description = "Planned items that use this budget item",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "item")
    private Set<PlannedItem> plannedItems = new HashSet<>();
}