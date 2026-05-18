/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DelegationRule
 *	@CreatedOn	: 05-18-2026
 *	@Updated	: 05-18-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Workflow / Delegation
 *
 *	Description : Defines a time-bounded authority delegation from one User (delegator)
 *	              to another (delegate) for a given WorkflowProcessType scope.
 *	              When active, the delegate may act on behalf of the delegator
 *	              within the specified process type and optionally a specific role.
 *
 **/

package dz.procsys.api.core.workflow.delegation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dz.procsys.api.core.system.security.model.Role;
import dz.procsys.api.core.system.security.model.User;
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
 * DelegationRule — time-bounded authority delegation between two Users.
 *
 * Database table : T_03_04_01
 * Primary key    : F_00 (id) — inherited from GenericModel
 *
 * Fields:
 *  - F_01 : delegator         — FK → T_00_02_02 (User, LAZY)
 *  - F_02 : delegate          — FK → T_00_02_02 (User, LAZY)
 *  - F_03 : scopeRoleCode     — code of the Role scope (null = all roles of delegator)
 *  - F_04 : processTypeCode   — code of the WorkflowProcessType this delegation covers
 *  - F_05 : validFrom         — inclusive start date of the delegation period
 *  - F_06 : validTo           — inclusive end date of the delegation period
 *  - F_07 : active            — manual activation flag
 *  - F_08 : createdAt         — record creation timestamp
 *  - F_09 : justification     — reason for the delegation
 */
@Setter
@Getter
@ToString(exclude = {"delegator", "delegate"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DelegationRule")
@Table(
    name = "T_03_04_01",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "T_03_04_01_UK_01",
            columnNames = {"F_01", "F_02", "F_04", "F_05", "F_06"}
        )
    }
)
@Schema(name = "DelegationRule",
        description = "Time-bounded authority delegation from one User to another for a specific process type scope")
public class DelegationRule extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_01",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_04_01_FK_01"),
        nullable = false
    )
    @Schema(description = "User who delegates their authority; loaded LAZY")
    private User delegator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_04_01_FK_02"),
        nullable = false
    )
    @Schema(description = "User who receives the delegated authority; loaded LAZY")
    private User delegate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_03_04_01_FK_03")
    )
    @Schema(description = "Role that scopes this delegation; null means all roles of the delegator are delegated",
            nullable = true)
    private Role scopeRole;

    @Column(name = "F_04", length = 60, nullable = false)
    @Schema(description = "Code of the WorkflowProcessType this delegation covers",
            example = "CONSULTATION")
    private String processTypeCode;

    @Column(name = "F_05", nullable = false)
    @Schema(description = "Inclusive start date of the delegation period", example = "2026-06-01")
    private LocalDate validFrom;

    @Column(name = "F_06", nullable = false)
    @Schema(description = "Inclusive end date of the delegation period", example = "2026-06-15")
    private LocalDate validTo;

    @Column(name = "F_07", nullable = false)
    @Schema(description = "Manual activation flag; false suspends the delegation regardless of dates")
    private boolean active = true;

    @Column(name = "F_08", nullable = false)
    @Schema(description = "Timestamp when this delegation rule was created")
    private LocalDateTime createdAt;

    @Column(name = "F_09", length = 1000)
    @Schema(description = "Reason / justification for granting this delegation", nullable = true)
    private String justification;
}
