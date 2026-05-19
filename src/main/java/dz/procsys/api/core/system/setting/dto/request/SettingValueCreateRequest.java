/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueCreateRequest
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for creating a new SettingValue.
 */
@Schema(name = "SettingValueCreateRequest", description = "Request payload for defining a scoped value for a setting")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueCreateRequest {

    /** Setting definition ID. */
    @Schema(description = "ID of the setting definition", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Setting definition ID is mandatory")
    @Positive(message = "Setting definition ID must be positive")
    private Long settingDefinitionId;

    /** Scope type ID. */
    @Schema(description = "ID of the scope type (e.g., GLOBAL, ORG, USER)", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Scope type ID is mandatory")
    @Positive(message = "Scope type ID must be positive")
    private Long scopeTypeId;

    /** Scope reference ID (null for GLOBAL). */
    @Schema(description = "Reference ID of the scope target (e.g., user ID or org ID); null for GLOBAL", example = "105", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long scopeReferenceId;

    /** The value string. */
    @Schema(description = "The setting value (will be encrypted if definition requires it)", example = "45", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Value is mandatory")
    private String value;

    /** Effective from date. */
    @Schema(description = "Date from which this value is effective; defaults to now", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date effectiveFrom;

    /** Effective to date. */
    @Schema(description = "Date until which this value is effective; null means indefinite", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date effectiveTo;
}
