/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : InfrastructureQueryServiceImpl
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : Service / Implementation
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.common.infrastructure.dto.request.BuildingSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.RoomSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.request.ShelfSearchRequestDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.BuildingResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.RoomResponseDTO;
import dz.procsys.api.core.common.infrastructure.dto.response.ShelfResponseDTO;
import dz.procsys.api.core.common.infrastructure.mapper.BuildingMapper;
import dz.procsys.api.core.common.infrastructure.mapper.RoomMapper;
import dz.procsys.api.core.common.infrastructure.mapper.ShelfMapper;
import dz.procsys.api.core.common.infrastructure.model.Building;
import dz.procsys.api.core.common.infrastructure.model.Room;
import dz.procsys.api.core.common.infrastructure.model.Shelf;
import dz.procsys.api.core.common.infrastructure.repository.BuildingRepository;
import dz.procsys.api.core.common.infrastructure.repository.RoomRepository;
import dz.procsys.api.core.common.infrastructure.repository.ShelfRepository;
import dz.procsys.api.core.common.infrastructure.service.InfrastructureQueryService;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of InfrastructureQueryService.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfrastructureQueryServiceImpl implements InfrastructureQueryService {

    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;
    private final ShelfRepository shelfRepository;
    private final BuildingMapper buildingMapper;
    private final RoomMapper roomMapper;
    private final ShelfMapper shelfMapper;

    @Override
    public Page<BuildingResponseDTO> searchBuildings(BuildingSearchRequestDTO request) {
        Pageable pageable = toPageable(request.getPage(), request.getSize());

        Page<Building> page = buildingRepository.findAll(pageable);
        // designationFrContains can be handled with specifications later
        return page.map(buildingMapper::toDTO);
    }

    @Override
    public Page<RoomResponseDTO> searchRooms(RoomSearchRequestDTO request) {
        Pageable pageable = toPageable(request.getPage(), request.getSize());
        Page<Room> page = roomRepository.findAll(pageable);
        // buildingId and codeContains can be refined via specifications
        return page.map(roomMapper::toDTO);
    }

    @Override
    public Page<ShelfResponseDTO> searchShelves(ShelfSearchRequestDTO request) {
        Pageable pageable = toPageable(request.getPage(), request.getSize());
        Page<Shelf> page = shelfRepository.findAll(pageable);
        // roomId and codeContains can be refined via specifications
        return page.map(shelfMapper::toDTO);
    }

    private Pageable toPageable(Integer page, Integer size) {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 20 : size;
        return PageRequest.of(p, s);
    }
}