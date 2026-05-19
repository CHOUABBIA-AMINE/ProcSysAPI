/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistoryService
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dz.procsys.api.core.system.setting.dto.response.SettingHistoryResponse;
import dz.procsys.api.core.system.setting.model.SettingChangeType;
import dz.procsys.api.core.system.setting.model.SettingDefinition;

/**
 * Service interface for SettingHistory audit trail operations.
 * History records are immutable and created internally by other services.
 */
public interface SettingHistoryService {

    /**
     * Records a new history entry for a setting change.
     * This method is called internally by SettingValueService and SettingChangeRequestService.
     *
     * @param definition     the affected setting definition
     * @param changeType     the type of change applied
     * @param scopeReference the scope reference (e.g., "GLOBAL", user ID as string)
     * @param oldValue       the value before the change (null if created)
     * @param newValue       the value after the change (null if deleted)
     * @param reason         the justification for the change
     * @param changedBy      the username who triggered the change
     */
    void record(SettingDefinition definition, SettingChangeType changeType,
                String scopeReference, String oldValue, String newValue,
                String reason, String changedBy);

    /**
     * Retrieves the full audit trail for a specific setting definition, newest first.
     *
     * @param definitionId the Long of the setting definition
     * @param pageable       pagination parameters
     * @return a page of history response DTOs
     */
    Page<SettingHistoryResponse> findByDefinition(Long definitionId, Pageable pageable);

    /**
     * Retrieves the audit trail for a setting definition filtered by scope reference.
     *
     * @param definitionId the Long of the setting definition
     * @param scopeReference the scope reference string
     * @param pageable       pagination parameters
     * @return a page of history response DTOs
     */
    Page<SettingHistoryResponse> findByDefinitionAndScope(Long definitionId, String scopeReference, Pageable pageable);

    /**
     * Retrieves a single history record by its Long.
     *
     * @param Long the Long of the history entry
     * @return the corresponding response DTO
     */
    SettingHistoryResponse findById(Long id);
}
