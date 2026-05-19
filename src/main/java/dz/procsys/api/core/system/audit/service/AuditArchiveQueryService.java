/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveQueryService
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Service
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service;

import org.springframework.data.domain.Page;

import dz.procsys.api.core.system.audit.dto.request.AuditArchiveSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveSummaryResponseDTO;

/**
 * Read-only query service for audit archives.
 * Provides search and detail retrieval of archive batches.
 */
public interface AuditArchiveQueryService {

    /**
     * Searches audit archive batches using the provided filters and pagination.
     *
     * @param request the archive search request parameters
     * @return paged list of archive summaries
     */
    Page<AuditArchiveSummaryResponseDTO> searchArchives(AuditArchiveSearchRequestDTO request);

    /**
     * Retrieves detailed metadata for a single archive batch.
     *
     * @param archiveId the technical identifier of the archive
     * @return detailed archive metadata
     */
    AuditArchiveDetailResponseDTO getArchiveDetail(Long archiveId);
}