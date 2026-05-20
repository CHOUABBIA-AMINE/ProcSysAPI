/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingValue
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.model;

import java.time.LocalDateTime;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SettingValue Entity - Extends GenericModel.
 * Represents an actual configuration value applied to a specific setting and scope.
 */
@Schema(name = "SettingValue", description = "A defined value for a setting under a specific scope (e.g., a user's language setting)")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingValue")
@Table(name = "T_00_00_07",
    uniqueConstraints = {@UniqueConstraint(name = "UK_T_00_00_07_01", columnNames = { "F_05", "F_06", "F_01" })},
    indexes = {
        @Index(name = "IDX_T_00_00_07_SETTING_DEF", columnList = "F_05"),
        @Index(name = "IDX_T_00_00_07_SCOPE_TYPE", columnList = "F_06"),
        @Index(name = "IDX_T_00_00_07_SCOPE_REF", columnList = "F_01")
    })
public class SettingValue extends GenericModel {
    
    @Schema(
        description = "The reference ID of the target scope (e.g., user ID, org ID). Null for GLOBAL scope.",
        example = "105",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_01")
    private Long scopeReferenceId;

    @Schema(
        description = "The configuration value represented as a string (possibly encrypted)",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Value string is mandatory")
    @Column(name = "F_02", columnDefinition = "TEXT", nullable = false)
    private String value;
    
    @Schema(
        description = "Effective start date for this value",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03")
    private LocalDateTime effectiveFrom;
    
    @Schema(
        description = "Effective end date for this value (null means indefinite)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04")
    private LocalDateTime effectiveTo;

    @Schema(
        description = "The setting definition this value belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK_T_00_00_07_01"),
        nullable = false
    )
    private SettingDefinition settingDefinition;
    
    @Schema(
        description = "The scope type (GLOBAL, ORG, USER) determining the hierarchy level",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Scope type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK_T_00_00_07_02"),
        nullable = false
    )
    private SettingScopeType scopeType;
}
