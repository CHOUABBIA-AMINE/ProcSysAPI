/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyService
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

import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingAccessPolicyUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingAccessPolicyResponse;

/**
 * Service interface defining business operations for SettingAccessPolicy management.
 */
public interface SettingAccessPolicyService {

    /**
     * Defines a new access policy for a setting definition and role combination.
     *
     * @param request the creation payload
     * @return the persisted access policy as a response DTO
     */
    SettingAccessPolicyResponse create(SettingAccessPolicyCreateRequest request);

    /**
     * Updates the read/write permissions of an existing access policy.
     *
     * @param Long    the stable Long of the target policy
     * @param request the update payload
     * @return the updated access policy as a response DTO
     */
    SettingAccessPolicyResponse update(Long id, SettingAccessPolicyUpdateRequest request);

    /**
     * Retrieves an access policy by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingAccessPolicyResponse findById(Long id);

    /**
     * Retrieves all access policies for a given setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @return list of access policy response DTOs
     */
    List<SettingAccessPolicyResponse> findByDefinition(Long definitionId);

    /**
     * Retrieves all access policies for a given role code with pagination.
     *
     * @param roleCode the role code
     * @param pageable pagination parameters
     * @return a page of access policy response DTOs
     */
    Page<SettingAccessPolicyResponse> findByRole(String roleCode, Pageable pageable);

    /**
     * Checks whether a given role can read a specific setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @param roleCode       the role code
     * @return true if the role has read access, false otherwise
     */
    boolean canRead(Long definitionId, String roleCode);

    /**
     * Checks whether a given role can write/modify a specific setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @param roleCode       the role code
     * @return true if the role has write access, false otherwise
     */
    boolean canWrite(Long definitionId, String roleCode);

    /**
     * Deletes an access policy by its Long.
     *
     * @param Long the Long of the policy to delete
     */
    void delete(Long id);
}
