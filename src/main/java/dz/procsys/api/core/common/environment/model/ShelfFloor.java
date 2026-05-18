/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloor
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.procsys.api.core.common.environment.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
 * ShelfFloor Entity - Extends GenericModel.
 * Represents floors/levels within shelves with multilingual designations.
 */
@Schema(
    name = "ShelfFloor",
    description = "Reference entity describing shelf levels (floors) with numeric order, code and multilingual designations"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ShelfFloor")
@Table(
    name = "T_01_01_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_01_05_UK_01", columnNames = { "F_01" }),
        @UniqueConstraint(name = "T_01_01_05_UK_02", columnNames = { "F_02" }),
        @UniqueConstraint(name = "T_01_01_05_UK_03", columnNames = { "F_05" })
    }
)
public class ShelfFloor extends GenericModel {
	
    @Schema(
        description = "Numeric level of the shelf floor (1 = lowest, increasing upwards)",
        example = "1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Shelf floor level is mandatory")
    @Min(value = 1, message = "Shelf floor level must be at least 1")
    @Max(value = 50, message = "Shelf floor level must not exceed 50")
    @Column(name = "F_01")
    private Integer floorLevel;
	
    @Schema(
        description = "Shelf floor code (e.g., N1, N2, TOP)",
        example = "N1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Shelf floor code is mandatory")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "F_02", length = 20, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the shelf floor",
        example = "الرف الأول",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200)
    private String designationAr;
	
    @Schema(
        description = "English designation of the shelf floor",
        example = "First shelf level",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_04", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the shelf floor (primary label)",
        example = "Niveau 1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_05", length = 200, nullable = false)
    private String designationFr;
}