/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingConstraint
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
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * SettingConstraint Entity - Extends GenericModel.
 * Represents a specific constraint applied to a SettingDefinition using a ConstraintType.
 */
@Schema(name = "SettingConstraint", description = "A validation constraint applied to a setting definition")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingConstraint")
@Table(name = "T_00_00_06", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_06_UK_01", columnNames = { "F_03", "F_04" })})
public class SettingConstraint extends GenericModel {

    @Schema(
        description = "The configuration value for this constraint (e.g., '[a-z]+' for REGEX, '10' for MIN)",
        example = "10",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 1000
    )
    @NotBlank(message = "Constraint value is mandatory")
    @Size(max = 1000, message = "Constraint value must not exceed 1000 characters")
    @Column(name = "F_01", length = 1000, nullable = false)
    private String constraintValue;
    
    @Schema(
        description = "Error message to display if validation fails",
        example = "Value must be at least 10",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Error message must not exceed 255 characters")
    @Column(name = "F_02", length = 255)
    private String errorMessage;

    @Schema(
        description = "The setting definition this constraint applies to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_06_FK_01"),
        nullable = false
    )
    private SettingDefinition settingDefinition;
    
    @Schema(
        description = "The type of constraint (e.g., REGEX, MIN, MAX)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Constraint type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_06_FK_02"),
        nullable = false
    )
    private ConstraintType constraintType;
}
