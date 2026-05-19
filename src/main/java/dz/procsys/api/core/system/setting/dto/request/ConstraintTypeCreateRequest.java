/**
 *	
 *	@Author		: System
 *
 *	@Name		: ConstraintTypeCreateRequest
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
 * DTO for creating a new ConstraintType.
 */
@Schema(name = "ConstraintTypeCreateRequest", description = "Request payload for creating a new constraint type")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstraintTypeCreateRequest {

    /** Unique code. */
    @Schema(description = "Unique code of the constraint type", example = "REGEX", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    /** Description. */
    @Schema(description = "Description of the validation logic", example = "Validates value against a regular expression", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Validator Spring bean name. */
    @Schema(description = "Spring bean name of the validator used to evaluate this constraint", example = "regexConstraintValidator", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 100)
    @Size(max = 100, message = "Validator bean name must not exceed 100 characters")
    private String validatorBean;
}
