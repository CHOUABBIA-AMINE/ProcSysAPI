/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingConstraintResponse
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
 * Response DTO for SettingConstraint.
 */
@Schema(name = "SettingConstraintResponse", description = "Response payload representing a setting constraint")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingConstraintResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Setting definition ID. */
    @Schema(description = "Setting definition internal ID", example = "1")
    private Long settingDefinitionId;

    /** Setting definition code. */
    @Schema(description = "Setting definition unique code", example = "auth.session.timeout")
    private String settingDefinitionCode;

    /** Constraint type ID. */
    @Schema(description = "Constraint type internal ID", example = "2")
    private Long constraintTypeId;

    /** Constraint type code. */
    @Schema(description = "Constraint type business code", example = "REGEX")
    private String constraintTypeCode;

    /** Constraint value. */
    @Schema(description = "The constraint parameter value", example = "^[0-9]+$")
    private String constraintValue;

    /** Custom error message. */
    @Schema(description = "Custom validation error message", example = "Value must be a positive integer")
    private String errorMessage;

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
