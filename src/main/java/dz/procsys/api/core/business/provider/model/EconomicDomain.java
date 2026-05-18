/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomain
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
@Entity(name = "EconomicDomain")
@Table(
    name = "T_02_03_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_03_01_UK_01", columnNames = { "F_04" })
    }
)
@Schema(
    name = "EconomicDomain",
    description = "Reference entity describing economic domains in which providers can operate"
)
public class EconomicDomain extends GenericModel {
	
    @Schema(
        description = "Domain code",
        example = "DOM-01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "F_01", length = 20)
    private String code;
	
    @Schema(
        description = "Arabic designation of the economic domain",
        example = "المجال الاقتصادي 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the economic domain",
        example = "Economic Domain 1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the economic domain (primary, unique label)",
        example = "Domaine économique 1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_04", length = 200, nullable = false)
    private String designationFr;
}