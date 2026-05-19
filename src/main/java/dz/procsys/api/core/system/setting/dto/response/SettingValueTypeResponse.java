/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingValueTypeResponse
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
 * Response DTO for SettingValueType.
 */
@Schema(
    name = "SettingValueTypeResponse",
    description = "Response payload representing a setting value type"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingValueTypeResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Business code. */
    @Schema(description = "Unique business code", example = "STRING")
    private String code;

    /** Description. */
    @Schema(description = "Human-readable description", example = "Textual string data")
    private String description;

    /** Whether encryption is supported. */
    @Schema(description = "Whether values of this type support encryption", example = "false")
    private Boolean supportsEncryption;

    /** Optimistic lock version. */
    @Schema(description = "Optimistic lock version", example = "0")
    private Long version;

    /** Record creation timestamp. */
    @Schema(description = "Creation timestamp")
    private Date createdAt;

    /** User who created the record. */
    @Schema(description = "Creator username", example = "admin")
    private String createdBy;

    /** Last update timestamp. */
    @Schema(description = "Last update timestamp")
    private Date updatedAt;

    /** User who last updated the record. */
    @Schema(description = "Last updater username", example = "admin")
    private String updatedBy;
}
