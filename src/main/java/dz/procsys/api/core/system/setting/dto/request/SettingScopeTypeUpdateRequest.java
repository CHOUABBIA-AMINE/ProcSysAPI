/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingScopeTypeUpdateRequest
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
 * DTO for updating an existing SettingScopeType.
 */
@Schema(
    name = "SettingScopeTypeUpdateRequest",
    description = "Request payload for updating an existing setting scope type"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingScopeTypeUpdateRequest {

    /** Updated resolution priority. */
    @Schema(description = "Resolution priority — lower value means higher precedence", example = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Priority is mandatory")
    @Positive(message = "Priority must be positive")
    private Integer priority;

    /** Updated description. */
    @Schema(description = "Updated human-readable description", example = "Organization-level scope", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}
