/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingConstraintRepository
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

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingConstraint;

/**
 * Repository interface for SettingConstraint entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingConstraintRepository extends JpaRepository<SettingConstraint, Long>, JpaSpecificationExecutor<SettingConstraint> {

    /**
     * Finds a SettingConstraint by its Id.
     * 
     * @param Id the Id to search for
     * @return an Optional containing the found SettingConstraint, or empty if not found
     */
    Optional<SettingConstraint> findById(Long id);

    /**
     * Checks if a SettingConstraint exists by its Id.
     * 
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Checks if a specific constraint type is already applied to a setting definition.
     * 
     * @param settingDefinitionId the definition ID
     * @param constraintTypeId the constraint type ID
     * @return true if it exists
     */
    boolean existsBySettingDefinitionIdAndConstraintTypeId(Long settingDefinitionId, Long constraintTypeId);
    
    /**
     * Finds all constraints for a specific setting definition ID.
     * 
     * @param settingDefinitionId the definition ID
     * @return list of constraints
     */
    @EntityGraph(attributePaths = {"constraintType"})
    List<SettingConstraint> findBySettingDefinitionId(Long settingDefinitionId);
}
