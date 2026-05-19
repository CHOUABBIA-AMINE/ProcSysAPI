/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueResponse
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Response
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.response;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO for SettingValue.
 * Encrypted values are masked in the response layer.
 */
@Schema(name = "SettingValueResponse", description = "Response payload representing a scoped setting value")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Setting definition ID. */
    @Schema(description = "Setting definition internal ID", example = "1")
    private Long settingDefinitionId;

    /** Setting definition code. */
    @Schema(description = "Setting definition key", example = "auth.session.timeout")
    private String settingDefinitionCode;

    /** Scope type ID. */
    @Schema(description = "Scope type internal ID", example = "1")
    private Long scopeTypeId;

    /** Scope type code. */
    @Schema(description = "Scope type code", example = "GLOBAL")
    private String scopeTypeCode;

    /** Scope reference ID. */
    @Schema(description = "Scope reference entity ID (null for GLOBAL)", example = "null")
    private Long scopeReferenceId;

    /** The value (masked if encrypted). */
    @Schema(description = "The setting value; masked with '***' if definition requires encryption", example = "45")
    private String value;

    /** Whether value is masked. */
    @Schema(description = "Whether the value has been masked due to encryption", example = "false")
    private Boolean masked;

    /** Effective from date. */
    @Schema(description = "Effective-from date")
    private Date effectiveFrom;

    /** Effective to date. */
    @Schema(description = "Effective-to date; null means indefinite")
    private Date effectiveTo;

    /** Optimistic lock version. */
    @Schema(description = "Optimistic lock version", example = "0")
    private Long version;

    /** Creation timestamp. */
    @Schema(description = "Creation timestamp")
    private Date createdAt;

    /** Creator. */
    @Schema(description = "Creator username", example = "admin")
    private String createdBy;

    /** Last update timestamp. */
    @Schema(description = "Last update timestamp")
    private Date updatedAt;

    /** Last updater. */
    @Schema(description = "Last updater username", example = "admin")
    private String updatedBy;
}
