/**
 *	
 *	@author		: CHOUABBIA Amine
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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Entity(name="Submission")
@Table(name="T_02_04_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_04_05_UK_01", columnNames = { "F_03", "F_04" })})
public class Submission extends GenericModel {
	
	@Column(name="F_01", nullable=true)
	private Date submissionDate;
	
	@Column(name="F_02", precision=18, scale=2, nullable=true)
	private BigDecimal financialOffer;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_02_04_05_FK_01"), nullable=false)
    private Consultation consultation;
	
	@ManyToOne
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_02_04_05_FK_02"), nullable=false)
    private Provider tender;
	
	@ManyToOne
    @JoinColumn(name="F_05", foreignKey=@ForeignKey(name="T_02_04_05_FK_03"), nullable=true)
    private File administrativePart;
	
	@ManyToOne
    @JoinColumn(name="F_06", foreignKey=@ForeignKey(name="T_02_04_05_FK_04"), nullable=true)
    private File technicalPart;
	
	@ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_02_04_05_FK_05"), nullable=true)
    private File financialPart;
	
	@ManyToOne
	@JoinColumn(name = "F_08", referencedColumnName = "F_00", foreignKey=@ForeignKey(name="T_02_04_05_FK_06"), nullable = true)
	private WorkflowInstance workflowInstance;

}
