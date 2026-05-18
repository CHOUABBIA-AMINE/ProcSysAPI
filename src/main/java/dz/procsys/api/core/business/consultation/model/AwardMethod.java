/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethod
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.procsys.api.core.business.consultation.model;

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
 * AwardMethod Entity - Extends GenericModel.
 * Represents the method of award used in consultation and tendering processes.
 */
@Schema(
    name = "AwardMethod",
    description = "Reference entity describing methods of award used in consultations (e.g. lowest price, best offer)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AwardMethod")
@Table(
    name = "T_02_04_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_04_01_UK_01", columnNames = { "F_03" }),
        @UniqueConstraint(name = "T_02_04_01_UK_02", columnNames = { "F_06" })
    }
)
public class AwardMethod extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the award method",
        example = "أدنى عرض",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 150
    )
    @Size(max = 150, message = "Arabic designation must not exceed 150 characters")
    @Column(name = "F_01", length = 150)
    private String designationAr;

    @Schema(
        description = "English designation of the award method",
        example = "Lowest evaluated bid",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 150
    )
    @Size(max = 150, message = "English designation must not exceed 150 characters")
    @Column(name = "F_02", length = 150)
    private String designationEn;
	
    @Schema(
        description = "French designation of the award method (primary, unique label)",
        example = "Offre la moins disante",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 150
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 150, message = "French designation must not exceed 150 characters")
    @Column(name = "F_03", length = 150, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Arabic acronym of the award method",
        example = "أع",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    @Column(name = "F_04", length = 20)
    private String acronymAr;
	
    @Schema(
        description = "English acronym of the award method",
        example = "LAB",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    @Column(name = "F_05", length = 20)
    private String acronymEn;
	
    @Schema(
        description = "French acronym of the award method (unique)",
        example = "LMD",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "French acronym is mandatory")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    @Column(name = "F_06", length = 20, nullable = false)
    private String acronymFr;
}