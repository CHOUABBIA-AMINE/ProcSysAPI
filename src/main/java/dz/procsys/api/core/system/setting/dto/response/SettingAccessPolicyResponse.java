/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicyResponse
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
 * Response DTO for SettingAccessPolicy.
 */
@Schema(name = "SettingAccessPolicyResponse", description = "Response payload representing a setting access policy")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingAccessPolicyResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Setting definition ID. */
    @Schema(description = "Setting definition internal ID", example = "1")
    private Long settingDefinitionId;

    /** Setting definition code. */
    @Schema(description = "Setting definition key", example = "auth.session.timeout")
    private String settingDefinitionCode;

    /** Role code. */
    @Schema(description = "Role or privilege code", example = "SYS_ADMIN")
    private String roleCode;

    /** Can read. */
    @Schema(description = "Whether the role can read this setting", example = "true")
    private Boolean canRead;

    /** Can write. */
    @Schema(description = "Whether the role can write this setting", example = "true")
    private Boolean canWrite;

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
