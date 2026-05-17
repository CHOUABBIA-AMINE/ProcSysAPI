/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowDefinition
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Defines a reusable workflow template.
 *	              A WorkflowDefinition is a named, versioned process blueprint
 *	              (e.g. "Consultation Process v1", "Contract Approval v2").
 *	              Business processes (Consultation, Contract, etc.) reference
 *	              a definition to create a live WorkflowInstance.
 *
 **/

package dz.procsys.api.core.workflow.definition.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * WorkflowDefinition - Blueprint for a procurement workflow process.
 *
 * Database table: T_03_01_01
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : code            – unique technical code  (e.g. CONS_PROCESS)
 *  - F_02 : designationFr   – French label (required)
 *  - F_03 : designationEn   – English label
 *  - F_04 : designationAr   – Arabic label
 *  - F_05 : version         – version string (e.g. "1.0")
 *  - F_06 : businessContext – the domain this definition belongs to
 *                             (CONSULTATION | CONTRACT | AMENDMENT | PLAN)
 *  - F_07 : active          – whether this definition is currently usable
 *  - F_08 : description     – optional long description
 *
 * INSERT INTO T_03_01_01 (F_00,F_01,F_02,F_03,F_04,F_05,F_06,F_07) VALUES
 *  (1,'CONS_PROCESS','Processus de consultation','Consultation Process',null,'1.0','CONSULTATION',1),
 *  (2,'CONTRACT_PROCESS','Processus de contrat','Contract Process',null,'1.0','CONTRACT',1),
 *  (3,'AMENDMENT_PROCESS','Processus d avenant','Amendment Process',null,'1.0','AMENDMENT',1);
 */
@Setter
@Getter
@ToString(exclude = {"stepDefinitions", "instances"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowDefinition")
@Table(
    name = "T_03_01_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_03_01_01_UK_01", columnNames = {"F_01", "F_05"})
    }
)
public class WorkflowDefinition extends GenericModel {

    @Column(name = "F_01", length = 60, nullable = false)
    private String code;

    @Column(name = "F_02", length = 300, nullable = false)
    private String designationFr;

    @Column(name = "F_03", length = 300)
    private String designationEn;

    @Column(name = "F_04", length = 300)
    private String designationAr;

    @Column(name = "F_05", length = 20, nullable = false)
    private String version;

    /**
     * Business context identifier — which bounded context owns this definition.
     * E.g.: CONSULTATION, CONTRACT, AMENDMENT, PLAN
     */
    @Column(name = "F_06", length = 60, nullable = false)
    private String businessContext;

    @Column(name = "F_07", nullable = false)
    private boolean active = true;

    @Column(name = "F_08", length = 1000)
    private String description;

    @OneToMany(mappedBy = "workflowDefinition", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkflowStepDefinition> stepDefinitions = new ArrayList<>();

    @OneToMany(mappedBy = "workflowDefinition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkflowInstance> instances = new ArrayList<>();
}
