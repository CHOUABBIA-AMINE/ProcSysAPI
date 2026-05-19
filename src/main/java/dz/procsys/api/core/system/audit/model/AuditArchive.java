/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditArchive
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import java.util.Date;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a sealed, immutable archive batch of audit records that have been migrated
 * from the active audit store into long-term compliance storage.
 * Tracks archival metadata: date range covered, record count, retention policy applied,
 * integrity hash of the archived batch, and storage reference.
 * Append-only; never modified after creation.
 */
@Schema(
    name = "AuditArchive",
    description = "Sealed archive batch of audit records migrated to long-term compliance storage"
)
@Setter
@Getter
@ToString(exclude = {"retentionPolicy"})
@EqualsAndHashCode(callSuper = true, exclude = {"retentionPolicy"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditArchive")
@Table(
    name = "T_00_03_14",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK__T_00_03_14__ARCHIVE_CODE", columnNames = {"F_01"})
    },
    indexes = {
        @Index(name = "IDX__T_00_03_14__CODE", columnList = "F_01"),
        @Index(name = "IDX__T_00_03_14__ARCHIVED_AT", columnList = "F_04"),
        @Index(name = "IDX__T_00_03_14__STATUS", columnList = "F_08"),
        @Index(name = "IDX__T_00_03_14__RETENTION", columnList = "F_09")
    }
)
public class AuditArchive extends GenericModel {

    /**
     * Unique code identifying this archive batch.
     */
    @Schema(
        description = "Unique code identifying this archive batch",
        example = "ARCHIVE-2026-05-001",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Archive code is mandatory")
    @Size(max = 200, message = "Archive code must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String archiveCode;

    /**
     * Timestamp of the earliest audit record included in this archive batch.
     */
    @Schema(
        description = "Timestamp of the earliest audit record in this archive",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Coverage start date is mandatory")
    @Column(name = "F_02", nullable = false)
    private Date coverageStartDate;

    /**
     * Timestamp of the latest audit record included in this archive batch.
     */
    @Schema(
        description = "Timestamp of the latest audit record in this archive",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Coverage end date is mandatory")
    @Column(name = "F_03", nullable = false)
    private Date coverageEndDate;

    /**
     * Timestamp when the archival process was executed.
     */
    @Schema(
        description = "Timestamp when the archival process was executed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Archived at timestamp is mandatory")
    @Column(name = "F_04", nullable = false)
    private Date archivedAt;

    /**
     * Total number of audit records included in this archive batch.
     */
    @Schema(
        description = "Total number of audit records in this archive batch",
        example = "150000",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Record count is mandatory")
    @Positive(message = "Record count must be positive")
    @Column(name = "F_05", nullable = false)
    private Long recordCount;

    /**
     * SHA-256 integrity hash of the archive batch content.
     */
    @Schema(
        description = "SHA-256 integrity hash of the archive batch",
        example = "b7e2a4c9...",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 512
    )
    @NotBlank(message = "Batch integrity hash is mandatory")
    @Size(max = 512, message = "Batch integrity hash must not exceed 512 characters")
    @Column(name = "F_06", length = 512, nullable = false)
    private String batchIntegrityHash;

    /**
     * External storage reference or path where the archive file is stored.
     */
    @Schema(
        description = "External storage reference or path for the archived data",
        example = "s3://procsys-audit-archive/2026/05/ARCHIVE-2026-05-001.gz",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 1000
    )
    @Size(max = 1000, message = "Storage reference must not exceed 1000 characters")
    @Column(name = "F_07", length = 1000)
    private String storageReference;

    /**
     * Status of the archive: COMPLETED, FAILED, PENDING_VERIFICATION.
     */
    @Schema(
        description = "Status of this archive batch",
        example = "COMPLETED",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Archive status is mandatory")
    @Size(max = 50, message = "Archive status must not exceed 50 characters")
    @Column(name = "F_08", length = 50, nullable = false)
    private String status;

    /**
     * The retention policy that triggered this archival.
     */
    @Schema(
        description = "Retention policy that triggered this archival",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_03_14__RETENTION_POLICY")
    )
    private AuditRetentionPolicy retentionPolicy;
}