/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepDefinition
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Defines a single step inside a WorkflowDefinition.
 *	              Steps are ordered and may carry an SLA (days), an actor role,
 *	              and a step type (ACTION | APPROVAL | REVIEW | NOTIFICATION).
 *
 **/

package dz.procsys.api.core.workflow.definition.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * WorkflowStepDefinition - One step in a WorkflowDefinition.
 *
 * Database table: T_03_01_02
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowDefinition (FK → T_03_01_01.F_00)
 *  - F_02 : stepOrder          – order of execution within the definition
 *  - F_03 : code               – unique code within a definition
 *  - F_04 : designationFr      – French label
 *  - F_05 : designationEn      – English label
 *  - F_06 : designationAr      – Arabic label
 *  - F_07 : stepType           – ACTION | APPROVAL | REVIEW | NOTIFICATION
 *  - F_08 : requiredRole       – role code that must execute this step
 *  - F_09 : slaDays            – SLA in business days (0 = no SLA)
 *  - F_10 : mandatory          – whether this step can be skipped
 *  - F_11 : description        – optional description
 */
@Setter
@Getter
@ToString(exclude = {"workflowDefinition", "outgoingTransitions", "incomingTransitions"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowStepDefinition")
@Table(
    name = "T_03_01_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_03_01_02_UK_01", columnNames = {"F_01", "F_03"})
    }
)
public class WorkflowStepDefinition extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_01", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_01_02_FK_01"), nullable = false)
    private WorkflowDefinition workflowDefinition;

    @Column(name = "F_02", nullable = false)
    private int stepOrder;

    @Column(name = "F_03", length = 60, nullable = false)
    private String code;

    @Column(name = "F_04", length = 300, nullable = false)
    private String designationFr;

    @Column(name = "F_05", length = 300)
    private String designationEn;

    @Column(name = "F_06", length = 300)
    private String designationAr;

    /**
     * Step type: ACTION | APPROVAL | REVIEW | NOTIFICATION
     */
    @Column(name = "F_07", length = 30, nullable = false)
    private String stepType;

    /**
     * Spring Security role code required to execute this step.
     * E.g.: ROLE_PROCUREMENT_MANAGER, ROLE_CONTROLLER
     */
    @Column(name = "F_08", length = 100)
    private String requiredRole;

    @Column(name = "F_09")
    private int slaDays;

    @Column(name = "F_10", nullable = false)
    private boolean mandatory = true;

    @Column(name = "F_11", length = 1000)
    private String description;

    @OneToMany(mappedBy = "fromStep", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkflowTransition> outgoingTransitions = new ArrayList<>();

    @OneToMany(mappedBy = "toStep", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkflowTransition> incomingTransitions = new ArrayList<>();
}
