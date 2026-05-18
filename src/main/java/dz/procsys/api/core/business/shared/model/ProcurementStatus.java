/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementStatus
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
 * ProcurementStatus Entity - Extends GenericModel.
 *
 * Database table: T_02_01_05.
 */
@Schema(
    name = "ProcurementStatus",
    description = "Reference entity describing the status of procurements (on hold, ongoing, finalized, etc.)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcurementStatus")
@Table(
    name = "T_02_01_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_01_05_UK_01", columnNames = { "F_03" })
    }
)
public class ProcurementStatus extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the procurement status",
        example = "قيد الانتظار",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the procurement status",
        example = "On Hold",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the procurement status (primary, unique label)",
        example = "En instance",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
}