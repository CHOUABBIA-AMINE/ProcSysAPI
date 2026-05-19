/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueTypeUpdateRequest
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
 * DTO for updating an existing SettingValueType.
 * The code is immutable and cannot be changed after creation.
 */
@Schema(
    name = "SettingValueTypeUpdateRequest",
    description = "Request payload for updating an existing setting value type (code is immutable)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueTypeUpdateRequest {

    /** Updated description. */
    @Schema(
        description = "Updated description of the value type",
        example = "Arbitrary text string",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Updated encryption support flag. */
    @Schema(
        description = "Whether values of this type can be encrypted at rest",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Encryption support flag is mandatory")
    private Boolean supportsEncryption;
}
