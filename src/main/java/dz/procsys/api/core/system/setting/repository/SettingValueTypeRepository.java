/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueTypeRepository
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingValueType;

/**
 * Repository interface for SettingValueType entity.
 * Provides CRUD operations, pagination, and dynamic specification support.
 */
@Repository
public interface SettingValueTypeRepository extends JpaRepository<SettingValueType, Long>, JpaSpecificationExecutor<SettingValueType> {

    /**
     * Finds a SettingValueType by its Id.
     * 
     * @param Id the Id to search for
     * @return an Optional containing the found SettingValueType, or empty if not found
     */
    Optional<SettingValueType> findById(Long id);

    /**
     * Finds a SettingValueType by its unique code.
     * 
     * @param code the unique code
     * @return an Optional containing the found SettingValueType, or empty if not found
     */
    Optional<SettingValueType> findByCode(String code);

    /**
     * Checks if a SettingValueType exists by its unique code.
     * 
     * @param code the unique code
     * @return true if exists, false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Checks if a SettingValueType exists by its Id.
     * 
     * @param Id the Id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Retrieves all SettingValueTypes matching the encryption support flag with pagination.
     * 
     * @param supportsEncryption true if encryption is supported
     * @param pageable pagination information
     * @return a page of SettingValueTypes
     */
    Page<SettingValueType> findBySupportsEncryption(Boolean supportsEncryption, Pageable pageable);
}
