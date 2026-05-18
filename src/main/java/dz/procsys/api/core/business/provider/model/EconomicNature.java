/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNature
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.procsys.api.core.business.provider.model;

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

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EconomicNature")
@Table(
    name = "T_02_03_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_03_02_UK_01", columnNames = { "F_03" }),
        @UniqueConstraint(name = "T_02_03_02_UK_02", columnNames = { "F_06" })
    }
)
@Schema(
    name = "EconomicNature",
    description = "Reference entity describing the economic nature of providers (manufacturer, distributor, etc.)"
)
public class EconomicNature extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the economic nature",
        example = "مصنِّع",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the economic nature",
        example = "Manufacturer",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the economic nature (primary, unique label)",
        example = "Fabricant",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Arabic acronym for the economic nature",
        example = "مص",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    @Column(name = "F_04", length = 20)
    private String acronymAr;
	
    @Schema(
        description = "English acronym for the economic nature",
        example = "MFG",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    @Column(name = "F_05", length = 20)
    private String acronymEn;
	
    @Schema(
        description = "French acronym for the economic nature (unique)",
        example = "FAB",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "French acronym is mandatory")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    @Column(name = "F_06", length = 20, nullable = false)
    private String acronymFr;
}