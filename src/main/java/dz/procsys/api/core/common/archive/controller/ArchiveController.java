/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveController
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : Controller
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.controller;

import dz.procsys.api.core.common.archive.dto.request.ArchiveBoxSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.request.ArchiveFolderSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveBoxResponseDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveFolderResponseDTO;
import dz.procsys.api.core.common.archive.service.ArchiveQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing read-only archive container APIs.
 */
@RestController
@RequestMapping(
    path = "/api/v1/common/archive",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Validated
@Tag(name = "Common Archive", description = "Read-only APIs for archive boxes and folders")
public class ArchiveController {

    private final ArchiveQueryService archiveQueryService;

    @Operation(
        summary = "Search archive boxes",
        description = "Searches archive boxes by code and shelf using pagination"
    )
    @GetMapping(path = "/boxes/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<ArchiveBoxResponseDTO> searchArchiveBoxes(
    	@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Archive box search filters and pagination parameters",
            required = true,
            content = @Content(schema = @Schema(implementation = ArchiveBoxSearchRequestDTO.class))
        )
        @Valid @RequestBody ArchiveBoxSearchRequestDTO request
    ) {
        return archiveQueryService.searchArchiveBoxes(request);
    }

    @Operation(
        summary = "Search archive folders",
        description = "Searches archive folders by code and archive box using pagination"
    )
    @GetMapping(path = "/folders/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<ArchiveFolderResponseDTO> searchArchiveFolders(
    	@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Archive folder search filters and pagination parameters",
            required = true,
            content = @Content(schema = @Schema(implementation = ArchiveFolderSearchRequestDTO.class))
        )
        @Valid @RequestBody ArchiveFolderSearchRequestDTO request
    ) {
        return archiveQueryService.searchArchiveFolders(request);
    }
}