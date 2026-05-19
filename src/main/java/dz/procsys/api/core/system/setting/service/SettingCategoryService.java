/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingCategoryService
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

import dz.procsys.api.core.system.setting.dto.request.SettingCategoryCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingCategoryUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingCategoryResponse;

/**
 * Service interface defining business operations for SettingCategory management.
 */
public interface SettingCategoryService {

    /**
     * Creates a new SettingCategory.
     *
     * @param request the creation payload
     * @return the persisted category as a response DTO
     */
    SettingCategoryResponse create(SettingCategoryCreateRequest request);

    /**
     * Updates an existing SettingCategory identified by Long.
     *
     * @param Long    the stable Long of the target entity
     * @param request the update payload
     * @return the updated category as a response DTO
     */
    SettingCategoryResponse update(Long id, SettingCategoryUpdateRequest request);

    /**
     * Activates a SettingCategory, making it visible and selectable.
     *
     * @param Long the Long of the category to activate
     * @return the updated response DTO
     */
    SettingCategoryResponse activate(Long id);

    /**
     * Deactivates a SettingCategory.
     *
     * @param Long the Long of the category to deactivate
     * @return the updated response DTO
     */
    SettingCategoryResponse deactivate(Long id);

    /**
     * Retrieves a SettingCategory by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingCategoryResponse findById(Long id);

    /**
     * Retrieves a SettingCategory by its unique code.
     *
     * @param code the business code
     * @return the corresponding response DTO
     */
    SettingCategoryResponse findByCode(String code);

    /**
     * Retrieves all root-level categories (where parentCategory is null).
     *
     * @return list of root category response DTOs
     */
    List<SettingCategoryResponse> findRootCategories();

    /**
     * Retrieves a paginated list of all SettingCategories.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingCategoryResponse> findAll(Pageable pageable);

    /**
     * Retrieves a paginated list of active SettingCategories.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of active response DTOs
     */
    Page<SettingCategoryResponse> findAllActive(Pageable pageable);

    /**
     * Deletes a SettingCategory by its Long.
     * Deletion is refused if the category contains any settings or sub-categories.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long id);
}
