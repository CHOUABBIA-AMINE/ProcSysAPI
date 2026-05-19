/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistoryResponse
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
 * Response DTO for SettingHistory.
 * History is read-only; no create/update request DTOs exist.
 */
@Schema(name = "SettingHistoryResponse", description = "Response payload representing an immutable audit history entry for a setting change")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingHistoryResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Setting definition ID. */
    @Schema(description = "Setting definition internal ID", example = "1")
    private Long settingDefinitionId;

    /** Setting definition code. */
    @Schema(description = "Setting definition key", example = "auth.session.timeout")
    private String settingDefinitionCode;

    /** Change type ID. */
    @Schema(description = "Change type internal ID", example = "2")
    private Long changeTypeId;

    /** Change type code. */
    @Schema(description = "Change type code", example = "UPDATE")
    private String changeTypeCode;

    /** Scope reference. */
    @Schema(description = "Scope reference string (e.g., 'GLOBAL', user ID)", example = "GLOBAL")
    private String scopeReference;

    /** Previous value before the change. */
    @Schema(description = "Value before the change (masked if encrypted)", example = "30")
    private String oldValue;

    /** New value after the change. */
    @Schema(description = "Value after the change (masked if encrypted)", example = "45")
    private String newValue;

    /** Reason for the change. */
    @Schema(description = "Reason or justification for the change", example = "Business requirement #1234")
    private String reason;

    /** Timestamp when the change was made. */
    @Schema(description = "Timestamp when the change was applied")
    private Date createdAt;

    /** User who made the change. */
    @Schema(description = "Username who made the change", example = "admin")
    private String createdBy;
}
