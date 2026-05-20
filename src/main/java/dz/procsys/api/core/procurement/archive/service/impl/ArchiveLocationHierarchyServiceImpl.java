package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.model.*;
import dz.procsys.api.core.procurement.archive.repository.*;
import dz.procsys.api.core.procurement.archive.service.ArchiveLocationHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchiveLocationHierarchyServiceImpl implements ArchiveLocationHierarchyService {
    private final FacilityRepository facilityRepository;
    private final RoomRepository roomRepository;
    private final ShelfRepository shelfRepository;
    private final ShelfLevelRepository shelfLevelRepository;
    private final ArchiveBoxRepository archiveBoxRepository;

    public Facility createFacility(Facility facility) { return facilityRepository.save(facility); }

    public Room createRoom(Long facilityId, Room room) {
        Facility facility = facilityRepository.findById(facilityId)
            .orElseThrow(() -> new IllegalArgumentException("Facility not found with id: " + facilityId));
        room.setFacility(facility);
        return roomRepository.save(room);
    }

    public Shelf createShelf(Long roomId, Shelf shelf) {
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
        if (shelf.getRoom() != null && !room.getId().equals(shelf.getRoom().getId())) {
            throw new IllegalArgumentException("Shelf parent mismatch: provided room does not match roomId");
        }
        shelf.setRoom(room);
        return shelfRepository.save(shelf);
    }

    public ShelfLevel createShelfLevel(Long shelfId, ShelfLevel shelfLevel) {
        Shelf shelf = shelfRepository.findById(shelfId)
            .orElseThrow(() -> new IllegalArgumentException("Shelf not found with id: " + shelfId));
        if (shelfLevel.getShelf() != null && !shelf.getId().equals(shelfLevel.getShelf().getId())) {
            throw new IllegalArgumentException("ShelfLevel parent mismatch: provided shelf does not match shelfId");
        }
        shelfLevel.setShelf(shelf);
        return shelfLevelRepository.save(shelfLevel);
    }

    public ArchiveBox createArchiveBox(Long shelfId, ArchiveBox archiveBox) {
        Shelf shelf = shelfRepository.findById(shelfId)
            .orElseThrow(() -> new IllegalArgumentException("Shelf not found with id: " + shelfId));
        if (archiveBox.getShelf() != null && !shelf.getId().equals(archiveBox.getShelf().getId())) {
            throw new IllegalArgumentException("ArchiveBox parent mismatch: provided shelf does not match shelfId");
        }
        archiveBox.setShelf(shelf);
        return archiveBoxRepository.save(archiveBox);
    }

    public List<Room> findRoomsByFacility(Long facilityId) { return roomRepository.findByFacilityId(facilityId); }
    public List<Shelf> findShelvesByRoom(Long roomId) { return shelfRepository.findByRoomId(roomId); }
    public List<ShelfLevel> findLevelsByShelf(Long shelfId) { return shelfLevelRepository.findByShelfId(shelfId); }

    public ArchiveBox findBoxByPath(String facilityCode, String roomCode, String shelfCode, String boxCode) {
        return archiveBoxRepository.findByShelfRoomFacilityCodeAndShelfRoomCodeAndShelfCodeAndCode(facilityCode, roomCode, shelfCode, boxCode)
            .orElseThrow(() -> new IllegalArgumentException("ArchiveBox not found for path: " + facilityCode + "/" + roomCode + "/" + shelfCode + "/" + boxCode));
    }
}
