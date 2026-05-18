/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Amendment
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Amendment
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Contract's amandment holding data related to the process")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Amendment")
@Table(name="T_02_06_04", uniqueConstraints = { @UniqueConstraint(name = "T_02_06_04_UK_01", columnNames = { "F_02" })})
public class Amendment extends GenericModel {
	
	@Schema(
		description = "Internal Id : AM-01-16",
		example = "AM-01-CT-01-2016",
		requiredMode = Schema.RequiredMode.REQUIRED,
		maxLength = 255
	)
	@NotNull(message = "Internal Id is mandatory")
	@Column(name="F_01")
	private int internalId;
	
	@Schema(
		description = "Consultation reference's (e.g., 'AM-01-CT-01-2016')",
		example = "AM-01-CT-01-2016",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED,
		maxLength = 25
	)
	@NotBlank(message = "Reference is mandatory")
	@Size(max = 25, message = "Reference must not exceed 25 characters")
	@Column(name="F_02", nullable=true, length=25)
	private String reference;
	
	@Column(name="F_03", length=300)
	private String designationAr;

	@Column(name="F_04", length=300)
	private String designationEn;
	
	@Column(name="F_05", length=300)
	private String designationFr;
	
	@Column(name="F_06", precision=18, scale=2, nullable=true)
	private BigDecimal amount;
	
	@Column(name="F_07", precision=18, scale=2, nullable=true)
	private BigDecimal transferableAmount;
	
	@Column(name="F_08")
	private Date startDate;
	
	@Column(name="F_09")
	private Date approvalDate;
	
	@Column(name="F_10")
	private Date notifyDate;

	@Column(name="F_11", length=500)
	private String observation;
	
	@ManyToOne
    @JoinColumn(name="F_12", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_01"), nullable=false)
    private Contract contract;
	
	@ManyToOne
    @JoinColumn(name="F_13", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_02"), nullable=false)
    private AmendmentType amendmentType;

	@ManyToOne
    @JoinColumn(name="F_14", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_03"), nullable=false)
    private ProcurementStatus procurementStatus;
	
	@ManyToOne
    @JoinColumn(name="F_15", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_04"), nullable=false)
    private AmendmentStep amendmentStep;
	
	@ManyToOne
    @JoinColumn(name="F_16", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_05"), nullable=true)
    private ApprovalStatus approvalStatus;
	
	@ManyToOne
    @JoinColumn(name="F_17", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_06_04_FK_06"), nullable=false)
    private Currency currency;
	
	@ManyToOne
	@JoinColumn(name="F_18", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_05_01_FK_09"), nullable = true)
	private WorkflowInstance workflowInstance;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "R_T020604_T010302", 
			joinColumns = @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="R_T020604_T010302_FK_01")), 
			inverseJoinColumns = @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="R_T020604_T010302_FK_02")),
			uniqueConstraints = @UniqueConstraint(name = "R_T020604_T010302_UK_01", columnNames = {"F_01", "F_02"}))
	private List<Document> documents;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "R_T020604_T010203", 
			joinColumns = @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T020604_T010203_FK_01")), 
			inverseJoinColumns = @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T020604_T010203_FK_02")),
			uniqueConstraints = @UniqueConstraint(name = "R_T020604_T010203_UK_01", columnNames = {"F_01", "F_02"}))
	private List<Mail> mails;

}
