/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistoryRepository
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
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingHistory;

/**
 * Repository interface for SettingHistory entity.
 * Provides read-oriented operations for the immutable audit trail of setting changes.
 */
@Repository
public interface SettingHistoryRepository extends JpaRepository<SettingHistory, Long>, JpaSpecificationExecutor<SettingHistory> {

    /**
     * Finds a SettingHistory record by its Id.
     *
     * @param Id the Id to search for
     * @return an Optional containing the found SettingHistory, or empty if not found
     */
    Optional<SettingHistory> findById(Long id);

    /**
     * Checks if a SettingHistory entry exists by its Id.
     *
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves the full audit trail for a given setting definition, newest first.
     *
     * @param definitionId the setting definition ID
     * @param pageable     pagination parameters
     * @return a page of SettingHistory records
     */
    @EntityGraph(attributePaths = {"changeType"})
    Page<SettingHistory> findBySettingDefinitionIdOrderByCreatedAtDesc(Long definitionId, Pageable pageable);

    /**
     * Retrieves the full audit trail for a given setting definition and scope reference.
     *
     * @param definitionId   the setting definition ID
     * @param scopeReference the scope reference string (e.g., "GLOBAL", user ID string)
     * @param pageable       pagination parameters
     * @return a page of SettingHistory records
     */
    Page<SettingHistory> findBySettingDefinitionIdAndScopeReferenceOrderByCreatedAtDesc(
            Long definitionId, String scopeReference, Pageable pageable);

    /**
     * Retrieves audit records created by a specific user.
     *
     * @param createdBy the username
     * @return list of SettingHistory records
     */
    List<SettingHistory> findByCreatedBy(String createdBy);
}
