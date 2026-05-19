/**
 *	
 *	@Author		: System
 *
 *	@Name		: ConstraintTypeRepository
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.ConstraintType;

/**
 * Repository interface for ConstraintType entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface ConstraintTypeRepository extends JpaRepository<ConstraintType, Long>, JpaSpecificationExecutor<ConstraintType> {

    /**
     * Finds a ConstraintType by its Long.
     * 
     * @param id the Long to search for
     * @return an Optional containing the found ConstraintType, or empty if not found
     */
    Optional<ConstraintType> findById(Long id);

    /**
     * Finds a ConstraintType by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found ConstraintType, or empty if not found
     */
    Optional<ConstraintType> findByCode(String code);

    /**
     * Checks if a ConstraintType exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a ConstraintType exists by its Long.
     * 
     * @param id the Long
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
}
