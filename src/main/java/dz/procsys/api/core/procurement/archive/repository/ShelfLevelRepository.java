package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.ShelfLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShelfLevelRepository extends JpaRepository<ShelfLevel, Long> {
    List<ShelfLevel> findByShelfId(Long shelfId);
    Optional<ShelfLevel> findByShelfRoomFacilityCodeAndShelfRoomCodeAndShelfCodeAndCode(String facilityCode, String roomCode, String shelfCode, String code);
}
