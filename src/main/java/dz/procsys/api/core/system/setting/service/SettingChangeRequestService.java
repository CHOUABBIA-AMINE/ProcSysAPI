/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestService
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

import dz.procsys.api.core.system.setting.dto.request.SettingChangeRequestCreateRequest;
import dz.procsys.api.core.system.setting.dto.request.SettingChangeRequestDecisionRequest;
import dz.procsys.api.core.system.setting.dto.response.SettingChangeRequestResponse;

/**
 * Service interface defining business operations for SettingChangeRequest approval workflow.
 */
public interface SettingChangeRequestService {

    /**
     * Submits a new change request for a governance-controlled setting.
     * Validates that the definition requires approval and that no pending request exists.
     *
     * @param request   the creation payload
     * @param requester the username submitting the request
     * @return the persisted change request as a response DTO with PENDING status
     */
    SettingChangeRequestResponse submit(SettingChangeRequestCreateRequest request, String requester);

    /**
     * Applies an approval or rejection decision to a pending change request.
     * On APPROVED: validates the proposed value against all constraints, applies it via
     * SettingValueService, records a SettingHistory entry, and transitions status to APPROVED.
     * On REJECTED: records the decision and transitions status to REJECTED.
     *
     * @param Long     the Long of the change request
     * @param decision the decision payload containing the verdict and optional remarks
     * @param approver the username of the approver
     * @return the updated change request response DTO
     */
    SettingChangeRequestResponse decide(Long id, SettingChangeRequestDecisionRequest decision, String approver);

    /**
     * Retrieves a SettingChangeRequest by its Long.
     *
     * @param Long the Long
     * @return the corresponding response DTO
     */
    SettingChangeRequestResponse findById(Long id);

    /**
     * Retrieves all change requests with a given status, newest first.
     *
     * @param status   the status filter (e.g., "PENDING", "APPROVED", "REJECTED")
     * @param pageable pagination parameters
     * @return a page of change request response DTOs
     */
    Page<SettingChangeRequestResponse> findByStatus(String status, Pageable pageable);

    /**
     * Retrieves all change requests for a specific setting definition.
     *
     * @param definitionId the Long of the setting definition
     * @param pageable       pagination parameters
     * @return a page of change request response DTOs
     */
    Page<SettingChangeRequestResponse> findByDefinition(Long definitionId, Pageable pageable);

    /**
     * Retrieves all change requests submitted by a specific user.
     *
     * @param requester the requester username
     * @param pageable  pagination parameters
     * @return a page of change request response DTOs
     */
    Page<SettingChangeRequestResponse> findByRequester(String requester, Pageable pageable);
}
