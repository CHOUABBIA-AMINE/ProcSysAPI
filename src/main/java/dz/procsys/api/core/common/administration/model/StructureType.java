/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: StructureType
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * StructureType Entity - Extends GenericModel.
 * Represents types of organizational structures with multilingual designations and acronyms.
 */
@Schema(
    name = "StructureType",
    description = "Reference entity describing types of organizational structures (e.g., Direction, Division, Service)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "StructureType")
@Table(
    name = "T_01_04_06",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_04_06_UK_01", columnNames = { "F_03" })
    }
)
public class StructureType extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the structure type",
        example = "مديرية",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the structure type",
        example = "Directorate",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the structure type (primary label)",
        example = "Direction",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Arabic acronym for the structure type",
        example = "مد",
        maxLength = 10
    )
    @Size(max = 10, message = "Arabic acronym must not exceed 10 characters")
    @Column(name = "F_04", length = 10)
    private String acronymAr;

    @Schema(
        description = "English acronym for the structure type",
        example = "DIR",
        maxLength = 10
    )
    @Size(max = 10, message = "English acronym must not exceed 10 characters")
    @Column(name = "F_05", length = 10)
    private String acronymEn;
	
    @Schema(
        description = "French acronym for the structure type",
        example = "DIR",
        maxLength = 10
    )
    @Size(max = 10, message = "French acronym must not exceed 10 characters")
    @Column(name = "F_06", length = 10)
    private String acronymFr;
}