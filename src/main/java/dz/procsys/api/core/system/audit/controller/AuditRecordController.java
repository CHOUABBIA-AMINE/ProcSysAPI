/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditRecordController
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : Controller
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.procsys.api.core.system.audit.dto.request.AuditActorTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditCorrelationRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditResourceTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordSummaryResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditTimelineItemResponseDTO;
import dz.procsys.api.core.system.audit.service.AuditQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller exposing read-only APIs for audit records:
 * generic search, actor timelines, resource timelines, correlation-based views and record detail.
 */
@RestController
@RequestMapping(path = "/audit/records", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Audit Records", description = "Read-only APIs for querying immutable audit records")
public class AuditRecordController {

    private final AuditQueryService auditQueryService;

    /**
     * Executes a generic audit search with filters and pagination.
     *
     * @param request search request containing filters and pagination parameters
     * @return page of audit record summaries
     */
    @Operation(
        summary = "Search audit records",
        description = "Executes a generic search over immutable audit records using filters and pagination"
    )
    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AuditRecordSummaryResponseDTO> searchAuditRecords(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Search filters and pagination parameters",
            required = true,
            content = @Content(
                schema = @Schema(implementation = AuditSearchRequestDTO.class)
            )
        )
        @Valid
        @RequestBody
        AuditSearchRequestDTO request
    ) {
        return auditQueryService.searchAuditRecords(request);
    }

    /**
     * Retrieves a chronological timeline of audit events for a specific actor.
     *
     * @param request actor timeline request parameters
     * @return page of timeline items ordered by event timestamp
     */
    @Operation(
        summary = "Get actor audit timeline",
        description = "Retrieves a chronological audit timeline for a specific actor by external actor reference ID"
    )
    @GetMapping(path = "/actor-timeline", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AuditTimelineItemResponseDTO> getActorTimeline(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Actor timeline request parameters",
            required = true,
            content = @Content(
                schema = @Schema(implementation = AuditActorTimelineRequestDTO.class)
            )
        )
        @Valid
        @RequestBody
        AuditActorTimelineRequestDTO request
    ) {
        return auditQueryService.getActorTimeline(request);
    }

    /**
     * Retrieves a chronological timeline of audit events for a specific resource.
     *
     * @param request resource timeline request parameters
     * @return page of timeline items ordered by event timestamp
     */
    @Operation(
        summary = "Get resource audit timeline",
        description = "Retrieves a chronological audit timeline for a specific resource instance and module"
    )
    @GetMapping(path = "/resource-timeline", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AuditTimelineItemResponseDTO> getResourceTimeline(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Resource timeline request parameters",
            required = true,
            content = @Content(
                schema = @Schema(implementation = AuditResourceTimelineRequestDTO.class)
            )
        )
        @Valid
        @RequestBody
        AuditResourceTimelineRequestDTO request
    ) {
        return auditQueryService.getResourceTimeline(request);
    }

    /**
     * Retrieves all audit records associated with a single correlation ID.
     *
     * @param request correlation request containing the correlation ID
     * @return list of audit record summaries for that correlation chain
     */
    @Operation(
        summary = "Get audit records by correlation ID",
        description = "Retrieves all audit records associated with a single correlation ID for forensic reconstruction"
    )
    @GetMapping(path = "/by-correlation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AuditRecordSummaryResponseDTO> getByCorrelationId(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Correlation ID request",
            required = true,
            content = @Content(
                schema = @Schema(implementation = AuditCorrelationRequestDTO.class)
            )
        )
        @Valid
        @RequestBody
        AuditCorrelationRequestDTO request
    ) {
        return auditQueryService.getByCorrelationId(request);
    }

    /**
     * Retrieves the full forensic detail of a single audit record.
     *
     * @param id technical identifier of the audit record
     * @return detailed forensic view including diffs and hash chain metadata
     */
    @Operation(
        summary = "Get audit record detail",
        description = "Retrieves the full forensic detail of a single immutable audit record"
    )
    @GetMapping(path = "/{id}")
    public AuditRecordDetailResponseDTO getRecordDetail(
        @Parameter(
            description = "Technical identifier of the audit record",
            required = true,
            example = "12345"
        )
        @PathVariable("id") Long id
    ) {
        return auditQueryService.getRecordDetail(id);
    }
}