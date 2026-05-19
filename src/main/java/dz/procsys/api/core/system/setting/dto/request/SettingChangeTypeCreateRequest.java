/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingChangeTypeCreateRequest
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for creating a new SettingChangeType.
 */
@Schema(name = "SettingChangeTypeCreateRequest", description = "Request payload for creating a new setting change type")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeTypeCreateRequest {

    /** Unique code. */
    @Schema(description = "Unique code of the change type", example = "UPDATE", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    /** Description. */
    @Schema(description = "Description of the change type", example = "Modifies an existing setting value", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Whether approval is required. */
    @Schema(description = "Whether changes of this type require managerial approval", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Requires approval flag is mandatory")
    private Boolean requiresApproval;
}
