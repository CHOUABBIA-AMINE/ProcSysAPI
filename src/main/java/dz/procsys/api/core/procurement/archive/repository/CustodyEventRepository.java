package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.CustodyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustodyEventRepository extends JpaRepository<CustodyEvent, Long> {

    List<CustodyEvent> findByArchiveFolderIdOrderByEventTimestampAscIdAsc(Long archiveFolderId);

    List<CustodyEvent> findByDocumentRecordIdOrderByEventTimestampAscIdAsc(Long documentRecordId);

    Optional<CustodyEvent> findTopByArchiveFolderIdOrderByEventTimestampDescIdDesc(Long archiveFolderId);

    Optional<CustodyEvent> findTopByDocumentRecordIdOrderByEventTimestampDescIdDesc(Long documentRecordId);
}
