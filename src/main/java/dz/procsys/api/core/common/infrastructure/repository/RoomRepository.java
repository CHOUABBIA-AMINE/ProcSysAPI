/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : RoomRepository
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.repository;

import dz.procsys.api.core.common.infrastructure.model.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for Room entity.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    /**
     * Lists rooms by building identifier.
     *
     * @param buildingId building identifier
     * @return list of rooms
     */
    List<Room> findByBuildingId(Long buildingId);
}