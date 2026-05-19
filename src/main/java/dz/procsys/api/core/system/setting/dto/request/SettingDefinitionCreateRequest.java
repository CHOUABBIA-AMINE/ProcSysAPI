/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingDefinitionCreateRequest
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
 * DTO for creating a new SettingDefinition (the aggregate root).
 */
@Schema(name = "SettingDefinitionCreateRequest", description = "Request payload for creating a new setting definition (aggregate root)")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingDefinitionCreateRequest {

    /** Unique code/key. */
    @Schema(description = "Unique setting key (e.g., 'auth.session.timeout')", example = "auth.session.timeout", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 150)
    @NotBlank(message = "Code is mandatory")
    @Size(max = 150, message = "Code must not exceed 150 characters")
    private String code;

    /** Display name. */
    @Schema(description = "Human-readable display name", example = "Session Timeout (Minutes)", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    /** Description. */
    @Schema(description = "Purpose and usage of this setting", example = "Defines the inactivity timeout in minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 1000)
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    /** Default value. */
    @Schema(description = "Default value applied if no scoped value is found", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String defaultValue;

    /** Whether values must be encrypted. */
    @Schema(description = "Whether the setting value must be encrypted at rest", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "IsEncrypted flag is mandatory")
    private Boolean isEncrypted;

    /** Whether changes require approval. */
    @Schema(description = "Whether modifying this setting requires an approval workflow", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "RequiresApproval flag is mandatory")
    private Boolean requiresApproval;

    /** Whether the setting is mutable after creation. */
    @Schema(description = "Whether this setting can be modified after creation", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "IsMutable flag is mandatory")
    private Boolean isMutable;

    /** Category ID. */
    @Schema(description = "ID of the category this setting belongs to", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Category ID is mandatory")
    @Positive(message = "Category ID must be positive")
    private Long categoryId;

    /** Value type ID. */
    @Schema(description = "ID of the data type of this setting", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Value Type ID is mandatory")
    @Positive(message = "Value Type ID must be positive")
    private Long valueTypeId;
}
