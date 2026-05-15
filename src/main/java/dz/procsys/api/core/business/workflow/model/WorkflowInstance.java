/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstance
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
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

package dz.procsys.api.core.business.workflow.model;

import dz.procsys.api.platform.kernel.GenericModel;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WorkflowInstance - A running workflow attached to a business aggregate.
 *
 * Database table: T_03_02_01
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowDefinition  (FK → T_03_01_01.F_00)
 *  - F_02 : currentStep         (FK → T_03_01_02.F_00, nullable when finished)
 *  - F_03 : businessContext     – domain context code (CONSULTATION | CONTRACT | AMENDMENT | PLAN)
 *  - F_04 : businessKey         – String representation of the owning aggregate ID
 *  - F_05 : instanceStatus      – ACTIVE | COMPLETED | CANCELLED | SUSPENDED
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

    /**
     * Identifies the domain owning this instance.
     * Must match WorkflowDefinition.businessContext.
     * CONSULTATION | CONTRACT | AMENDMENT | PLAN
     */
    @Column(name = "F_03", length = 60, nullable = false)
    private String businessContext;

    /**
     * String representation of the owning aggregate primary key.
     * E.g. "42" for Consultation with id=42.
     * Kept as String for polymorphic binding across all contexts.
     */
    @Column(name = "F_04", length = 100, nullable = false)
    private String businessKey;

    /**
     * Lifecycle status: ACTIVE | COMPLETED | CANCELLED | SUSPENDED
     */
    @Column(name = "F_05", length = 20, nullable = false)
    private String instanceStatus = "ACTIVE";

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
