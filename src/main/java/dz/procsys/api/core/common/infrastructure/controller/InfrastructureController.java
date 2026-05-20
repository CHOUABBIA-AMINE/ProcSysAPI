/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : InfrastructureController
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : Controller
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.controller;

import dz.procsys.api.core.common.infrastructure.dto.request.BuildingSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.RoomSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.ShelfSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.BuildingResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.RoomResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.ShelfResponseDTO;
import dz.procsys.api.core.common.infrastructure.service.InfrastructureQueryService;
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
 * REST controller exposing read-only infrastructure APIs.
 */
@RestController
@RequestMapping(
    path = "/api/v1/common/infrastructure",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Validated
@Tag(name = "Common Infrastructure", description = "Read-only APIs for buildings, rooms and shelves")
public class InfrastructureController {

    private final InfrastructureQueryService infrastructureQueryService;

    @Operation(
        summary = "Search buildings",
        description = "Searches buildings using multilingual designations and pagination"
    )
    @GetMapping(path = "/buildings/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<BuildingResponseDTO> searchBuildings(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Building search filters and pagination parameters",
            required = true,
            content = @Content(schema = @Schema(implementation = BuildingSearchRequestDTO.class))
        )
        @Valid @RequestBody BuildingSearchRequestDTO request
    ) {
        return infrastructureQueryService.searchBuildings(request);
    }

    @Operation(
        summary = "Search rooms",
        description = "Searches rooms by building and code filters"
    )
    @GetMapping(path = "/rooms/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<RoomResponseDTO> searchRooms(
    	@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Room search filters and pagination parameters",
            required = true,
            content = @Content(schema = @Schema(implementation = RoomSearchRequestDTO.class))
        )
        @Valid @RequestBody RoomSearchRequestDTO request
    ) {
        return infrastructureQueryService.searchRooms(request);
    }

    @Operation(
        summary = "Search shelves",
        description = "Searches shelves by room and code filters"
    )
    @GetMapping(path = "/shelves/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<ShelfResponseDTO> searchShelves(
    	@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Shelf search filters and pagination parameters",
            required = true,
            content = @Content(schema = @Schema(implementation = ShelfSearchRequestDTO.class))
        )
        @Valid @RequestBody ShelfSearchRequestDTO request
    ) {
        return infrastructureQueryService.searchShelves(request);
    }
}