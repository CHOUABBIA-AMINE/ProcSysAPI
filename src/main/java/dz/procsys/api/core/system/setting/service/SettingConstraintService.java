/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingConstraintService
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Service
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.service;

import java.util.List;

import dz.procsys.api.core.system.setting.dto.request.SettingConstraintCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingConstraintUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingConstraintResponse;

/**
 * Service interface defining business operations for SettingConstraint management.
 */
public interface SettingConstraintService {

    /**
     * Attaches a new constraint to a SettingDefinition.
     * Validates that the combination of definition + constraint type is unique.
     *
     * @param request the creation payload
     * @return the persisted constraint as a response DTO
     */
    SettingConstraintResponse create(SettingConstraintCreateRequest request);

    /**
     * Updates the value and error message of an existing SettingConstraint.
     *
     * @param Long    the stable Long of the target constraint
     * @param request the update payload
     * @return the updated constraint as a response DTO
     */
    SettingConstraintResponse update(Long id, SettingConstraintUpdateRequest request);

    /**
     * Retrieves a SettingConstraint by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingConstraintResponse findById(Long id);

    /**
     * Retrieves all constraints for a given setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @return list of constraint response DTOs
     */
    List<SettingConstraintResponse> findByDefinition(Long definitionId);

    /**
     * Deletes a SettingConstraint by its Long.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
