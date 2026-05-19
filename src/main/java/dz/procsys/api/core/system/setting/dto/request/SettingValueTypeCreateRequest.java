/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueTypeCreateRequest
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
 * DTO for creating a new SettingValueType.
 */
@Schema(
    name = "SettingValueTypeCreateRequest",
    description = "Request payload for creating a new setting value type"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueTypeCreateRequest {

    /** Unique code identifying the value type. */
    @Schema(
        description = "Unique business code of the value type",
        example = "STRING",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    /** Description of what the type represents. */
    @Schema(
        description = "Human-readable description of the value type",
        example = "Textual string data",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Whether this type supports encrypted storage. */
    @Schema(
        description = "Whether values of this type can be encrypted at rest",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Encryption support flag is mandatory")
    private Boolean supportsEncryption;
}
