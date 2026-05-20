package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findByRoomId(Long roomId);
    Optional<Shelf> findByRoomFacilityCodeAndRoomCodeAndCode(String facilityCode, String roomCode, String code);
}
