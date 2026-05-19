/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditRecord
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import java.util.Date;
import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Central immutable audit record representing a single traceable event in the platform.
 * This is the core persistence unit of the audit bounded context.
 * Contains full forensic data: actor snapshot, resource snapshot, action, before/after state,
 * correlation ID, and cryptographic hash chain value for integrity verification.
 * Append-only; once persisted, this record must never be modified.
 */
@Schema(
    name = "AuditRecord",
    description = "Immutable central audit record capturing a full traceable event with actor, resource, action, state diff, and hash chain"
)
@Setter
@Getter
@ToString(exclude = {"actor", "resource", "action", "context", "granularityType", "diffs"})
@EqualsAndHashCode(callSuper = true, exclude = {"actor", "resource", "action", "context", "granularityType", "diffs"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditRecord")
@Table(
    name = "T_00_04_10",
    indexes = {
        @Index(name = "IDX__T_00_04_10__CORRELATION", columnList = "F_01"),
        @Index(name = "IDX__T_00_04_10__TIMESTAMP", columnList = "F_02"),
        @Index(name = "IDX__T_00_04_10__ACTOR", columnList = "F_07"),
        @Index(name = "IDX__T_00_04_10__RESOURCE", columnList = "F_08"),
        @Index(name = "IDX__T_00_04_10__ACTION", columnList = "F_09"),
        @Index(name = "IDX__T_00_04_10__TRAIL", columnList = "F_11")
    }
)
public class AuditRecord extends GenericModel {

    /**
     * Correlation identifier linking this record to a logical transaction or request chain.
     * Primary forensic reconstruction key across all modules.
     */
    @Schema(
        description = "Correlation ID linking this record to a logical transaction or request chain",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Correlation ID is mandatory")
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String correlationId;

    /**
     * Timestamp of when the auditable event occurred.
     */
    @Schema(
        description = "Timestamp of when the auditable event occurred",
        example = "2026-05-19T16:00:00Z",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Event timestamp is mandatory")
    @PastOrPresent(message = "Event timestamp must be in the past or present")
    @Column(name = "F_02", nullable = false)
    private Date eventTimestamp;

    /**
     * JSON snapshot of the domain entity state before the action.
     */
    @Schema(
        description = "JSON snapshot of the domain entity state before the action",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03", columnDefinition = "TEXT")
    private String beforeState;

    /**
     * JSON snapshot of the domain entity state after the action.
     */
    @Schema(
        description = "JSON snapshot of the domain entity state after the action",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04", columnDefinition = "TEXT")
    private String afterState;

    /**
     * Cryptographic hash value for audit chain integrity verification.
     * Computed as SHA-256 of (previousHashValue + record content).
     */
    @Schema(
        description = "SHA-256 hash of the concatenation of the previous hash and this record's content (hash chain)",
        example = "a3f2c9d1e8b4...",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 512
    )
    @NotBlank(message = "Hash chain value is mandatory")
    @Size(max = 512, message = "Hash chain value must not exceed 512 characters")
    @Column(name = "F_05", length = 512, nullable = false)
    private String hashChainValue;

    /**
     * Hash of the previous AuditRecord in the chain (null for the genesis record).
     */
    @Schema(
        description = "Hash of the previous audit record in the chain (null for genesis record)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 512
    )
    @Size(max = 512, message = "Previous hash must not exceed 512 characters")
    @Column(name = "F_06", length = 512)
    private String previousHashValue;

    /**
     * Reference to the immutable actor snapshot.
     */
    @Schema(
        description = "Immutable actor snapshot who performed the action",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Actor is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__ACTOR"),
        nullable = false
    )
    private AuditActor actor;

    /**
     * Reference to the immutable resource snapshot.
     */
    @Schema(
        description = "Immutable resource snapshot that was targeted",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Resource is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__RESOURCE"),
        nullable = false
    )
    private AuditResource resource;

    /**
     * Reference to the action performed.
     */
    @Schema(
        description = "Action performed and recorded in this audit record",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Action is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__ACTION"),
        nullable = false
    )
    private AuditAction action;

    /**
     * Reference to the execution context.
     */
    @Schema(
        description = "Execution context in which this event occurred",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_10",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__CONTEXT")
    )
    private AuditContext context;

    /**
     * Reference to the audit trail grouping this record belongs to.
     */
    @Schema(
        description = "Audit trail this record belongs to",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_11",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__TRAIL")
    )
    private AuditTrail auditTrail;

    /**
     * Granularity level applied to this record.
     */
    @Schema(
        description = "Granularity level applied to this audit record",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Granularity type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_12",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_10__GRANULARITY"),
        nullable = false
    )
    private AuditGranularityType granularityType;

    /**
     * Collection of field-level or entity-level diffs associated with this record.
     */
    @Schema(
        description = "Field-level or entity-level diffs for this audit record",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "auditRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuditDiff> diffs;
}