/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingScopeTypeCreateRequest
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
 * DTO for creating a new SettingScopeType.
 */
@Schema(
    name = "SettingScopeTypeCreateRequest",
    description = "Request payload for creating a new setting scope type"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingScopeTypeCreateRequest {

    /** Unique code. */
    @Schema(description = "Unique code of the scope type", example = "GLOBAL", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    /** Resolution priority (lower = higher precedence). */
    @Schema(description = "Resolution priority — lower value means higher precedence during setting resolution", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Priority is mandatory")
    @Positive(message = "Priority must be positive")
    private Integer priority;

    /** Description. */
    @Schema(description = "Human-readable description", example = "System-wide global scope", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}
