/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveBoxRepository
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.repository;

import dz.procsys.api.core.common.archive.model.ArchiveBox;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for ArchiveBox entity.
 */
@Repository
public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox, Long>, JpaSpecificationExecutor<ArchiveBox> {

    /**
     * Finds an archive box by its code.
     *
     * @param code archive box code
     * @return optional archive box
     */
    Optional<ArchiveBox> findByCode(String code);

    /**
     * Lists archive boxes located on a given shelf.
     *
     * @param shelfId shelf identifier
     * @return list of archive boxes
     */
    List<ArchiveBox> findByShelfId(Long shelfId);
}