package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.CustodyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustodyHolderRepository extends JpaRepository<CustodyHolder, Long> {
    Optional<CustodyHolder> findByActorIdentifier(String actorIdentifier);
}
