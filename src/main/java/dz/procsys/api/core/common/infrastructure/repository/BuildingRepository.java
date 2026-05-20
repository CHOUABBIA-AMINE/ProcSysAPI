/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : BuildingRepository
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.repository;

import dz.procsys.api.core.common.infrastructure.model.Building;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for Building entity.
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long>, JpaSpecificationExecutor<Building> {

    /**
     * Finds a building by its unique French designation.
     *
     * @param designationFr French designation
     * @return optional building
     */
    Optional<Building> findByDesignationFr(String designationFr);
}