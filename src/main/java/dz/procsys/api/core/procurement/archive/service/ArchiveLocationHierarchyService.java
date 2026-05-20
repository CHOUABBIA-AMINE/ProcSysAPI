package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.model.*;

import java.util.List;

public interface ArchiveLocationHierarchyService {
    Facility createFacility(Facility facility);
    Room createRoom(Long facilityId, Room room);
    Shelf createShelf(Long roomId, Shelf shelf);
    ShelfLevel createShelfLevel(Long shelfId, ShelfLevel shelfLevel);
    ArchiveBox createArchiveBox(Long shelfId, ArchiveBox archiveBox);
    List<Room> findRoomsByFacility(Long facilityId);
    List<Shelf> findShelvesByRoom(Long roomId);
    List<ShelfLevel> findLevelsByShelf(Long shelfId);
    ArchiveBox findBoxByPath(String facilityCode, String roomCode, String shelfCode, String boxCode);
}
