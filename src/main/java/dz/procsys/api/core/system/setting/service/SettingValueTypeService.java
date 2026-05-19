/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueTypeService
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

import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueTypeResponse;

/**
 * Service interface defining business operations for SettingValueType management.
 */
public interface SettingValueTypeService {

    /**
     * Creates a new SettingValueType after validating code uniqueness.
     *
     * @param request the creation payload
     * @return the persisted SettingValueType as a response DTO
     */
    SettingValueTypeResponse create(SettingValueTypeCreateRequest request);

    /**
     * Updates an existing SettingValueType identified by Long.
     *
     * @param Long    the stable Long of the target entity
     * @param request the update payload
     * @return the updated SettingValueType as a response DTO
     */
    SettingValueTypeResponse update(Long id, SettingValueTypeUpdateRequest request);

    /**
     * Retrieves a SettingValueType by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingValueTypeResponse findById(Long id);

    /**
     * Retrieves a SettingValueType by its unique code.
     *
     * @param code the business code
     * @return the corresponding response DTO
     */
    SettingValueTypeResponse findByCode(String code);

    /**
     * Retrieves a paginated list of all SettingValueTypes.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingValueTypeResponse> findAll(Pageable pageable);

    /**
     * Deletes a SettingValueType by its Long.
     * Deletion is refused if the type is in use by any SettingDefinition.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
