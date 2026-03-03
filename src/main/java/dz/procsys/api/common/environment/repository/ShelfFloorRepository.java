/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.procsys.api.common.environment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.procsys.api.common.environment.model.ShelfFloor;

/**
 * ShelfFloor Repository
 */
@Repository
public interface ShelfFloorRepository extends JpaRepository<ShelfFloor, Long> {
    
}
