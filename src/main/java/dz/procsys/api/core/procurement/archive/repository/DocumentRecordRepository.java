package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.DocumentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRecordRepository extends JpaRepository<DocumentRecord, Long> {
}
