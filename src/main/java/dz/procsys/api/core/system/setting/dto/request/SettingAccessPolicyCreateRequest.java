/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyCreateRequest
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
 * DTO for creating a new SettingAccessPolicy.
 */
@Schema(name = "SettingAccessPolicyCreateRequest", description = "Request payload for defining a read/write access policy for a setting")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingAccessPolicyCreateRequest {

    /** Setting definition ID. */
    @Schema(description = "ID of the setting definition this policy governs", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Setting definition ID is mandatory")
    @Positive(message = "Setting definition ID must be positive")
    private Long settingDefinitionId;

    /** Role code. */
    @Schema(description = "Role or privilege code being granted access", example = "SYS_ADMIN", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    @NotBlank(message = "Role code is mandatory")
    @Size(max = 100, message = "Role code must not exceed 100 characters")
    private String roleCode;

    /** Can read flag. */
    @Schema(description = "Whether the role can read this setting", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "CanRead flag is mandatory")
    private Boolean canRead;

    /** Can write flag. */
    @Schema(description = "Whether the role can write/modify this setting", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "CanWrite flag is mandatory")
    private Boolean canWrite;
}
