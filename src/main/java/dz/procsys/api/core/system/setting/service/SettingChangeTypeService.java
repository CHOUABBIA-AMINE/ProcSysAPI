/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeTypeService
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

import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingChangeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeTypeResponse;

/**
 * Service interface defining business operations for SettingChangeType management.
 */
public interface SettingChangeTypeService {

    /**
     * Creates a new SettingChangeType after validating code uniqueness.
     *
     * @param request the creation payload
     * @return the persisted SettingChangeType as a response DTO
     */
    SettingChangeTypeResponse create(SettingChangeTypeCreateRequest request);

    /**
     * Updates an existing SettingChangeType identified by Long.
     *
     * @param Long    the stable Long of the target entity
     * @param request the update payload
     * @return the updated SettingChangeType as a response DTO
     */
    SettingChangeTypeResponse update(Long id, SettingChangeTypeUpdateRequest request);

    /**
     * Retrieves a SettingChangeType by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingChangeTypeResponse findById(Long id);

    /**
     * Retrieves a SettingChangeType by its unique code.
     *
     * @param code the business code
     * @return the corresponding response DTO
     */
    SettingChangeTypeResponse findByCode(String code);

    /**
     * Retrieves a paginated list of all SettingChangeTypes.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingChangeTypeResponse> findAll(Pageable pageable);

    /**
     * Deletes a SettingChangeType by its Long.
     * Deletion is refused if the type is used by any SettingChangeRequest or SettingHistory.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
