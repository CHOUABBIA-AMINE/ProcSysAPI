/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyUpdateRequest
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for updating an existing SettingAccessPolicy.
 * Only the permission flags can be updated; setting and role association are immutable.
 */
@Schema(name = "SettingAccessPolicyUpdateRequest", description = "Request payload for updating permissions of an access policy")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingAccessPolicyUpdateRequest {

    /** Updated can-read flag. */
    @Schema(description = "Updated read permission", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "CanRead flag is mandatory")
    private Boolean canRead;

    /** Updated can-write flag. */
    @Schema(description = "Updated write permission", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "CanWrite flag is mandatory")
    private Boolean canWrite;
}
