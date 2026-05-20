package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.DocumentRecord;
import dz.procsys.api.core.procurement.archive.model.DocumentClassification;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRecordRepository extends JpaRepository<DocumentRecord, Long> {

    @Query("""
        select d from DocumentRecord d
        where (:classification is null or d.classification = :classification)
          and (:referenceNumber is null or lower(d.referenceNumber) like lower(concat('%', :referenceNumber, '%')))
          and (:supplierOrContractReference is null or lower(coalesce(d.supplierReference, d.contractReference, '')) like lower(concat('%', :supplierOrContractReference, '%')))
          and (:fiscalPeriod is null or lower(d.fiscalPeriod) like lower(concat('%', :fiscalPeriod, '%')))
          and (:issueDateFrom is null or d.issueDate >= :issueDateFrom)
          and (:issueDateTo is null or d.issueDate <= :issueDateTo)
        """)
    Page<DocumentRecord> searchByClassification(
        @Param("classification") DocumentClassification classification,
        @Param("referenceNumber") String referenceNumber,
        @Param("supplierOrContractReference") String supplierOrContractReference,
        @Param("fiscalPeriod") String fiscalPeriod,
        @Param("issueDateFrom") LocalDate issueDateFrom,
        @Param("issueDateTo") LocalDate issueDateTo,
        Pageable pageable
    );
}
