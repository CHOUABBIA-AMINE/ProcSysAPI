/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingDefinitionRepository
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
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingDefinition;

/**
 * Repository interface for SettingDefinition entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingDefinitionRepository extends JpaRepository<SettingDefinition, Long>, JpaSpecificationExecutor<SettingDefinition> {

    /**
     * Finds a SettingDefinition by its Id.
     * 
     * @param Id the Id to search for
     * @return an Optional containing the found SettingDefinition, or empty if not found
     */
    Optional<SettingDefinition> findById(Long id);

    /**
     * Finds a SettingDefinition by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found SettingDefinition, or empty if not found
     */
    Optional<SettingDefinition> findByCode(String code);

    /**
     * Checks if a SettingDefinition exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a SettingDefinition exists by its Id.
     * 
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves all active SettingDefinitions.
     * 
     * @param isActive the active status
     * @param pageable pagination parameters
     * @return a page of SettingDefinitions
     */
    Page<SettingDefinition> findByIsActive(Boolean isActive, Pageable pageable);

    /**
     * Finds setting definitions by their category ID.
     * 
     * @param categoryId the ID of the category
     * @param pageable pagination parameters
     * @return a page of SettingDefinitions
     */
    Page<SettingDefinition> findByCategoryId(Long categoryId, Pageable pageable);

    /**
     * Retrieves a SettingDefinition along with its associated category and value type.
     * 
     * @param code the unique code
     * @return Optional containing the setting definition
     */
    @EntityGraph(attributePaths = {"category", "valueType", "constraints"})
    Optional<SettingDefinition> findWithDetailsByCode(String code);
}
