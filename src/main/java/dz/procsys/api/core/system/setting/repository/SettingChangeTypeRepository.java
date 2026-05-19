/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeTypeRepository
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingChangeType;

/**
 * Repository interface for SettingChangeType entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingChangeTypeRepository extends JpaRepository<SettingChangeType, Long>, JpaSpecificationExecutor<SettingChangeType> {

    /**
     * Finds a SettingChangeType by its id.
     * 
     * @param Long the Id to search for
     * @return an Optional containing the found SettingChangeType, or empty if not found
     */
    Optional<SettingChangeType> findById(Long id);

    /**
     * Finds a SettingChangeType by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found SettingChangeType, or empty if not found
     */
    Optional<SettingChangeType> findByCode(String code);

    /**
     * Checks if a SettingChangeType exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a SettingChangeType exists by its id.
     * 
     * @param Long the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves all SettingChangeTypes matching the approval requirement flag.
     * 
     * @param requiresApproval true if approval is required
     * @param pageable pagination parameters
     * @return a page of SettingChangeTypes
     */
    Page<SettingChangeType> findByRequiresApproval(Boolean requiresApproval, Pageable pageable);
}
