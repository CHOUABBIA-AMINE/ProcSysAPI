/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowTransition
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Defines an allowed transition between two WorkflowStepDefinitions.
 *	              A transition carries a label (APPROVE, REJECT, SUBMIT, etc.)
 *	              and an optional guard condition code.
 *
 **/

package dz.procsys.api.core.workflow.definition.model;

import dz.procsys.api.platform.kernel.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

/**
 * WorkflowTransition - Allowed edge between two steps.
 *
 * Database table: T_03_01_03
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : fromStep     (FK → T_03_01_02.F_00)
 *  - F_02 : toStep       (FK → T_03_01_02.F_00, nullable for terminal transitions)
 *  - F_03 : actionCode   – APPROVE | REJECT | SUBMIT | RETURN | ESCALATE | CLOSE
 *  - F_04 : labelFr      – French button/action label
 *  - F_05 : labelEn      – English label
 *  - F_06 : guardCode    – optional business-rule code checked before allowing the transition
 *  - F_07 : terminal     – true if this transition ends the workflow instance
 */
@Setter
@Getter
@ToString(exclude = {"fromStep", "toStep"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowTransition")
@Table(
    name = "T_03_01_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_03_01_03_UK_01", columnNames = {"F_01", "F_03"})
    }
)
public class WorkflowTransition extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_01", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_01_03_FK_01"), nullable = false)
    private WorkflowStepDefinition fromStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_02", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_01_03_FK_02"))
    private WorkflowStepDefinition toStep;

    /**
     * Action code: APPROVE | REJECT | SUBMIT | RETURN | ESCALATE | CLOSE
     */
    @Column(name = "F_03", length = 40, nullable = false)
    private String actionCode;

    @Column(name = "F_04", length = 200, nullable = false)
    private String labelFr;

    @Column(name = "F_05", length = 200)
    private String labelEn;

    /**
     * Optional guard condition code — evaluated by WorkflowGuardEvaluator
     * before allowing the transition. E.g.: AMOUNT_THRESHOLD_CHECK
     */
    @Column(name = "F_06", length = 100)
    private String guardCode;

    @Column(name = "F_07", nullable = false)
    private boolean terminal = false;
}
