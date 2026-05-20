/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveFolderRepository
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.repository;

import dz.procsys.api.core.common.archive.model.ArchiveFolder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for ArchiveFolder entity.
 */
@Repository
public interface ArchiveFolderRepository extends JpaRepository<ArchiveFolder, Long>, JpaSpecificationExecutor<ArchiveFolder> {

    /**
     * Finds an archive folder by its code.
     *
     * @param code archive folder code
     * @return optional archive folder
     */
    Optional<ArchiveFolder> findByCode(String code);

    /**
     * Lists archive folders stored in a given archive box.
     *
     * @param archiveBoxId archive box identifier
     * @return list of archive folders
     */
    List<ArchiveFolder> findByArchiveBoxId(Long archiveBoxId);
}