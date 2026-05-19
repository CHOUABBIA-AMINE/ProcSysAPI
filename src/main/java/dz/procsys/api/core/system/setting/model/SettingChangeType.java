/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeType
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SettingChangeType Entity - Extends GenericModel.
 * Represents the types of changes that can be requested (e.g., CREATE, UPDATE, DELETE).
 * Replacing standard enums to allow workflow-driven changes.
 */
@Schema(
    name = "SettingChangeType",
    description = "Reference entity describing the nature of a setting change request"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingChangeType")
@Table(name = "T_00_00_02", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_02_UK_01", columnNames = { "F_01" })})
public class SettingChangeType extends GenericModel {

    @Schema(
        description = "Unique code of the change type",
        example = "UPDATE",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;

    @Schema(
        description = "Description of the change type",
        example = "Modifies an existing setting value",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(name = "F_02", length = 255)
    private String description;
    
    @Schema(
        description = "Indicates if this change requires managerial approval",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Requires approval flag is mandatory")
    @Column(name = "F_03", nullable = false)
    private Boolean requiresApproval;
}
