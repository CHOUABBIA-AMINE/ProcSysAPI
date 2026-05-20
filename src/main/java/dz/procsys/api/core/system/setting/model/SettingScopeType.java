/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingScopeType
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
import jakarta.persistence.Index;
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
 * SettingScopeType Entity - Extends GenericModel.
 * Represents the hierarchical scope levels for settings (e.g., GLOBAL, ORGANIZATION, DEPARTMENT, USER).
 * Replacing standard enums to allow dynamic and data-driven hierarchy configurations.
 */
@Schema(name = "SettingScopeType", description = "Reference entity describing the scope level of a setting (e.g., GLOBAL, ORGANIZATION, DEPARTMENT, USER)")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingScopeType")
@Table(name = "T_00_00_03",
    uniqueConstraints = {
            @UniqueConstraint(name = "UK_T_00_00_03_01", columnNames = { "F_01" }),
            @UniqueConstraint(name = "UK_T_00_00_03_02", columnNames = { "F_02" })},
    indexes = {@Index(name = "IDX_T_00_00_03_CODE", columnList = "F_01")})
public class SettingScopeType extends GenericModel {

    @Schema(
        description = "Unique code of the scope type (e.g., GLOBAL, USER)",
        example = "GLOBAL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;

    @Schema(
        description = "Priority or hierarchical level (lower number = higher precedence during resolution)",
        example = "10",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Priority is mandatory")
    @Column(name = "F_02", nullable = false)
    private Integer priority;

    @Schema(
        description = "Description of the scope type",
        example = "System-wide global scope",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(name = "F_03", length = 255)
    private String description;
}
