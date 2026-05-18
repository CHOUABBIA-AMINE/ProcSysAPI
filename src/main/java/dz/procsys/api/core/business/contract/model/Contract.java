/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Contract
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.procsys.api.core.business.contract.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dz.procsys.api.core.business.consultation.model.Consultation;
import dz.procsys.api.core.business.plan.model.PlannedItem;
import dz.procsys.api.core.business.provider.model.Provider;
import dz.procsys.api.core.business.shared.model.ApprovalStatus;
import dz.procsys.api.core.business.shared.model.Currency;
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
 * Contract Entity - Extends GenericModel.
 * Represents a procurement contract with identification, financial data, duration and links to provider, consultation and planned items.
 */
@Schema(
    name = "Contract",
    description = "Represents a procurement contract with identification, financial data, duration, status and links to provider, consultation and planned items"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Contract")
@Table(
    name = "T_02_05_04",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_05_04_UK_01", columnNames = { "F_01" })
    }
)
public class Contract extends GenericModel {
	
    @Schema(
        description = "Internal identifier of the contract (unique)",
        example = "CTR-2025-001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Internal id is mandatory")
    @Column(name = "F_01", nullable = false)
    private String internalId;
	
    @Schema(
        description = "Year of the contract (YYYY)",
        example = "2025",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Pattern(regexp = "\\d{4}", message = "Contract year must be a 4-digit year")
    @Column(name = "F_02")
    private String contractYear;
	
    @Schema(
        description = "External reference of the contract",
        example = "REF-CTR-2025-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03")
    private String reference;
	
    @Schema(
        description = "Arabic designation of the contract object",
        example = "عقد اقتناء تجهيزات معلوماتية",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    @Column(name = "F_04", length = 300)
    private String designationAr;

    @Schema(
        description = "English designation of the contract object",
        example = "Contract for acquisition of IT equipment",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "English designation must not exceed 300 characters")
    @Column(name = "F_05", length = 300)
    private String designationEn;
	
    @Schema(
        description = "French designation of the contract object (primary label)",
        example = "Contrat d'acquisition de matériels informatiques",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 300
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    @Column(name = "F_06", length = 300, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Contract amount (initial amount excluding amendments)",
        example = "5000000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Amount must be positive or zero")
    @Column(name = "F_07", precision = 18, scale = 2)
    private BigDecimal amount;
	
    @Schema(
        description = "Transferable amount (part of the contract that can be transferred)",
        example = "500000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Transferable amount must be positive or zero")
    @Column(name = "F_08", precision = 18, scale = 2)
    private BigDecimal transferableAmount;
	
    @Schema(
        description = "Start date of the contract execution period",
        example = "2025-04-01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_09")
    private Date startDate;
	
    @Schema(
        description = "Reference of the approval decision authorizing the contract",
        example = "DEC-CTR-2025-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_10")
    private String approvalReference;
	
    @Schema(
        description = "Date of the approval decision for the contract",
        example = "2025-03-25",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_11")
    private Date approvalDate;
	
    @Schema(
        description = "Date when the contract was signed",
        example = "2025-03-30",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_12")
    private Date contractDate;
	
    @Schema(
        description = "Date when the contract was notified to the provider",
        example = "2025-04-02",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_13")
    private Date notifyDate;
	
    @Schema(
        description = "Contract duration in days",
        example = "365",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_14")
    private int contractDuration;

    @Schema(
        description = "Additional notes or observations about the contract",
        maxLength = 500,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 500, message = "Observation must not exceed 500 characters")
    @Column(name = "F_15", length = 500)
    private String observation;
	
    @Schema(
        description = "Type of the contract (works, services, supplies, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Contract type is mandatory for contract")
    @ManyToOne
    @JoinColumn(
        name = "F_16",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_01"),
        nullable = false
    )
    private ContractType contractType;
	
    @Schema(
        description = "Provider with whom the contract is signed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Provider is mandatory for contract")
    @ManyToOne
    @JoinColumn(
        name = "F_17",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_02"),
        nullable = false
    )
    private Provider provider;

    @Schema(
        description = "Procurement status of the contract (on hold, ongoing, finalized, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Procurement status is mandatory for contract")
    @ManyToOne
    @JoinColumn(
        name = "F_18",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_03"),
        nullable = false
    )
    private ProcurementStatus procurementStatus;
	
    @Schema(
        description = "Current step of the contract lifecycle",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Contract step is mandatory for contract")
    @ManyToOne
    @JoinColumn(
        name = "F_19",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_04"),
        nullable = false
    )
    private ContractStep contractStep;
	
    @Schema(
        description = "Approval status of the contract, if subject to internal visa",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_20",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_05"),
        nullable = true
    )
    private ApprovalStatus approvalStatus;
	
    @Schema(
        description = "Currency in which the contract amount is expressed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Currency is mandatory for contract")
    @ManyToOne
    @JoinColumn(
        name = "F_21",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_06"),
        nullable = false
    )
    private Currency currency;

    @Schema(
        description = "Consultation from which this contract originates, if applicable",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_22",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_07"),
        nullable = true
    )
    private Consultation consultation;

    @Schema(
        description = "Parent contract in case of amendment or addendum",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_23",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_04_FK_08"),
        nullable = true
    )
    private Contract contractUp;
	
    @Schema(
        description = "Workflow instance tracking the contract lifecycle",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_24",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_01_FK_09"),
        nullable = true
    )
    private WorkflowInstance workflowInstance;
	
    @Schema(
        description = "Contract items detailing the quantities and prices under this contract",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "contract")
    private List<ContractItem> contractItems;
	
    @Schema(
        description = "Documents attached to the contract (contract text, decisions, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020504_T010302",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020504_T010302_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020504_T010302_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020504_T010302_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Document> documents;
	
    @Schema(
        description = "Mails linked to the contract (notifications, reminders, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020504_T010203",
        joinColumns = @JoinColumn(
            name = "F_01",
            foreignKey = @ForeignKey(name = "R_T020504_T010203_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            foreignKey = @ForeignKey(name = "R_T020504_T010203_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020504_T010203_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Mail> mails;
	
    @Schema(
        description = "Planned items whose execution is covered by this contract",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020504_T020208",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020504_T020208_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020504_T020208_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020504_T020208_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<PlannedItem> plannedItems;
}