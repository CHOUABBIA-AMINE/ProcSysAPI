/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingChangeTypeUpdateRequest
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Request
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * DTO for updating an existing SettingChangeType.
 */
@Schema(name = "SettingChangeTypeUpdateRequest", description = "Request payload for updating an existing setting change type")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeTypeUpdateRequest {

    /** Updated description. */
    @Schema(description = "Updated description", example = "Modifies an existing active setting value", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Updated approval requirement. */
    @Schema(description = "Whether approval is required for this change type", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Requires approval flag is mandatory")
    private Boolean requiresApproval;
}
