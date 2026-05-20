package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.CustodyEvent;
import dz.procsys.api.core.procurement.archive.repository.projection.CustodyEventSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CustodyEventRepository extends JpaRepository<CustodyEvent, Long> {

    List<CustodyEvent> findByArchiveFolderIdOrderByEventTimestampAscIdAsc(Long archiveFolderId);

    List<CustodyEvent> findByDocumentRecordIdOrderByEventTimestampAscIdAsc(Long documentRecordId);

    Optional<CustodyEvent> findTopByArchiveFolderIdOrderByEventTimestampDescIdDesc(Long archiveFolderId);

    Optional<CustodyEvent> findTopByDocumentRecordIdOrderByEventTimestampDescIdDesc(Long documentRecordId);

    @Query("""
        select ce.id as id,
               ce.eventType as eventType,
               ce.eventTimestamp as eventTimestamp,
               ce.actorIdentifier as actorIdentifier,
               ab.id as archiveBoxId,
               af.id as archiveFolderId,
               dr.id as documentRecordId,
               cs.id as custodySessionId
          from CustodyEvent ce
          left join ce.archiveBox ab
          left join ce.archiveFolder af
          left join ce.documentRecord dr
          left join ce.custodySession cs
         where (:actorIdentifier is null or ce.actorIdentifier = :actorIdentifier)
           and (:eventType is null or ce.eventType = :eventType)
           and (:fromTs is null or ce.eventTimestamp >= :fromTs)
           and (:toTs is null or ce.eventTimestamp <= :toTs)
    """)
    Page<CustodyEventSearchView> searchCustody(
        @Param("actorIdentifier") String actorIdentifier,
        @Param("eventType") dz.procsys.api.core.procurement.archive.model.CustodyEventType eventType,
        @Param("fromTs") Instant fromTs,
        @Param("toTs") Instant toTs,
        Pageable pageable
    );
}
