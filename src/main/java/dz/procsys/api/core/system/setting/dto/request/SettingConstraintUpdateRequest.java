/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingConstraintUpdateRequest
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for updating an existing SettingConstraint.
 * The definition and constraint type association cannot be changed; only the value and message.
 */
@Schema(name = "SettingConstraintUpdateRequest", description = "Request payload for updating the value/message of an existing setting constraint")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingConstraintUpdateRequest {

    /** Updated constraint value. */
    @Schema(description = "Updated constraint parameter value", example = "^[A-Za-z0-9]+$", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1000)
    @NotBlank(message = "Constraint value is mandatory")
    @Size(max = 1000, message = "Constraint value must not exceed 1000 characters")
    private String constraintValue;

    /** Updated error message. */
    @Schema(description = "Updated custom validation error message", example = "Value must be alphanumeric", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Error message must not exceed 255 characters")
    private String errorMessage;
}
