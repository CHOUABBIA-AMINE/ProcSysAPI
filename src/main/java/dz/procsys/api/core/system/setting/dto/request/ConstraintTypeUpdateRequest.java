/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ConstraintTypeUpdateRequest
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Request
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for updating an existing ConstraintType.
 */
@Schema(name = "ConstraintTypeUpdateRequest", description = "Request payload for updating an existing constraint type")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstraintTypeUpdateRequest {

    /** Updated description. */
    @Schema(description = "Updated description", example = "Validates min/max numeric range", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /** Updated validator bean. */
    @Schema(description = "Updated Spring bean name of the validator", example = "rangeConstraintValidator", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 100)
    @Size(max = 100, message = "Validator bean name must not exceed 100 characters")
    private String validatorBean;
}
