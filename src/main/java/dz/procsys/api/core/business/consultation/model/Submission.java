/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Submission
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.procsys.api.core.business.consultation.model;

import java.math.BigDecimal;
import java.util.Date;

import dz.procsys.api.core.business.provider.model.Provider;
import dz.procsys.api.core.system.utility.model.File;
import dz.procsys.api.core.workflow.execution.model.WorkflowInstance;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Submission Entity - Extends GenericModel.
 * Represents a provider's submission (offer) to a consultation.
 */
@Schema(
    name = "Submission",
    description = "Represents a provider's submission to a consultation, including offer amount and attached parts"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Submission")
@Table(
    name = "T_02_04_05",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_04_05_UK_01", columnNames = { "F_03", "F_04" })
    }
)
public class Submission extends GenericModel {
	
    @Schema(
        description = "Date when the submission was received",
        example = "2025-02-18",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_01")
    private Date submissionDate;
	
    @Schema(
        description = "Total financial offer amount submitted by the provider",
        example = "4300000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Financial offer must be positive or zero")
    @Column(name = "F_02", precision = 18, scale = 2)
    private BigDecimal financialOffer;
	
    @Schema(
        description = "Consultation to which this submission belongs",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Consultation is mandatory for submission")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_01"),
        nullable = false
    )
    private Consultation consultation;
	
    @Schema(
        description = "Provider (tenderer) who submitted this offer",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Provider (tenderer) is mandatory for submission")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_02"),
        nullable = false
    )
    private Provider tender;
	
    @Schema(
        description = "File representing the administrative part of the submission",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_03"),
        nullable = true
    )
    private File administrativePart;
	
    @Schema(
        description = "File representing the technical part of the submission",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_04"),
        nullable = true
    )
    private File technicalPart;
	
    @Schema(
        description = "File representing the financial part of the submission",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_07",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_05"),
        nullable = true
    )
    private File financialPart;
	
    @Schema(
        description = "Workflow instance tracking the evaluation and approval of this submission",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_05_FK_06"),
        nullable = true
    )
    private WorkflowInstance workflowInstance;
}