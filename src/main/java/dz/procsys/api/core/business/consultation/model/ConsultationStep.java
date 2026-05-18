/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStep
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
 * ConsultationStep Entity - Extends GenericModel.
 * Represents a detailed step within a consultation phase.
 */
@Schema(
    name = "ConsultationStep",
    description = "Reference entity describing a detailed step within a consultation phase"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ConsultationStep")
@Table(
    name = "T_02_04_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_04_03_UK_01", columnNames = { "F_03" })
    }
)
public class ConsultationStep extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the consultation step",
        example = "إطلاق الاستشارة",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationAr;

    @Schema(
        description = "English designation of the consultation step",
        example = "Consultation launch",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationEn;
	
    @Schema(
        description = "French designation of the consultation step (primary, unique label)",
        example = "Lancement de la consultation",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    @Column(name = "F_03", length = 200, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Phase this step belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Consultation phase is mandatory for consultation step")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        foreignKey = @ForeignKey(name = "T_02_04_03_FK_01"),
        nullable = false
    )
    private ConsultationPhase consultationPhase;
}