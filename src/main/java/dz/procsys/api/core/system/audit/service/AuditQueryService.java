/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditQueryService
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Service
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service;

import org.springframework.data.domain.Page;

import dz.procsys.api.core.system.audit.dto.request.AuditActorTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditCorrelationRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditResourceTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordSummaryResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditTimelineItemResponseDTO;

/**
 * Read-only query service for audit records.
 * Provides search, actor timeline, resource timeline and correlation-based views.
 */
public interface AuditQueryService {

    /**
     * Executes a generic audit search using the provided filters and pagination.
     *
     * @param request the search request parameters
     * @return paged list of audit record summaries
     */
    Page<AuditRecordSummaryResponseDTO> searchAuditRecords(AuditSearchRequestDTO request);

    /**
     * Retrieves a chronological timeline of audit events for a specific actor.
     *
     * @param request the actor timeline request parameters
     * @return paged list of timeline items
     */
    Page<AuditTimelineItemResponseDTO> getActorTimeline(AuditActorTimelineRequestDTO request);

    /**
     * Retrieves a chronological timeline of audit events for a specific resource.
     *
     * @param request the resource timeline request parameters
     * @return paged list of timeline items
     */
    Page<AuditTimelineItemResponseDTO> getResourceTimeline(AuditResourceTimelineRequestDTO request);

    /**
     * Retrieves all audit records associated with a single correlation ID.
     *
     * @param request the correlation request parameters
     * @return paged list of audit record summaries
     */
    Page<AuditRecordSummaryResponseDTO> getByCorrelationId(AuditCorrelationRequestDTO request);

    /**
     * Retrieves the full detail of a single audit record.
     *
     * @param recordId the technical identifier of the audit record
     * @return detailed forensic view of the audit record
     */
    AuditRecordDetailResponseDTO getRecordDetail(Long recordId);
}