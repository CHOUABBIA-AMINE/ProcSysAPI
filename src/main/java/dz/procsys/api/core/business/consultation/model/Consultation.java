/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Consultation
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
import java.util.List;

import dz.procsys.api.core.business.plan.model.BudgetType;
import dz.procsys.api.core.business.plan.model.PlannedItem;
import dz.procsys.api.core.business.shared.model.ApprovalStatus;
import dz.procsys.api.core.business.shared.model.ProcurementDirector;
import dz.procsys.api.core.business.shared.model.ProcurementNature;
import dz.procsys.api.core.business.shared.model.ProcurementStatus;
import dz.procsys.api.core.common.communication.model.Mail;
import dz.procsys.api.core.common.document.model.Document;
import dz.procsys.api.core.workflow.execution.model.WorkflowInstance;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Consultation Entity - Extends GenericModel.
 * Represents a procurement consultation with identity, financial data, dates and related references.
 */
@Schema(
    name = "Consultation",
    description = "Represents a procurement consultation with identification, financial data, dates, status and related documents, mails and planned items"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Consultation")
@Table(
    name = "T_02_04_04",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_04_04_UK_01", columnNames = { "F_01", "F_02" })
    }
)
public class Consultation extends GenericModel {
	
    @Schema(
        description = "Internal identifier of the consultation (3-digit code per year)",
        example = "015",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 3
    )
    @NotBlank(message = "Internal id is mandatory")
    @Size(max = 3, message = "Internal id must not exceed 3 characters")
    @Pattern(regexp = "\\d{1,3}", message = "Internal id must be up to 3 digits")
    @Column(name = "F_01", length = 3, nullable = false)
    private String internalId;
	
    @Schema(
        description = "Year of the consultation (YYYY)",
        example = "2025",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 4
    )
    @NotBlank(message = "Consultation year is mandatory")
    @Size(min = 4, max = 4, message = "Consultation year must be exactly 4 characters")
    @Pattern(regexp = "\\d{4}", message = "Consultation year must be a 4-digit year")
    @Column(name = "F_02", length = 4, nullable = false)
    private String consultationYear;
	
    @Schema(
        description = "External reference of the consultation (if any)",
        example = "CONS-2025-015",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Reference must not exceed 20 characters")
    @Column(name = "F_03", length = 20)
    private String reference;
	
    @Schema(
        description = "Arabic designation of the consultation object",
        example = "اقتناء تجهيزات معلوماتية",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    @Column(name = "F_04", length = 300)
    private String designationAr;

    @Schema(
        description = "English designation of the consultation object",
        example = "Acquisition of IT equipment",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "English designation must not exceed 300 characters")
    @Column(name = "F_05", length = 300)
    private String designationEn;
	
    @Schema(
        description = "French designation of the consultation object (primary label)",
        example = "Acquisition de matériels informatiques",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 300
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    @Column(name = "F_06", length = 300, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Amount allocated in the budget to this consultation",
        example = "5000000.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Allocated amount is mandatory")
    @PositiveOrZero(message = "Allocated amount must be positive or zero")
    @Column(name = "F_07", precision = 18, scale = 2, nullable = false)
    private BigDecimal allocatedAmount;
	
    @Schema(
        description = "Financial estimation of the consultation",
        example = "4500000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Financial estimation must be positive or zero")
    @Column(name = "F_08", precision = 18, scale = 2)
    private BigDecimal financialEstimation;
	
    @Schema(
        description = "Start date of the consultation process",
        example = "2025-02-01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_09")
    private Date startDate;
	
    @Schema(
        description = "Reference of the approval decision authorizing the consultation",
        example = "DEC-2025-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_10")
    private String approvalReference;
	
    @Schema(
        description = "Date of the approval decision authorizing the consultation",
        example = "2025-01-20",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_11")
    private Date approvalDate;
	
    @Schema(
        description = "Date when the consultation was published",
        example = "2025-02-05",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_12")
    private Date publishDate;
	
    @Schema(
        description = "Deadline for submission of offers",
        example = "2025-02-20",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_13")
    private Date deadline;

    @Schema(
        description = "Additional notes or observations about the consultation",
        maxLength = 500,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 500, message = "Observation must not exceed 500 characters")
    @Column(name = "F_14", length = 500)
    private String observation;
	
    @Schema(
        description = "Award method applied to this consultation",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Award method is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_15",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_01"),
        nullable = false
    )
    private AwardMethod awardMethod;
	
    @Schema(
        description = "Procurement nature of this consultation (services, works, supplies, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Procurement nature is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_16",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_02"),
        nullable = false
    )
    private ProcurementNature procurementNature;
	
    @Schema(
        description = "Budget type used for financing this consultation",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Budget type is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_17",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_03"),
        nullable = false
    )
    private BudgetType budgetType;
	
    @Schema(
        description = "Procurement status of the consultation (on hold, ongoing, finalized, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Procurement status is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_18",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_04"),
        nullable = false
    )
    private ProcurementStatus procurementStatus;
	
    @Schema(
        description = "Approval status within internal validation workflow (visa, under preparation, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Approval status is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_19",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_05"),
        nullable = false
    )
    private ApprovalStatus approvalStatus;
	
    @Schema(
        description = "Procurement directorate responsible for this consultation",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Procurement director is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_20",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_06"),
        nullable = false
    )
    private ProcurementDirector procurementDirector;
	
    @Schema(
        description = "Current step of the consultation process",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Consultation step is mandatory for consultation")
    @ManyToOne
    @JoinColumn(
        name = "F_21",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_07"),
        nullable = false
    )
    private ConsultationStep consultationStep;
	
    @Schema(
        description = "Workflow instance tracking the consultation process",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_22",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_04_04_FK_08"),
        nullable = true
    )
    private WorkflowInstance workflowInstance;
	
    @Schema(
        description = "Documents attached to the consultation (terms of reference, decisions, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020404_T010302",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T010302_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T010302_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020404_T010302_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Document> documents;
	
    @Schema(
        description = "Mails linked to the consultation (notifications, invitations, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020404_T010203",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T010203_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T010203_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020404_T010203_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Mail> mails;
	
    @Schema(
        description = "Planned items financed and executed through this consultation",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020404_T020208",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T020208_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020404_T020208_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020404_T020208_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<PlannedItem> plannedItems;
	
    @Schema(
        description = "Submissions received in response to this consultation",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "consultation")
    private List<Submission> submissions;
}