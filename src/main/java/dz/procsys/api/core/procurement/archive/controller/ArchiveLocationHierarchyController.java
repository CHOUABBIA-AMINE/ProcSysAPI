package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.model.*;
import dz.procsys.api.core.procurement.archive.service.ArchiveLocationHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procurement/archive/locations")
@RequiredArgsConstructor
public class ArchiveLocationHierarchyController {

    private final ArchiveLocationHierarchyService service;

    @PostMapping("/facilities")
    @ResponseStatus(HttpStatus.CREATED)
    public Facility createFacility(@RequestBody Facility facility) { return service.createFacility(facility); }

    @PostMapping("/facilities/{facilityId}/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@PathVariable Long facilityId, @RequestBody Room room) { return service.createRoom(facilityId, room); }

    @PostMapping("/rooms/{roomId}/shelves")
    @ResponseStatus(HttpStatus.CREATED)
    public Shelf createShelf(@PathVariable Long roomId, @RequestBody Shelf shelf) { return service.createShelf(roomId, shelf); }

    @PostMapping("/shelves/{shelfId}/levels")
    @ResponseStatus(HttpStatus.CREATED)
    public ShelfLevel createLevel(@PathVariable Long shelfId, @RequestBody ShelfLevel level) { return service.createShelfLevel(shelfId, level); }

    @PostMapping("/shelves/{shelfId}/boxes")
    @ResponseStatus(HttpStatus.CREATED)
    public ArchiveBox createBox(@PathVariable Long shelfId, @RequestBody ArchiveBox box) { return service.createArchiveBox(shelfId, box); }

    @GetMapping("/facilities/{facilityId}/rooms")
    public List<Room> getRooms(@PathVariable Long facilityId) { return service.findRoomsByFacility(facilityId); }

    @GetMapping("/rooms/{roomId}/shelves")
    public List<Shelf> getShelves(@PathVariable Long roomId) { return service.findShelvesByRoom(roomId); }

    @GetMapping("/shelves/{shelfId}/levels")
    public List<ShelfLevel> getLevels(@PathVariable Long shelfId) { return service.findLevelsByShelf(shelfId); }

    @GetMapping("/lookup/box")
    public ArchiveBox getBoxByPath(@RequestParam String facilityCode, @RequestParam String roomCode,
                                   @RequestParam String shelfCode, @RequestParam String boxCode) {
        return service.findBoxByPath(facilityCode, roomCode, shelfCode, boxCode);
    }
}
