/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingChangeTypeResponse
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
 * Response DTO for SettingChangeType.
 */
@Schema(name = "SettingChangeTypeResponse", description = "Response payload representing a setting change type")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeTypeResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Business code. */
    @Schema(description = "Unique business code", example = "UPDATE")
    private String code;

    /** Description. */
    @Schema(description = "Description of the change type", example = "Modifies an existing setting value")
    private String description;

    /** Whether approval is required. */
    @Schema(description = "Whether this change type requires managerial approval", example = "true")
    private Boolean requiresApproval;

    /** Optimistic lock version. */
    @Schema(description = "Optimistic lock version", example = "0")
    private Long version;

    /** Record creation timestamp. */
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
