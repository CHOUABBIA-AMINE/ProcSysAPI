/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStep
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Amendment
 *
 **/

package dz.procsys.api.core.business.amendment.model;

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
 * AmendmentStep Entity - Extends GenericModel.
 * Represents a detailed step within a given amendment phase in the lifecycle.
 */
@Schema(
    name = "AmendmentStep",
    description = "Reference entity describing detailed steps of the amendment lifecycle, grouped by amendment phases"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AmendmentStep")
@Table(
    name = "T_02_06_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_06_03_UK_01", columnNames = { "F_03" })
    }
)
public class AmendmentStep extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the amendment step",
        example = "إرسال مشروع الملحق",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the amendment step",
        example = "Send amendment draft",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the amendment step (primary, unique label)",
        example = "Envoi du projet d'avenant",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Phase to which this amendment step belongs",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Amendment phase is mandatory for amendment step")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        foreignKey = @ForeignKey(name = "T_02_06_03_FK_01"),
        nullable = false
    )
    private AmendmentPhase amendmentPhase;
}