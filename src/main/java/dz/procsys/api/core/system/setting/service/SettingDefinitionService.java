/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingDefinitionService
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

import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingDefinitionUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingDefinitionResponse;

/**
 * Service interface defining business operations for SettingDefinition (aggregate root) management.
 */
public interface SettingDefinitionService {

    /**
     * Creates a new SettingDefinition after validating code uniqueness and referenced entities.
     *
     * @param request the creation payload
     * @return the persisted definition as a response DTO
     */
    SettingDefinitionResponse create(SettingDefinitionCreateRequest request);

    /**
     * Updates a SettingDefinition identified by Long.
     * Immutability is enforced: if isMutable is false, an exception is thrown.
     *
     * @param Long    the stable Long
     * @param request the update payload
     * @return the updated definition as a response DTO
     */
    SettingDefinitionResponse update(Long id, SettingDefinitionUpdateRequest request);

    /**
     * Activates a SettingDefinition, making it resolvable.
     *
     * @param Long the Long of the definition to activate
     * @return the updated response DTO
     */
    SettingDefinitionResponse activate(Long id);

    /**
     * Deactivates a SettingDefinition so it is excluded from resolution.
     *
     * @param Long the Long of the definition to deactivate
     * @return the updated response DTO
     */
    SettingDefinitionResponse deactivate(Long id);

    /**
     * Retrieves a SettingDefinition by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingDefinitionResponse findById(Long id);

    /**
     * Retrieves a SettingDefinition by its unique business code.
     *
     * @param code the setting key
     * @return the corresponding response DTO
     */
    SettingDefinitionResponse findByCode(String code);

    /**
     * Retrieves a paginated list of all SettingDefinitions.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingDefinitionResponse> findAll(Pageable pageable);

    /**
     * Retrieves a paginated list of active SettingDefinitions.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of active response DTOs
     */
    Page<SettingDefinitionResponse> findAllActive(Pageable pageable);

    /**
     * Retrieves all SettingDefinitions belonging to a given category.
     *
     * @param categoryId the Long of the category
     * @param pageable     pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingDefinitionResponse> findByCategory(Long categoryId, Pageable pageable);

    /**
     * Deletes a SettingDefinition by its Long.
     * Deletion is refused if there are associated values, history entries, or pending change requests.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
