package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.ArchiveBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox, Long> {
    Optional<ArchiveBox> findByShelfRoomFacilityCodeAndShelfRoomCodeAndShelfCodeAndCode(String facilityCode, String roomCode, String shelfCode, String code);
}
