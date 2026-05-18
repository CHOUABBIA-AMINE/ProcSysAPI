/**
 *
 * @author     : CHOUABBIA Amine
 *
 * @Name       : Amendment
 * @CreatedOn  : 06-26-2025
 * @Updated    : 12-10-2025
 *
 * @Type       : Class
 * @Layer      : Model
 * @Package    : Business / Amendment
 *
 **/

package dz.procsys.api.core.business.amendment.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dz.procsys.api.core.business.contract.model.Contract;
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Amendment Entity - Extends GenericModel.
 * Represents a contract amendment with identification, financial impact, key dates and links to statuses and workflow.
 */
@Schema(
    name = "Amendment",
    description = "Contract amendment holding process-related, financial and status data"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Amendment")
@Table(
    name = "T_02_06_04",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_06_04_UK_01", columnNames = { "F_02" })
    }
)
public class Amendment extends GenericModel {

    @Schema(
        description = "Technical internal numeric identifier of the amendment",
        example = "1",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_01")
    private int internalId;

    @Schema(
        description = "Business reference of the amendment (unique when present)",
        example = "AM-01-CT-01-2016",
        maxLength = 25,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 25, message = "Reference must not exceed 25 characters")
    @Column(name = "F_02", length = 25, nullable = true)
    private String reference;

    @Schema(
        description = "Arabic designation or subject of the amendment",
        maxLength = 300,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    @Column(name = "F_03", length = 300)
    private String designationAr;

    @Schema(
        description = "English designation or subject of the amendment",
        maxLength = 300,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 300, message = "English designation must not exceed 300 characters")
    @Column(name = "F_04", length = 300)
    private String designationEn;

    @Schema(
        description = "French designation or subject of the amendment",
        maxLength = 300,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    @Column(name = "F_05", length = 300)
    private String designationFr;

    @Schema(
        description = "Financial impact amount of the amendment",
        example = "150000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Amount must be positive or zero")
    @Column(name = "F_06", precision = 18, scale = 2, nullable = true)
    private BigDecimal amount;

    @Schema(
        description = "Transferable amount within the amendment, if applicable",
        example = "30000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PositiveOrZero(message = "Transferable amount must be positive or zero")
    @Column(name = "F_07", precision = 18, scale = 2, nullable = true)
    private BigDecimal transferableAmount;

    @Schema(
        description = "Date from which the amendment takes effect",
        example = "2025-07-01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_08")
    private Date startDate;

    @Schema(
        description = "Date on which the amendment is approved",
        example = "2025-06-20",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_09")
    private Date approvalDate;

    @Schema(
        description = "Date on which the amendment is notified to the contractor",
        example = "2025-06-25",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_10")
    private Date notifyDate;

    @Schema(
        description = "Additional notes or observations about the amendment",
        maxLength = 500,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 500, message = "Observation must not exceed 500 characters")
    @Column(name = "F_11", length = 500)
    private String observation;

    @Schema(
        description = "Contract to which this amendment is attached",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Contract is mandatory for amendment")
    @ManyToOne
    @JoinColumn(
        name = "F_12",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_01"),
        nullable = false
    )
    private Contract contract;

    @Schema(
        description = "Type of the amendment (amount increase, time extension, scope change, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Amendment type is mandatory for amendment")
    @ManyToOne
    @JoinColumn(
        name = "F_13",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_02"),
        nullable = false
    )
    private AmendmentType amendmentType;

    @Schema(
        description = "Procurement status of the amendment",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Procurement status is mandatory for amendment")
    @ManyToOne
    @JoinColumn(
        name = "F_14",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_03"),
        nullable = false
    )
    private ProcurementStatus procurementStatus;

    @Schema(
        description = "Current step of the amendment process",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Amendment step is mandatory for amendment")
    @ManyToOne
    @JoinColumn(
        name = "F_15",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_04"),
        nullable = false
    )
    private AmendmentStep amendmentStep;

    @Schema(
        description = "Approval status of the amendment (if applicable)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_16",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_05"),
        nullable = true
    )
    private ApprovalStatus approvalStatus;

    @Schema(
        description = "Currency in which the amendment amounts are expressed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Currency is mandatory for amendment")
    @ManyToOne
    @JoinColumn(
        name = "F_17",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_06_04_FK_06"),
        nullable = false
    )
    private Currency currency;

    @Schema(
        description = "Workflow instance tracking the amendment approval process",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_18",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_05_01_FK_09"),
        nullable = true
    )
    private WorkflowInstance workflowInstance;

    @Schema(
        description = "Documents associated with this amendment (drafts, approvals, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020604_T010302",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020604_T010302_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020604_T010302_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020604_T010302_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Document> documents;

    @Schema(
        description = "Mails linked to this amendment (notifications, correspondence, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020604_T010203",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020604_T010203_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020604_T010203_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020604_T010203_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Mail> mails;
}