/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EscalationRule
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Escalation
 *
 *	Description : Defines the escalation policy applied to a workflow step when
 *	              its SLA is breached.
 *	              Specifies the threshold (in hours after breach), the escalation
 *	              target role, and the notification template code to trigger.
 *
 **/

package dz.procsys.api.core.workflow.escalation.model;

import dz.procsys.api.core.system.security.model.Role;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * EscalationRule — SLA breach escalation policy for one workflow step.
 *
 * Database table : T_03_05_02
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : stepDefinitionCode       — code of the WorkflowStepDefinition this rule targets
 *  - F_02 : processTypeCode          — code of the WorkflowProcessType scope
 *  - F_03 : thresholdHours           — hours after breach before escalation fires (0 = immediate)
 *  - F_04 : escalationTargetRole     — FK → T_00_02_03 (Role, LAZY): role to notify/assign to
 *  - F_05 : notificationTemplateCode — code identifying the notification message template
 *  - F_06 : active                   — whether this rule is currently enforced
 *  - F_07 : priority                 — rule priority when multiple rules apply (lower = higher)
 */
@Setter
@Getter
@ToString(exclude = {"escalationTargetRole"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EscalationRule")
@Table(
    name = "T_03_05_02",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "T_03_05_02_UK_01",
            columnNames = {"F_01", "F_02", "F_03"}
        )
    }
)
@Schema(name = "EscalationRule",
        description = "SLA breach escalation policy for a specific workflow step and process type")
public class EscalationRule extends GenericModel {

    @Column(name = "F_01", length = 60, nullable = false)
    @Schema(description = "Code of the WorkflowStepDefinition this rule targets",
            example = "STEP_EVALUATION")
    private String stepDefinitionCode;

    @Column(name = "F_02", length = 60, nullable = false)
    @Schema(description = "Code of the WorkflowProcessType that scopes this rule",
            example = "CONSULTATION")
    private String processTypeCode;

    @Column(name = "F_03", nullable = false)
    @Schema(description = "Hours after the SLA breach before this escalation fires; 0 means immediate",
            example = "2")
    private int thresholdHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_05_02_FK_01")
    )
    @Schema(description = "Role to notify or reassign to when this rule fires; loaded LAZY",
            nullable = true)
    private Role escalationTargetRole;

    @Column(name = "F_05", length = 100)
    @Schema(description = "Code identifying the notification template to send on escalation",
            example = "NOTIF_ESCALATION_DEFAULT", nullable = true)
    private String notificationTemplateCode;

    @Column(name = "F_06", nullable = false)
    @Schema(description = "Whether this rule is currently active and enforced")
    private boolean active = true;

    @Column(name = "F_07", nullable = false)
    @Schema(description = "Rule priority when multiple rules apply to the same step; lower number = higher priority",
            example = "1")
    private int priority = 1;
}
