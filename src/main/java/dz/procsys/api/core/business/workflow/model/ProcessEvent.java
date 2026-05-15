/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEvent
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Immutable event record written on every workflow transition.
 *	              Provides a full audit trail per WorkflowInstance and is the
 *	              primary data source for process analytics and AI features.
 *
 **/

package dz.procsys.api.core.business.workflow.model;

import dz.procsys.api.platform.kernel.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * ProcessEvent - Immutable event log entry for a WorkflowInstance transition.
 *
 * Database table: T_03_02_03
 * Primary key  : F_00 (id) - inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowInstance (FK → T_03_02_01.F_00)
 *  - F_02 : businessContext  – domain context (CONSULTATION | CONTRACT | ...)
 *  - F_03 : businessKey      – aggregate ID as String
 *  - F_04 : eventType        – WORKFLOW_STARTED | STEP_ENTERED | STEP_COMPLETED
 *                              | TRANSITION_FIRED | WORKFLOW_COMPLETED | WORKFLOW_CANCELLED
 *  - F_05 : fromStepCode     – code of the step before the transition (nullable)
 *  - F_06 : toStepCode       – code of the step after the transition (nullable for terminal)
 *  - F_07 : actionCode       – action that triggered this event
 *  - F_08 : actorUsername    – user who triggered the event
 *  - F_09 : occurredAt       – timestamp
 *  - F_10 : decisionReason   – reason provided (nullable)
 *  - F_11 : durationSeconds  – seconds spent in the fromStep (-1 if not applicable)
 */
@Setter
@Getter
@ToString(exclude = "workflowInstance")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcessEvent")
@Table(name = "T_03_02_03")
public class ProcessEvent extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_01", referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_03_FK_01"), nullable = false)
    private WorkflowInstance workflowInstance;

    @Column(name = "F_02", length = 60, nullable = false)
    private String businessContext;

    @Column(name = "F_03", length = 100, nullable = false)
    private String businessKey;

    /**
     * Event type:
     *  WORKFLOW_STARTED | STEP_ENTERED | STEP_COMPLETED
     *  | TRANSITION_FIRED | WORKFLOW_COMPLETED | WORKFLOW_CANCELLED
     */
    @Column(name = "F_04", length = 40, nullable = false)
    private String eventType;

    @Column(name = "F_05", length = 60)
    private String fromStepCode;

    @Column(name = "F_06", length = 60)
    private String toStepCode;

    @Column(name = "F_07", length = 40)
    private String actionCode;

    @Column(name = "F_08", length = 150, nullable = false)
    private String actorUsername;

    @Column(name = "F_09", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "F_10", length = 1000)
    private String decisionReason;

    @Column(name = "F_11")
    private long durationSeconds = -1L;
}
