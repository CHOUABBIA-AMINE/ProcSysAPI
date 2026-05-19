/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyRepository
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.setting.model.SettingAccessPolicy;

/**
 * Repository interface for SettingAccessPolicy entity.
 * Provides security-oriented lookups to determine read/write privileges per setting per role.
 */
@Repository
public interface SettingAccessPolicyRepository extends JpaRepository<SettingAccessPolicy, Long>, JpaSpecificationExecutor<SettingAccessPolicy> {

    /**
     * Finds a SettingAccessPolicy by its id.
     *
     * @param Long the id
     * @return an Optional containing the found policy, or empty if not found
     */
    Optional<SettingAccessPolicy> findById(Long id);

    /**
     * Checks if a policy exists for a specific definition and role code.
     * Used to enforce the unique constraint at the application layer.
     *
     * @param settingDefinitionId the definition ID
     * @param roleCode            the role code
     * @return true if a policy already exists
     */
    boolean existsBySettingDefinitionIdAndRoleCode(Long settingDefinitionId, String roleCode);

    /**
     * Finds the access policy for a specific definition and role code.
     *
     * @param settingDefinitionId the definition ID
     * @param roleCode            the role code
     * @return an Optional containing the policy
     */
    Optional<SettingAccessPolicy> findBySettingDefinitionIdAndRoleCode(Long settingDefinitionId, String roleCode);

    /**
     * Retrieves all access policies for a given setting definition.
     *
     * @param settingDefinitionId the definition ID
     * @return a list of access policies
     */
    List<SettingAccessPolicy> findBySettingDefinitionId(Long settingDefinitionId);

    /**
     * Retrieves all policies for a given role code with pagination.
     *
     * @param roleCode the role code
     * @param pageable pagination parameters
     * @return a page of SettingAccessPolicy records
     */
    Page<SettingAccessPolicy> findByRoleCode(String roleCode, Pageable pageable);

    /**
     * Finds all writable policies for a given role code.
     *
     * @param roleCode the role code
     * @return a list of SettingAccessPolicy records where canWrite is true
     */
    List<SettingAccessPolicy> findByRoleCodeAndCanWriteTrue(String roleCode);
}
