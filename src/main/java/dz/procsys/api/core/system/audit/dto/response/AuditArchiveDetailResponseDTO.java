/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveDetailResponseDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.response;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO representing detailed metadata for a single audit archive batch.
 * Does not expose the raw archived records, only metadata and storage reference.
 */
@Schema(
    name = "AuditArchiveDetailResponseDto",
    description = "Detailed metadata view of a single audit archive batch"
)
@Setter
@Getter
@ToString
public class AuditArchiveDetailResponseDTO {

    @Schema(
        description = "Technical identifier of the archive batch",
        example = "10"
    )
    private Long id;

    @Schema(
        description = "Unique code identifying this archive batch",
        example = "ARCHIVE-2026-05-001"
    )
    private String archiveCode;

    @Schema(
        description = "Timestamp of the earliest audit record included in this archive",
        example = "2026-05-01T00:00:00Z"
    )
    private Date coverageStartDate;

    @Schema(
        description = "Timestamp of the latest audit record included in this archive",
        example = "2026-05-31T23:59:59Z"
    )
    private Date coverageEndDate;

    @Schema(
        description = "Timestamp when the archival process was executed",
        example = "2026-06-01T02:15:00Z"
    )
    private Date archivedAt;

    @Schema(
        description = "Total number of audit records in this archive batch",
        example = "150000"
    )
    private Long recordCount;

    @Schema(
        description = "Status of the archive (e.g. COMPLETED, FAILED, PENDING_VERIFICATION)",
        example = "COMPLETED"
    )
    private String status;

    @Schema(
        description = "SHA-256 integrity hash of the archive batch",
        example = "b7e2a4c9..."
    )
    private String batchIntegrityHash;

    @Schema(
        description = "External storage reference or path for the archived data",
        example = "s3://procsys-audit-archive/2026/05/ARCHIVE-2026-05-001.gz"
    )
    private String storageReference;

    @Schema(
        description = "Code of the retention policy that triggered this archival",
        example = "POLICY-SETTING-CHANGE-10Y"
    )
    private String retentionPolicyCode;
}