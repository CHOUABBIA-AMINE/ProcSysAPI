/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestDecisionRequest
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Request
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for approving or rejecting a SettingChangeRequest.
 * The approver sets the decision (APPROVED or REJECTED) and provides remarks.
 */
@Schema(name = "SettingChangeRequestDecisionRequest", description = "Request payload for approving or rejecting a pending setting change request")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeRequestDecisionRequest {

    /**
     * The decision to apply.
     * Must be either APPROVED or REJECTED.
     */
    @Schema(
        description = "The decision applied to the change request",
        example = "APPROVED",
        allowableValues = {"APPROVED", "REJECTED"},
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Decision is mandatory")
    @Pattern(regexp = "^(APPROVED|REJECTED)$", message = "Decision must be APPROVED or REJECTED")
    private String decision;

    /** Approver remarks or rejection reason. */
    @Schema(description = "Remarks from the approver; mandatory when rejecting", example = "Approved — aligns with security policy v2.4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 500)
    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String approverRemarks;
}
