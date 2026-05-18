/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEvent
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Immutable event record written on every workflow transition.
 *	              Provides a full audit trail per WorkflowInstance and is the
 *	              primary data source for process analytics.
 *
 **/

package dz.procsys.api.core.business.workflow.model;

import dz.procsys.api.platform.kernel.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
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
 *  - F_01 : workflowInstance    (FK → T_03_02_01.F_00)
 *  - F_02 : workflowProcessType (FK → T_03_00_01.F_00)
 *  - F_03 : businessKey         – aggregate ID as String
 *  - F_04 : workflowEventType   (FK → T_03_00_04.F_00)
 *  - F_05 : fromStepCode        – code of the step before the transition (nullable)
 *  - F_06 : toStepCode          – code of the step after the transition (nullable for terminal)
 *  - F_07 : actionCode          – action that triggered this event
 *  - F_08 : actorUsername       – user who triggered the event
 *  - F_09 : occurredAt          – timestamp
 *  - F_10 : decisionReason      – reason provided (nullable)
 *  - F_11 : durationSeconds     – seconds spent in the fromStep (-1 if not applicable)
 */
@Setter
@Getter
@ToString(exclude = "workflowInstance")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcessEvent")
@Table(name = "T_03_02_03")
@Schema(name = "ProcessEvent",
        description = "Immutable audit event generated on every workflow transition")
public class ProcessEvent extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_03_FK_01"),
        nullable = false
    )
    @Schema(description = "Owning workflow instance")
    private WorkflowInstance workflowInstance;

    // REFACTORED: was String businessContext with hardcoded CONSULTATION / CONTRACT / ...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_03_FK_02"),
        nullable = false
    )
    @Schema(description = "Process type that generated this event (managed in reference table T_03_00_01)")
    private WorkflowProcessType workflowProcessType;

    @Column(name = "F_03", length = 100, nullable = false)
    @Schema(description = "String representation of the owning aggregate primary key",
            example = "42")
    private String businessKey;

    // REFACTORED: was String eventType with hardcoded WORKFLOW_STARTED / STEP_COMPLETED / ...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_02_03_FK_03"),
        nullable = false
    )
    @Schema(description = "Type of workflow event (managed in reference table T_03_00_04)")
    private WorkflowEventType workflowEventType;

    @Column(name = "F_05", length = 60)
    @Schema(description = "Code of the step before the transition; null for start events")
    private String fromStepCode;

    @Column(name = "F_06", length = 60)
    @Schema(description = "Code of the step after the transition; null for terminal events")
    private String toStepCode;

    @Column(name = "F_07", length = 40)
    @Schema(description = "Action code that triggered this event")
    private String actionCode;

    @Column(name = "F_08", length = 150, nullable = false)
    @Schema(description = "Username of the actor who triggered the event")
    private String actorUsername;

    @Column(name = "F_09", nullable = false)
    @Schema(description = "Exact timestamp when the event occurred")
    private LocalDateTime occurredAt;

    @Column(name = "F_10", length = 1000)
    @Schema(description = "Optional decision reason provided by the actor")
    private String decisionReason;

    @Column(name = "F_11")
    @Schema(description = "Duration in seconds spent in the fromStep; -1 if not applicable")
    private long durationSeconds = -1L;
}
