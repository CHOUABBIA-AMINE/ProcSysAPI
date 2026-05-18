/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowStepStatus
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Workflow
 *
 *	Description : Reference entity describing the execution statuses of a
 *	              WorkflowStepInstance (e.g. PENDING, IN_PROGRESS, COMPLETED,
 *	              SKIPPED, REJECTED). Values are managed in the database;
 *	              no literals are hardcoded in domain classes.
 *
 **/

package dz.procsys.api.core.business.workflow.model;

import dz.procsys.api.platform.kernel.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowStepStatus — Reference table for step execution statuses.
 *
 * Database table : T_03_00_03
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Seed values (managed in database, not as enum):
 *   PENDING       — step is waiting to be processed
 *   IN_PROGRESS   — step is currently being processed by an actor
 *   COMPLETED     — step was successfully completed
 *   SKIPPED       — step was bypassed by a conditional transition
 *   REJECTED      — step action was rejected; workflow may return or terminate
 *
 * Fields:
 *  - F_01 : code           — unique technical code (max 60 chars)
 *  - F_02 : designationFr  — French label (required)
 *  - F_03 : designationEn  — English label
 *  - F_04 : designationAr  — Arabic label
 *  - F_05 : description    — optional description
 *  - F_06 : active         — whether this status is usable
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkflowStepStatus")
@Table(
    name = "T_03_00_03",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "T_03_00_03_UK_01",
            columnNames = {"F_01"}
        )
    }
)
@Schema(name = "WorkflowStepStatus",
        description = "Reference entity for workflow step execution statuses")
public class WorkflowStepStatus extends GenericModel {

    @Column(name = "F_01", length = 60, nullable = false)
    @Schema(description = "Unique technical code of the step status",
            example = "PENDING")
    private String code;

    @Column(name = "F_02", length = 300, nullable = false)
    @Schema(description = "French label of the step status")
    private String designationFr;

    @Column(name = "F_03", length = 300)
    @Schema(description = "English label of the step status")
    private String designationEn;

    @Column(name = "F_04", length = 300)
    @Schema(description = "Arabic label of the step status")
    private String designationAr;

    @Column(name = "F_05", length = 1000)
    @Schema(description = "Optional description of the step status")
    private String description;

    @Column(name = "F_06", nullable = false)
    @Schema(description = "Indicates whether this status is active and usable")
    private boolean active = true;
}
