/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ShelfRepository
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.repository;

import dz.procsys.api.core.common.infrastructure.model.Shelf;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for Shelf entity.
 */
@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long>, JpaSpecificationExecutor<Shelf> {

    /**
     * Lists shelves by room identifier.
     *
     * @param roomId room identifier
     * @return list of shelves
     */
    List<Shelf> findByRoomId(Long roomId);
}