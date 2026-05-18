/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalStep
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Approval
 *
 *	Description : One ordered step in an ApprovalChain.
 *	              Holds the assigned approver (User), the expected role, and
 *	              the resulting ApprovalDecision once the approver acts.
 *	              Child entity — no standalone repository.
 *
 **/

package dz.procsys.api.core.workflow.approval.model;

import dz.procsys.api.core.system.security.model.Role;
import dz.procsys.api.core.system.security.model.User;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
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
 * ApprovalStep — one ordered step inside an ApprovalChain.
 *
 * Database table : T_03_03_02
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : stepOrder       — execution order within the chain (1-based)
 *  - F_02 : approvalChain   — FK → T_03_03_01 (parent ApprovalChain)
 *  - F_03 : assignedUser    — FK → T_00_02_02 (User), LAZY, nullable (role-based assignment)
 *  - F_04 : requiredRole    — FK → T_00_02_03 (Role), LAZY, nullable (user-based assignment)
 *  - F_05 : stepStatus      — code: PENDING | IN_PROGRESS | APPROVED | REJECTED | SKIPPED
 *  - F_06 : decision        — FK → T_03_03_03 (ApprovalDecision), nullable until decided
 */
@Setter
@Getter
@ToString(exclude = {"approvalChain", "assignedUser", "requiredRole", "decision"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ApprovalStep")
@Table(name = "T_03_03_02")
@Schema(name = "ApprovalStep", description = "One ordered step inside an ApprovalChain")
public class ApprovalStep extends GenericModel {

    @Column(name = "F_01", nullable = false)
    @Schema(description = "Execution order within the chain (1-based)", example = "1")
    private Integer stepOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_02_FK_01"),
        nullable = false
    )
    @Schema(description = "Parent approval chain")
    private ApprovalChain approvalChain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_02_FK_02")
    )
    @Schema(description = "Explicitly assigned approver (User); null when assignment is role-based",
            nullable = true)
    private User assignedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_02_FK_03")
    )
    @Schema(description = "Required role for approver resolution; null when user is directly assigned",
            nullable = true)
    private Role requiredRole;

    @Column(name = "F_05", length = 30, nullable = false)
    @Schema(description = "Current status of this step",
            allowableValues = {"PENDING", "IN_PROGRESS", "APPROVED", "REJECTED", "SKIPPED"},
            example = "PENDING")
    private String stepStatus;

    @OneToOne(mappedBy = "approvalStep", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY, orphanRemoval = true)
    @Schema(description = "Decision recorded by the approver; null while step is still pending",
            nullable = true)
    private ApprovalDecision decision;
}
