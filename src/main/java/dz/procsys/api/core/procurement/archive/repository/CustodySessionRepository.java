package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.CustodySession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustodySessionRepository extends JpaRepository<CustodySession, Long> {
}
