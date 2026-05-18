/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistribution
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.procsys.api.core.business.plan.model;

import dz.procsys.api.core.common.administration.model.Structure;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ItemDistribution Entity - Extends GenericModel.
 * Represents the distribution of a planned item's quantity to a specific structure.
 */
@Schema(
    name = "ItemDistribution",
    description = "Represents the distribution of a planned item's quantity to a specific organizational structure"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ItemDistribution")
@Table(name = "T_02_02_09")
public class ItemDistribution extends GenericModel {
	
    @Schema(
        description = "Quantity allocated to the given structure from the planned item",
        example = "5.0",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @PositiveOrZero(message = "Quantity must be positive or zero")
    @Column(name = "F_01")
    private float quantity;
	
    @Schema(
        description = "Planned item from which this quantity is distributed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Planned item is mandatory for item distribution")
    @ManyToOne
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_09_FK_01"),
        nullable = false
    )
    private PlannedItem plannedItem;
	
    @Schema(
        description = "Structure (department, directorate, etc.) receiving the quantity",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Structure is mandatory for item distribution")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_02_09_FK_02"),
        nullable = false
    )
    private Structure structure;
}