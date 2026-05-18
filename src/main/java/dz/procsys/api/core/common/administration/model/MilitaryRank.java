/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRank
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.procsys.api.core.common.administration.model;

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
 * MilitaryRank Entity - Extends GenericModel.
 * Represents military ranks with multilingual designations and category.
 */
@Schema(
    name = "MilitaryRank",
    description = "Reference entity describing military ranks with multilingual designations, acronyms and category"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MilitaryRank")
@Table(
    name = "T_01_04_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_04_05_UK_01", columnNames = { "F_03" })
    }
)
public class MilitaryRank extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the military rank",
        example = "جنرال",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the military rank",
        example = "General",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the military rank (primary label)",
        example = "Général",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Arabic acronym of the military rank",
        example = "جن",
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic acronym must not exceed 100 characters")
    @Column(name = "F_04", length = 100)
    private String acronymAr;

    @Schema(
        description = "English acronym of the military rank",
        example = "GEN",
        maxLength = 100
    )
    @Size(max = 100, message = "English acronym must not exceed 100 characters")
    @Column(name = "F_05", length = 100)
    private String acronymEn;
	
    @Schema(
        description = "French acronym of the military rank",
        example = "Gén.",
        maxLength = 100
    )
    @Size(max = 100, message = "French acronym must not exceed 100 characters")
    @Column(name = "F_06", length = 100)
    private String acronymFr;
	
    @Schema(
        description = "Military category this rank belongs to (e.g., officer, NCO, troop)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Military category is mandatory for rank")
    @ManyToOne
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_05_FK_01"),
        nullable = false
    )
    private MilitaryCategory militaryCategory;
}