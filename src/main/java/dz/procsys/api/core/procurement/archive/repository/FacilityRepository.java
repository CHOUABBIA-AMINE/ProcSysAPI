package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Optional<Facility> findByCode(String code);
}
