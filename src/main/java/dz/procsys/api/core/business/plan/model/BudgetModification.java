/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModification
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

import java.util.Date;
import java.util.List;

import dz.procsys.api.core.common.document.model.Document;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BudgetModification Entity - Extends GenericModel.
 * Represents a modification of the budget plan, linked to request/response documents and planned items.
 */
@Schema(
    name = "BudgetModification",
    description = "Represents a budget modification decision with object, description, approval date, related documents and impacted planned items"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BudgetModification")
@Table(
    name = "T_02_02_07",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_02_07_UK_01", columnNames = { "F_03", "F_04" })
    }
)
public class BudgetModification extends GenericModel {
	
    @Schema(
        description = "Object/subject of the budget modification",
        example = "Reallocation of budget for IT equipment",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Object must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String object;

    @Schema(
        description = "Detailed description of the modification",
        example = "Transfer of remaining funds from rubric X to rubric Y for additional equipment.",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_02", length = 500)
    private String description;
	
    @Schema(
        description = "Approval date of the budget modification",
        example = "2025-03-15",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03")
    private Date approvalDate;
	
    @Schema(
        description = "Request document that initiated the budget modification",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Demande document is mandatory for budget modification")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_07_FK_01"),
        nullable = false
    )
    private Document demande;
	
    @Schema(
        description = "Response document (decision) for the budget modification",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Response document is mandatory for budget modification")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_07_FK_02"),
        nullable = false
    )
    private Document response;

    @Schema(
        description = "Planned items impacted by this budget modification",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020207_T020208",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020207_T020208_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020207_T020208_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020207_T020208_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<PlannedItem> plannedItems;
}