package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.RetentionAssignment;
import dz.procsys.api.core.procurement.archive.model.RetentionClass;
import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleState;
import dz.procsys.api.core.procurement.archive.repository.projection.RetentionAssignmentSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RetentionAssignmentRepository extends JpaRepository<RetentionAssignment, Long> {
    List<RetentionAssignment> findByRetentionPolicyRetentionClass(RetentionClass retentionClass);
    List<RetentionAssignment> findByDisposalEligibilityDateLessThanEqualAndLifecycleStateIn(LocalDate date, List<RetentionLifecycleState> states);
    Optional<RetentionAssignment> findTopByArchiveFolderIdOrderByIdDesc(Long archiveFolderId);

    @Query("""
        select ra.id as id,
               rp.id as retentionPolicyId,
               rp.code as retentionPolicyCode,
               ra.lifecycleState as lifecycleState,
               ra.disposalEligibilityDate as disposalEligibilityDate,
               dr.id as documentRecordId,
               af.id as archiveFolderId
          from RetentionAssignment ra
          join ra.retentionPolicy rp
          left join ra.documentRecord dr
          left join ra.archiveFolder af
         where (:retentionClass is null or rp.retentionClass = :retentionClass)
           and (:lifecycleState is null or ra.lifecycleState = :lifecycleState)
           and (:eligibleBefore is null or ra.disposalEligibilityDate <= :eligibleBefore)
    """)
    Page<RetentionAssignmentSearchView> searchRetention(
        @Param("retentionClass") RetentionClass retentionClass,
        @Param("lifecycleState") RetentionLifecycleState lifecycleState,
        @Param("eligibleBefore") LocalDate eligibleBefore,
        Pageable pageable
    );
}
