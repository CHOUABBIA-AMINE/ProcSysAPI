/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingCategoryCreateRequest
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
 * DTO for creating a new SettingCategory.
 */
@Schema(name = "SettingCategoryCreateRequest", description = "Request payload for creating a new setting category")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingCategoryCreateRequest {

    /** Unique code of the category. */
    @Schema(description = "Unique business code", example = "SECURITY", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    private String code;

    /** Display name. */
    @Schema(description = "Display name", example = "Security Settings", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    /** Description. */
    @Schema(description = "Description of the category", example = "Authentication and authorization parameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 500)
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    /** Parent category ID (null for root). */
    @Schema(description = "ID of the parent category; null for root categories", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long parentCategoryId;
}
