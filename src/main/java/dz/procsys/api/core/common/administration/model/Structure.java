/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Structure
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
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
 * Structure Entity - Extends GenericModel.
 * Represents organizational structures with multilingual designations and hierarchy.
 */
@Schema(
    name = "Structure",
    description = "Represents an organizational structure (direction, division, service, etc.) with hierarchy and multilingual labels"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Structure")
@Table(
    name = "T_01_04_07",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_04_07_UK_01", columnNames = { "F_01" }),
        @UniqueConstraint(name = "T_01_04_07_UK_02", columnNames = { "F_04" })
    }
)
public class Structure extends GenericModel {
	
    @Schema(
        description = "Structure code (unique identifier within the organization)",
        example = "DIR-OP-NORTH",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Structure code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the structure",
        example = "مديرية العمليات - الشمال",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the structure",
        example = "Operations Directorate - North",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the structure (primary label, unique)",
        example = "Direction des opérations - Nord",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Type of the structure (direction, division, service, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Structure type is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_07_FK_01"),
        nullable = false
    )
    private StructureType structureType;
	
    @Schema(
        description = "Parent structure in the hierarchy (nullable for top-level structures)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_07_FK_02"),
        nullable = true
    )
    private Structure parentStructure;
}