/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Floor
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Floor Entity - Extends GenericModel.
 * Represents a floor in a building bloc with multilingual designations.
 */
@Schema(
    name = "Floor",
    description = "Reference entity describing a floor in a building bloc with code and multilingual designations"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Floor")
@Table(name = "T_01_01_02")
public class Floor extends GenericModel {
	
    @Schema(
        description = "Floor code (e.g., RDC, R+1, R+2)",
        example = "R+1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Floor code is mandatory")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the floor",
        example = "الطابق الأول",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationAr;
	
    @Schema(
        description = "English designation of the floor",
        example = "First floor",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the floor (primary label)",
        example = "1er étage",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_04", length = 200, nullable = false)
    private String designationFr;
}