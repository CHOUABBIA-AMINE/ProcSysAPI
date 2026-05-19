/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueService
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

import dz.procsys.api.core.system.setting.dto.request.SettingValueCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingValueUpdateRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingValueResponse;

/**
 * Service interface defining business operations for SettingValue management.
 */
public interface SettingValueService {

    /**
     * Creates a new SettingValue for a specific definition/scope combination.
     * Validates: scope uniqueness, effective date range, constraint compliance, and encryption eligibility.
     *
     * @param request the creation payload
     * @return the persisted value as a response DTO (encrypted values are masked)
     */
    SettingValueResponse create(SettingValueCreateRequest request);

    /**
     * Updates an existing SettingValue.
     * Validates: immutability of definition, effective dates, constraint compliance.
     * Creates a SettingHistory record on successful update.
     *
     * @param Long    the stable Long of the target value
     * @param request the update payload
     * @return the updated value as a response DTO
     */
    SettingValueResponse update(Long id, SettingValueUpdateRequest request);

    /**
     * Retrieves a SettingValue by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO (encrypted values masked)
     */
    SettingValueResponse findById(Long Long);

    /**
     * Resolves the effective setting value for a given definition code, scope type, and reference.
     * Uses the dynamic scope resolution chain ordered by scope priority.
     *
     * @param definitionCode   the setting key
     * @param scopeTypeCode    the code of the desired scope type
     * @param scopeReferenceId the reference ID of the scope target (null for GLOBAL)
     * @return the resolved effective SettingValue response DTO
     */
    SettingValueResponse resolve(String definitionCode, String scopeTypeCode, Long scopeReferenceId);

    /**
     * Retrieves all values for a given setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @return list of value response DTOs
     */
    List<SettingValueResponse> findByDefinition(Long definitionId);

    /**
     * Retrieves a paginated list of all SettingValues.
     *
     * @param pageable pagination and sorting parameters
     * @return a page of response DTOs
     */
    Page<SettingValueResponse> findAll(Pageable pageable);

    /**
     * Deletes a SettingValue by its Long.
     * Creates a SettingHistory record marking the deletion.
     *
     * @param Long the Long of the entity to delete
     */
    void delete(Long Long);
}
