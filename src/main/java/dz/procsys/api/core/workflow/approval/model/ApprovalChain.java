/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalChain
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Approval
 *
 *	Description : Root aggregate of an approval process bound to one WorkflowInstance.
 *	              Owns the ordered list of ApprovalStep records and tracks the
 *	              overall outcome (pending / approved / rejected).
 *
 **/

package dz.procsys.api.core.workflow.approval.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dz.procsys.api.core.workflow.shared.model.WorkflowBusinessRef;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ApprovalChain — root aggregate for a multi-step approval process.
 *
 * Database table : T_03_03_01
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : workflowInstanceId  — FK id of the WorkflowInstance owning this chain
 *  - F_02 : businessRef         — FK → T_03_00_05 (WorkflowBusinessRef)
 *  - F_03 : chainStatus         — code: PENDING | IN_PROGRESS | APPROVED | REJECTED
 *  - F_04 : createdAt           — timestamp when chain was created
 *  - F_05 : resolvedAt          — timestamp of final decision (nullable)
 *  - F_06 : observation         — optional free-text observation
 */
@Setter
@Getter
@ToString(exclude = {"steps"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ApprovalChain")
@Table(name = "T_03_03_01")
@Schema(name = "ApprovalChain", description = "Root aggregate of a multi-step approval process bound to one WorkflowInstance")
public class ApprovalChain extends GenericModel {

    @Column(name = "F_01", nullable = false)
    @Schema(description = "Primary key of the owning WorkflowInstance", example = "101")
    private Long workflowInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_03_01_FK_01")
    )
    @Schema(description = "Business aggregate reference bound to this approval chain")
    private WorkflowBusinessRef businessRef;

    @Column(name = "F_03", length = 30, nullable = false)
    @Schema(description = "Current status code of the chain",
            allowableValues = {"PENDING", "IN_PROGRESS", "APPROVED", "REJECTED"},
            example = "IN_PROGRESS")
    private String chainStatus;

    @Column(name = "F_04", nullable = false)
    @Schema(description = "Timestamp when the approval chain was created")
    private LocalDateTime createdAt;

    @Column(name = "F_05")
    @Schema(description = "Timestamp of the final decision; null while chain is still active", nullable = true)
    private LocalDateTime resolvedAt;

    @Column(name = "F_06", length = 1000)
    @Schema(description = "Optional free-text observation", nullable = true)
    private String observation;

    @OneToMany(mappedBy = "approvalChain", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("F_01 ASC")
    @Schema(description = "Ordered list of approval steps composing this chain")
    private List<ApprovalStep> steps = new ArrayList<>();
}
