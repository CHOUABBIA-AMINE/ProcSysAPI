/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementDirector
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Shared
 *
 **/

package dz.procsys.api.core.business.shared.model;

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
 * ProcurementDirector Entity - Extends GenericModel.
 *
 * Database table: T_02_01_03.
 */
@Schema(
    name = "ProcurementDirector",
    description = "Reference entity listing procurement directorates involved in procurement processes"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcurementDirector")
@Table(
    name = "T_02_01_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_01_03_UK_01", columnNames = { "F_03" })
    }
)
public class ProcurementDirector extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the procurement directorate",
        example = "المديرية الفرعية للإنجازات",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    @Column(name = "F_01", length = 300)
    private String designationAr;

    @Schema(
        description = "English designation of the procurement directorate",
        example = "Sub Directorate of Procurements",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "English designation must not exceed 300 characters")
    @Column(name = "F_02", length = 300)
    private String designationEn;
	
    @Schema(
        description = "French designation of the procurement directorate (primary, unique label)",
        example = "Sous Direction Réalisations",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 300
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    @Column(name = "F_03", length = 300, nullable = false)
    private String designationFr;
}