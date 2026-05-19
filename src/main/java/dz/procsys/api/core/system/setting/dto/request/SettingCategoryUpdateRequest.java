/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingCategoryUpdateRequest
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
 * DTO for updating an existing SettingCategory. Code is immutable.
 */
@Schema(name = "SettingCategoryUpdateRequest", description = "Request payload for updating an existing setting category")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingCategoryUpdateRequest {

    /** Updated name. */
    @Schema(description = "Updated display name", example = "Security & Auth Settings", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    /** Updated description. */
    @Schema(description = "Updated description", example = "Authentication, authorization and session parameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 500)
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    /** Updated parent category ID. */
    @Schema(description = "ID of the new parent category; null to make it a root category", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long parentCategoryId;
}
