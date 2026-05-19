/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestCreateRequest
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for submitting a new SettingChangeRequest.
 * Initiates an approval workflow when a governance-protected setting must be modified.
 */
@Schema(name = "SettingChangeRequestCreateRequest", description = "Request payload for submitting a setting change request for approval")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeRequestCreateRequest {

    /** Setting definition ID. */
    @Schema(description = "ID of the setting definition targeted by this request", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Setting definition ID is mandatory")
    @Positive(message = "Setting definition ID must be positive")
    private Long settingDefinitionId;

    /** Change type ID. */
    @Schema(description = "ID of the change type (e.g., UPDATE, DELETE)", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Change type ID is mandatory")
    @Positive(message = "Change type ID must be positive")
    private Long changeTypeId;

    /** Scope reference. */
    @Schema(description = "Scope reference to which the change applies (e.g., 'GLOBAL', user ID as string)", example = "GLOBAL", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    @NotBlank(message = "Scope reference is mandatory")
    @Size(max = 100, message = "Scope reference must not exceed 100 characters")
    private String scopeReference;

    /** The proposed new value. */
    @Schema(description = "The proposed new value for the setting", example = "60", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Proposed value is mandatory")
    private String proposedValue;

    /** Business justification. */
    @Schema(description = "Business justification for the requested change", example = "Increasing session timeout per security audit recommendation SR-2026-04", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 500)
    @Size(max = 500, message = "Justification must not exceed 500 characters")
    private String justification;
}
