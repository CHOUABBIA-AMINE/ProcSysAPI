/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ConstraintTypeService
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

import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.ConstraintTypeUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.ConstraintTypeResponse;

/**
 * Service interface defining business operations for ConstraintType management.
 */
public interface ConstraintTypeService {

    /**
     * Creates a new ConstraintType after validating code uniqueness.
     *
     * @param request the creation payload
     * @return the persisted ConstraintType as a response DTO
     */
    ConstraintTypeResponse create(ConstraintTypeCreateRequest request);

    /**
     * Updates an existing ConstraintType identified by id.
     *
     * @param Long    the stable id of the target entity
     * @param request the update payload
     * @return the updated ConstraintType as a response DTO
     */
    ConstraintTypeResponse update(Long id, ConstraintTypeUpdateRequest request);

    /**
     * Retrieves a ConstraintType by its id.
     *
     * @param Long the Id
     * @return the corresponding response DTO
     */
    ConstraintTypeResponse findById(Long id);

    /**
     * Retrieves a ConstraintType by its unique code.
     *
     * @param code the business code
     * @return the corresponding response DTO
     */
    ConstraintTypeResponse findByCode(String code);

    /**
     * Retrieves a paginated list of all ConstraintTypes.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<ConstraintTypeResponse> findAll(Pageable pageable);

    /**
     * Deletes a ConstraintType by its id.
     * Deletion is refused if the type is used by any SettingConstraint.
     *
     * @param Long the Id of the entity to delete
     */
    void delete(Long id);
}
