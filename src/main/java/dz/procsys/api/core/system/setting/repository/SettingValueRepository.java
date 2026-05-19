/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueRepository
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingValue;

/**
 * Repository interface for SettingValue entity.
 * Provides CRUD operations, scope-aware lookups, effective-date filtering,
 * pagination, and dynamic specification support.
 */
@Repository
public interface SettingValueRepository extends JpaRepository<SettingValue, Long>, JpaSpecificationExecutor<SettingValue> {

    /**
     * Finds a SettingValue by its Id.
     *
     * @param Id the Id to search for
     * @return an Optional containing the found SettingValue, or empty if not found
     */
    Optional<SettingValue> findById(Long id);

    /**
     * Checks if a SettingValue exists by its Id.
     *
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Checks if a SettingValue already exists for a given definition, scope type, and scope reference.
     * Used to enforce the unique constraint at the application layer before persistence.
     *
     * @param settingDefinitionId  the ID of the definition
     * @param scopeTypeId          the ID of the scope type
     * @param scopeReferenceId     the scope reference ID (nullable for GLOBAL)
     * @return true if a value already exists for this combination
     */
    boolean existsBySettingDefinitionIdAndScopeTypeIdAndScopeReferenceId(
            Long settingDefinitionId, Long scopeTypeId, Long scopeReferenceId);

    /**
     * Retrieves all effective values for a given setting definition ordered by scope priority.
     * Only values whose effective range covers the supplied date are returned.
     *
     * @param definitionId  the setting definition ID
     * @param now           the reference date for effective range check
     * @return list of effective SettingValues ordered by scope priority ascending
     */
    @Query("SELECT sv FROM SettingValue sv " +
           "JOIN sv.scopeType st " +
           "WHERE sv.settingDefinition.id = :definitionId " +
           "AND (sv.effectiveFrom IS NULL OR sv.effectiveFrom <= :now) " +
           "AND (sv.effectiveTo IS NULL OR sv.effectiveTo >= :now) " +
           "ORDER BY st.priority ASC")
    List<SettingValue> findEffectiveValuesByDefinitionOrdered(
            @Param("definitionId") Long definitionId,
            @Param("now") Date now);

    /**
     * Retrieves all values for a given setting definition and scope type.
     *
     * @param definitionId the definition ID
     * @param scopeTypeId  the scope type ID
     * @param pageable     pagination parameters
     * @return a page of SettingValues
     */
    Page<SettingValue> findBySettingDefinitionIdAndScopeTypeId(
            Long definitionId, Long scopeTypeId, Pageable pageable);

    /**
     * Retrieves all values for a given setting definition.
     *
     * @param definitionId the definition ID
     * @return list of SettingValues with eagerly loaded scope type
     */
    @EntityGraph(attributePaths = {"scopeType", "settingDefinition"})
    List<SettingValue> findBySettingDefinitionId(Long definitionId);
}
