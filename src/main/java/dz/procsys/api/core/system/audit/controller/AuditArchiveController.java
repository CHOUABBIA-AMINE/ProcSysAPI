/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveController
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

import dz.procsys.api.core.system.audit.dto.request.AuditArchiveSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveSummaryResponseDTO;
import dz.procsys.api.core.system.audit.service.AuditArchiveQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller exposing read-only APIs for audit archive batches:
 * search and detailed metadata retrieval.
 */
@RestController
@RequestMapping(path = "/audit/archives", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Audit Archives", description = "Read-only APIs for querying audit archive batches")
public class AuditArchiveController {

    private final AuditArchiveQueryService auditArchiveQueryService;

    /**
     * Searches audit archive batches using the provided filters and pagination.
     *
     * @param request archive search filters and pagination parameters
     * @return page of archive summaries
     */
    @Operation(
        summary = "Search audit archives",
        description = "Searches immutable audit archive batches using filters and pagination"
    )
    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AuditArchiveSummaryResponseDTO> searchArchives(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Archive search filters and pagination parameters",
            required = true,
            content = @Content(
                schema = @Schema(implementation = AuditArchiveSearchRequestDTO.class)
            )
        )
        @Valid
        @RequestBody
        AuditArchiveSearchRequestDTO request
    ) {
        return auditArchiveQueryService.searchArchives(request);
    }

    /**
     * Retrieves detailed metadata of a single audit archive batch.
     *
     * @param id technical identifier of the archive batch
     * @return detailed metadata including integrity hash and storage reference
     */
    @Operation(
        summary = "Get audit archive detail",
        description = "Retrieves detailed metadata of a single audit archive batch"
    )
    @GetMapping(path = "/{id}")
    public AuditArchiveDetailResponseDTO getArchiveDetail(
        @Parameter(
            description = "Technical identifier of the archive batch",
            required = true,
            example = "10"
        )
        @PathVariable("id") Long id
    ) {
        return auditArchiveQueryService.getArchiveDetail(id);
    }
}