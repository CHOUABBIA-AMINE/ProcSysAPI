/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingScopeTypeService
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingScopeTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingScopeTypeResponse;

/**
 * Service interface defining business operations for SettingScopeType management.
 */
public interface SettingScopeTypeService {

    /**
     * Creates a new SettingScopeType after validating code and priority uniqueness.
     *
     * @param request the creation payload
     * @return the persisted SettingScopeType as a response DTO
     */
    SettingScopeTypeResponse create(SettingScopeTypeCreateRequest request);

    /**
     * Updates an existing SettingScopeType identified by Long.
     *
     * @param Long    the stable Long of the target entity
     * @param request the update payload
     * @return the updated SettingScopeType as a response DTO
     */
    SettingScopeTypeResponse update(Long id, SettingScopeTypeUpdateRequest request);

    /**
     * Retrieves a SettingScopeType by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingScopeTypeResponse findById(Long id);

    /**
     * Retrieves a SettingScopeType by its unique code.
     *
     * @param code the business code
     * @return the corresponding response DTO
     */
    SettingScopeTypeResponse findByCode(String code);

    /**
     * Retrieves all SettingScopeTypes ordered by priority ascending.
     * Used during scope resolution chain construction.
     *
     * @return ordered list of all scope types
     */
    List<SettingScopeTypeResponse> findAllOrderedByPriority();

    /**
     * Retrieves a paginated list of all SettingScopeTypes.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingScopeTypeResponse> findAll(Pageable pageable);

    /**
     * Deletes a SettingScopeType by its Long.
     * Deletion is refused if the type is used by any SettingValue.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
