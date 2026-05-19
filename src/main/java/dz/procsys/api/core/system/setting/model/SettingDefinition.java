/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingDefinition
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.model;

import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
 * SettingDefinition Entity - Extends GenericModel.
 * This is the Aggregate Root of the settings domain.
 * Represents the declaration/definition of a configuration parameter, its type, its default value, and its metadata.
 */
@Schema(name = "SettingDefinition", description = "Aggregate root defining a configuration parameter, its default value, and behaviors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingDefinition")
@Table(name = "T_00_00_05", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_05_UK_01", columnNames = { "F_01" })})
public class SettingDefinition extends GenericModel {

    @Schema(
        description = "Unique code/key of the setting",
        example = "auth.session.timeout",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 150
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 150, message = "Code must not exceed 150 characters")
    @Column(name = "F_01", length = 150, nullable = false)
    private String code;

    @Schema(
        description = "Display name of the setting",
        example = "Session Timeout (Minutes)",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String name;

    @Schema(
        description = "Description explaining the setting purpose",
        example = "Global timeout for inactive user sessions",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 1000
    )
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "F_03", length = 1000)
    private String description;
    
    @Schema(
        description = "Default value string representation",
        example = "30",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04", columnDefinition = "TEXT")
    private String defaultValue;
    
    @Schema(
        description = "Flag indicating if this setting value must be encrypted",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "IsEncrypted flag is mandatory")
    @Column(name = "F_05", nullable = false)
    private Boolean isEncrypted;
    
    @Schema(
        description = "Flag indicating if this setting is globally active",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "IsActive flag is mandatory")
    @Column(name = "F_06", nullable = false)
    private Boolean isActive;
    
    @Schema(
        description = "Flag indicating if this setting can be modified after creation (false = immutable)",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "IsMutable flag is mandatory")
    @Column(name = "F_07", nullable = false)
    private Boolean isMutable;

    @Schema(description = "Category this setting belongs to", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Category is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_05_FK_01"),
        nullable = false
    )
    private SettingCategory category;

    @Schema(description = "Data type of this setting", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Value Type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_05_FK_02"),
        nullable = false
    )
    private SettingValueType valueType;

    @Schema(description = "Validation constraints for this setting", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "settingDefinition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SettingConstraint> constraints;

    @Schema(description = "Specific values defined for scopes", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "settingDefinition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SettingValue> values;
}
