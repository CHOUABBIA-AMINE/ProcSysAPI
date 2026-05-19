/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingDefinitionResponse
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
 * Response DTO for SettingDefinition.
 * Related entities are returned as flat references (ID + code) to avoid circular nesting.
 */
@Schema(name = "SettingDefinitionResponse", description = "Response payload representing a setting definition (aggregate root)")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingDefinitionResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Business code/key. */
    @Schema(description = "Unique setting key", example = "auth.session.timeout")
    private String code;

    /** Display name. */
    @Schema(description = "Display name", example = "Session Timeout (Minutes)")
    private String name;

    /** Description. */
    @Schema(description = "Purpose and usage description", example = "Inactivity timeout before session expires")
    private String description;

    /** Default value. */
    @Schema(description = "Default value if no scoped value is found", example = "30")
    private String defaultValue;

    /** Whether encrypted. */
    @Schema(description = "Whether the value must be encrypted at rest", example = "false")
    private Boolean isEncrypted;

    /** Whether approval required. */
    @Schema(description = "Whether changes require an approval workflow", example = "true")
    private Boolean requiresApproval;

    /** Whether active. */
    @Schema(description = "Whether this setting is active", example = "true")
    private Boolean isActive;

    /** Whether mutable. */
    @Schema(description = "Whether this setting can be modified after creation", example = "true")
    private Boolean isMutable;

    /** Category ID. */
    @Schema(description = "Category internal ID", example = "1")
    private Long categoryId;

    /** Category code. */
    @Schema(description = "Category business code", example = "SECURITY")
    private String categoryCode;

    /** Value type ID. */
    @Schema(description = "Value type internal ID", example = "1")
    private Long valueTypeId;

    /** Value type code. */
    @Schema(description = "Value type business code", example = "INTEGER")
    private String valueTypeCode;

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
