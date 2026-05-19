/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingCategoryResponse
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
 * Response DTO for SettingCategory.
 * Parent category is returned as a flat reference (ID + code) to avoid circular nesting.
 */
@Schema(name = "SettingCategoryResponse", description = "Response payload representing a setting category")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingCategoryResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Business code. */
    @Schema(description = "Unique business code", example = "SECURITY")
    private String code;

    /** Display name. */
    @Schema(description = "Display name", example = "Security Settings")
    private String name;

    /** Description. */
    @Schema(description = "Description", example = "Authentication and authorization parameters")
    private String description;

    /** Active flag. */
    @Schema(description = "Whether this category is active", example = "true")
    private Boolean isActive;

    /** Parent category ID (null for root). */
    @Schema(description = "Parent category internal ID (null for root categories)", example = "1")
    private Long parentCategoryId;

    /** Parent category code for display. */
    @Schema(description = "Parent category business code", example = "ROOT")
    private String parentCategoryCode;

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
