/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalDecision
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Approval
 *
 *	Description : Immutable record of the decision made on one ApprovalStep.
 *	              Created once when an approver acts; never updated.
 *	              Decider is linked via User (LAZY, no collections loaded).
 *
 **/

package dz.procsys.api.core.workflow.approval.model;

import java.time.LocalDateTime;

import dz.procsys.api.core.system.security.model.User;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ApprovalDecision — immutable record of one approver’s decision.
 *
 * Database table : T_03_03_03
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : approvalStep    — FK → T_03_03_02 (parent ApprovalStep, 1:1)
 *  - F_02 : decidedBy       — FK → T_00_02_02 (User, LAZY)
 *  - F_03 : decisionCode    — APPROVED | REJECTED | RETURNED
 *  - F_04 : decidedAt       — exact timestamp of the decision
 *  - F_05 : justification   — mandatory for REJECTED / RETURNED
 *  - F_06 : observation     — optional additional comment
 */
@Setter
@Getter
@ToString(exclude = {"approvalStep", "decidedBy"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ApprovalDecision")
@Table(name = "T_03_03_03")
@Schema(name = "ApprovalDecision",
        description = "Immutable decision record created when an approver acts on an ApprovalStep")
public class ApprovalDecision extends GenericModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_03_FK_01"),
        nullable = false
    )
    @Schema(description = "The ApprovalStep this decision closes")
    private ApprovalStep approvalStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_03_FK_02"),
        nullable = false
    )
    @Schema(description = "User who made this decision; loaded LAZY, no collections traversed")
    private User decidedBy;

    @Column(name = "F_03", length = 20, nullable = false)
    @Schema(description = "Decision outcome code",
            allowableValues = {"APPROVED", "REJECTED", "RETURNED"},
            example = "APPROVED")
    private String decisionCode;

    @Column(name = "F_04", nullable = false)
    @Schema(description = "Exact timestamp when the decision was recorded")
    private LocalDateTime decidedAt;

    @Column(name = "F_05", length = 1000)
    @Schema(description = "Justification text; mandatory for REJECTED or RETURNED decisions",
            nullable = true)
    private String justification;

    @Column(name = "F_06", length = 1000)
    @Schema(description = "Optional additional comment", nullable = true)
    private String observation;
}
