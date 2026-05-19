/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValueUpdateRequest
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Request
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.request;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for updating an existing SettingValue.
 * Scope association cannot be changed; only the value and effective dates.
 */
@Schema(name = "SettingValueUpdateRequest", description = "Request payload for updating a scoped setting value")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueUpdateRequest {

    /** The new value. */
    @Schema(description = "Updated setting value", example = "60", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Value is mandatory")
    private String value;

    /** Updated effective from. */
    @Schema(description = "Updated effective-from date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date effectiveFrom;

    /** Updated effective to. */
    @Schema(description = "Updated effective-to date; null means indefinite", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date effectiveTo;
}
