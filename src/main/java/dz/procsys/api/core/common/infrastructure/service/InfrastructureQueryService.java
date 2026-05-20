/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : InfrastructureQueryService
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Service
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.service;

import dz.procsys.api.core.common.infrastructure.dto.request.BuildingSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.RoomSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.ShelfSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.BuildingResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.RoomResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.ShelfResponseDTO;
import org.springframework.data.domain.Page;

/**
 * Read-only query service for infrastructure entities (building, room, shelf).
 */
public interface InfrastructureQueryService {

    Page<BuildingResponseDTO> searchBuildings(BuildingSearchRequestDTO request);

    Page<RoomResponseDTO> searchRooms(RoomSearchRequestDTO request);

    Page<ShelfResponseDTO> searchShelves(ShelfSearchRequestDTO request);
}