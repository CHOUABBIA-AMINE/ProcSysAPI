/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstance
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : A live instance of a WorkflowDefinition bound to a concrete
 *	              business entity (e.g. a specific Consultation, Contract, etc.).
 *	              Tracks the current step, overall status, actor, and lifecycle dates.
 *
 **/

package dz.procsys.api.core.workflow.execution.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dz.procsys.api.core.workflow.definition.model.WorkflowDefinition;
import dz.procsys.api.core.workflow.definition.model.WorkflowStepDefinition;
import dz.procsys.api.core.workflow.shared.model.WorkflowBusinessRef;
import dz.procsys.api.core.workflow.shared.model.WorkflowInstanceStatus;
import dz.procsys.api.core.workflow.shared.model.WorkflowProcessType;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowInstance - A running workflow attached to a business aggregate.
 *
 * Database table: T_03_02_01
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowDefinition  (FK → T_03_01_01.F_00)
 *  - F_02 : currentStep         (FK → T_03_01_02.F_00, nullable when finished)
 *  - F_03 : workflowProcessType (FK → T_03_00_01.F_00)
 *  - F_04 : businessRef         (FK → T_03_00_05.F_00)
 *  - F_05 : instanceStatus      (FK → T_03_00_02.F_00)
 *  - F_06 : startedAt           – when the instance was created
 *  - F_07 : completedAt         – when the instance reached a terminal step
 *  - F_08 : startedBy           – username of the actor who started the workflow
 *  - F_09 : slaDueDate          – computed SLA deadline for the current step
 *  - F_10 : observation         – optional free-text observation
 */
@Setter
@Getter
@ToString(exclude = {"workflowDefinition", "currentStep", "stepInstances", "processEvents"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowInstance")
@Table(name = "T_03_02_01")
public class WorkflowInstance extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_01", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_01_FK_01"), nullable = false)
    private WorkflowDefinition workflowDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_02", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_01_FK_02"))
    private WorkflowStepDefinition currentStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_03", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_01_FK_03"), nullable = false)
    private WorkflowProcessType workflowProcessType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_04", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_01_FK_04"), nullable = false)
    private WorkflowBusinessRef businessRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_05", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_01_FK_05"), nullable = false)
    private WorkflowInstanceStatus instanceStatus;

    @Column(name = "F_06", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "F_07")
    private LocalDateTime completedAt;

    @Column(name = "F_08", length = 150, nullable = false)
    private String startedBy;

    @Column(name = "F_09")
    private LocalDateTime slaDueDate;

    @Column(name = "F_10", length = 1000)
    private String observation;

    @OneToMany(mappedBy = "workflowInstance", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkflowStepInstance> stepInstances = new ArrayList<>();

    @OneToMany(mappedBy = "workflowInstance", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProcessEvent> processEvents = new ArrayList<>();
}
