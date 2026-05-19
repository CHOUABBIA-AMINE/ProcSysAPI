/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingConstraintCreateRequest
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
 * DTO for creating a new SettingConstraint.
 */
@Schema(name = "SettingConstraintCreateRequest", description = "Request payload for attaching a constraint to a setting definition")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingConstraintCreateRequest {

    /** Setting definition ID. */
    @Schema(description = "ID of the setting definition this constraint applies to", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Setting definition ID is mandatory")
    @Positive(message = "Setting definition ID must be positive")
    private Long settingDefinitionId;

    /** Constraint type ID. */
    @Schema(description = "ID of the constraint type (e.g., REGEX, MIN, MAX)", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Constraint type ID is mandatory")
    @Positive(message = "Constraint type ID must be positive")
    private Long constraintTypeId;

    /** The constraint configuration value. */
    @Schema(description = "The constraint parameter value (e.g., regex pattern, min/max number)", example = "^[0-9]+$", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1000)
    @NotBlank(message = "Constraint value is mandatory")
    @Size(max = 1000, message = "Constraint value must not exceed 1000 characters")
    private String constraintValue;

    /** Custom error message. */
    @Schema(description = "Custom validation error message displayed when constraint is violated", example = "Value must be a positive integer", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Error message must not exceed 255 characters")
    private String errorMessage;
}
