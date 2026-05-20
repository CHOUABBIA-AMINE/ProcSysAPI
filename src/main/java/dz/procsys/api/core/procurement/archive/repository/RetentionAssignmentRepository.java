package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.RetentionAssignment;
import dz.procsys.api.core.procurement.archive.model.RetentionClass;
import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RetentionAssignmentRepository extends JpaRepository<RetentionAssignment, Long> {
    List<RetentionAssignment> findByRetentionPolicyRetentionClass(RetentionClass retentionClass);
    List<RetentionAssignment> findByDisposalEligibilityDateLessThanEqualAndLifecycleStateIn(LocalDate date, List<RetentionLifecycleState> states);
}
