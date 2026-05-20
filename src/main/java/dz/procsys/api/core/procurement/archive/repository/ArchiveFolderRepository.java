package dz.procsys.api.core.procurement.archive.repository;

import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveFolderRepository extends JpaRepository<ArchiveFolder, Long> {
    boolean existsByCode(String code);
}
