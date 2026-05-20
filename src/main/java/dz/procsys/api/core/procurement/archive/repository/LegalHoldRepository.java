package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.LegalHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalHoldRepository extends JpaRepository<LegalHold, Long> {
    List<LegalHold> findByActive(boolean active);
    boolean existsByActiveTrueAndDocumentRecordId(Long documentRecordId);
    boolean existsByActiveTrueAndArchiveFolderId(Long archiveFolderId);
}
