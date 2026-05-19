/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingDefinitionUpdateRequest
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
 * DTO for updating an existing SettingDefinition.
 * The code is immutable and cannot be changed after creation.
 */
@Schema(name = "SettingDefinitionUpdateRequest", description = "Request payload for updating an existing setting definition (code is immutable)")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingDefinitionUpdateRequest {

    /** Updated display name. */
    @Schema(description = "Updated display name", example = "Session Timeout Duration (Minutes)", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    /** Updated description. */
    @Schema(description = "Updated description", example = "Duration of user inactivity before session expires", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 1000)
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    /** Updated default value. */
    @Schema(description = "Updated default value applied when no scoped value is found", example = "60", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String defaultValue;

    /** Updated approval requirement. */
    @Schema(description = "Whether modifying this setting requires an approval workflow", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "RequiresApproval flag is mandatory")
    private Boolean requiresApproval;

    /** Updated category ID. */
    @Schema(description = "Updated category ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Category ID is mandatory")
    @Positive(message = "Category ID must be positive")
    private Long categoryId;
}
