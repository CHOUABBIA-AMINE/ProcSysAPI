/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestRepository
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

import dz.procsys.api.core.system.setting.model.SettingChangeRequest;

/**
 * Repository interface for SettingChangeRequest entity.
 * Provides workflow-oriented queries for approval lifecycle management.
 */
@Repository
public interface SettingChangeRequestRepository extends JpaRepository<SettingChangeRequest, Long>, JpaSpecificationExecutor<SettingChangeRequest> {

    /**
     * Finds a SettingChangeRequest by its id.
     *
     * @param Long the Id
     * @return an Optional containing the found request, or empty if not found
     */
    Optional<SettingChangeRequest> findById(Long id);

    /**
     * Checks if a SettingChangeRequest exists by its id.
     *
     * @param Long the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves all change requests with a given status, newest first.
     *
     * @param status   the status string (e.g., "PENDING", "APPROVED", "REJECTED")
     * @param pageable pagination parameters
     * @return a page of SettingChangeRequest records
     */
    @EntityGraph(attributePaths = {"settingDefinition", "changeType"})
    Page<SettingChangeRequest> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);

    /**
     * Retrieves all pending change requests for a specific setting definition.
     *
     * @param settingDefinitionId the definition ID
     * @return list of pending requests
     */
    List<SettingChangeRequest> findBySettingDefinitionIdAndStatus(Long settingDefinitionId, String status);

    /**
     * Retrieves all change requests submitted by a specific user.
     *
     * @param createdBy the requester username
     * @param pageable  pagination parameters
     * @return a page of SettingChangeRequest records
     */
    Page<SettingChangeRequest> findByCreatedByOrderByCreatedAtDesc(String createdBy, Pageable pageable);

    /**
     * Counts the number of pending requests for a given setting definition.
     *
     * @param settingDefinitionId the definition ID
     * @param status              the status string
     * @return the count of matching requests
     */
    long countBySettingDefinitionIdAndStatus(Long settingDefinitionId, String status);

    /**
     * Retrieves all change requests for a specific setting definition with pagination.
     *
     * @param settingDefinitionId the definition ID
     * @param pageable            pagination parameters
     * @return a page of SettingChangeRequest records
     */
    Page<SettingChangeRequest> findBySettingDefinitionIdOrderByCreatedAtDesc(Long settingDefinitionId, Pageable pageable);
}
