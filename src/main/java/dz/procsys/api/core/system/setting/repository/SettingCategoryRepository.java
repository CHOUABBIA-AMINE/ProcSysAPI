/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingCategoryRepository
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

import dz.procsys.api.core.system.setting.model.SettingCategory;

/**
 * Repository interface for SettingCategory entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingCategoryRepository extends JpaRepository<SettingCategory, Long>, JpaSpecificationExecutor<SettingCategory> {

    /**
     * Finds a SettingCategory by its id.
     * 
     * @param id the id to search for
     * @return an Optional containing the found SettingCategory, or empty if not found
     */
    Optional<SettingCategory> findById(Long id);

    /**
     * Finds a SettingCategory by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found SettingCategory, or empty if not found
     */
    Optional<SettingCategory> findByCode(String code);

    /**
     * Checks if a SettingCategory exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a SettingCategory exists by its id.
     * 
     * @param id the Id
     * @return true if exists, false otherwise
     */
    boolean existsByid(Long id);

    /**
     * Retrieves all SettingCategories matching the active status flag.
     * 
     * @param isActive true if active
     * @param pageable pagination parameters
     * @return a page of SettingCategories
     */
    Page<SettingCategory> findByIsActive(Boolean isActive, Pageable pageable);

    /**
     * Retrieves all root SettingCategories (where parentCategory is null).
     * 
     * @return list of root categories
     */
    List<SettingCategory> findByParentCategoryIsNull();
    
    /**
     * Finds a category by its ID and eagerly fetches its sub-categories.
     * 
     * @param id the ID of the category
     * @return Optional containing the category with fetched sub-categories
     */
    @EntityGraph(attributePaths = {"subCategories"})
    Optional<SettingCategory> findWithSubCategoriesById(Long id);
}
