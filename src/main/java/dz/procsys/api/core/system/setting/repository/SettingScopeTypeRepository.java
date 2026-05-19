/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingScopeTypeRepository
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingScopeType;

/**
 * Repository interface for SettingScopeType entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingScopeTypeRepository extends JpaRepository<SettingScopeType, Long>, JpaSpecificationExecutor<SettingScopeType> {

    /**
     * Finds a SettingScopeType by its Id.
     * 
     * @param Id the Id to search for
     * @return an Optional containing the found SettingScopeType, or empty if not found
     */
    Optional<SettingScopeType> findById(Long id);

    /**
     * Finds a SettingScopeType by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found SettingScopeType, or empty if not found
     */
    Optional<SettingScopeType> findByCode(String code);

    /**
     * Checks if a SettingScopeType exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a SettingScopeType exists by its Id.
     * 
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves all SettingScopeTypes ordered by priority (highest priority first).
     * 
     * @return a list of SettingScopeTypes ordered by priority ascending
     */
    List<SettingScopeType> findAllByOrderByPriorityAsc();
    
    /**
     * Finds setting scope types by priority less than or equal to a given value.
     * 
     * @param priority the priority threshold
     * @param pageable pagination parameters
     * @return paginated scope types
     */
    Page<SettingScopeType> findByPriorityLessThanEqual(Integer priority, Pageable pageable);
}
