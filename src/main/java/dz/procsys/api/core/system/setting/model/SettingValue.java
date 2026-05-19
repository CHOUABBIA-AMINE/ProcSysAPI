/**
 *	
 *	@Author		: System
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

import java.util.Date;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SettingValue Entity - Extends GenericModel.
 * Represents an actual configuration value applied to a specific setting and scope.
 */
@Schema(name = "SettingValue", description = "A defined value for a setting under a specific scope (e.g., a user's language setting)")
@Setter
@Getter
@ToString(exclude = {"settingDefinition", "scopeType"})
@EqualsAndHashCode(callSuper = true, exclude = {"settingDefinition", "scopeType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingValue")
@Table(name = "T_00_00_11", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_11_UK_01", columnNames = { "F_DEFINITION_ID", "F_SCOPE_TYPE_ID", "F_SCOPE_REF_ID" })})
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
    private Date effectiveFrom;
    
    @Schema(
        description = "Effective end date for this value (null means indefinite)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04")
    private Date effectiveTo;
    
    @Schema(description = "Record creation timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Column(name = "F_05", nullable = false, updatable = false)
    private Date createdAt;

    @Schema(description = "User who created the record", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_06", length = 100, updatable = false)
    private String createdBy;

    @Schema(description = "Record last update timestamp")
    @Column(name = "F_07")
    private Date updatedAt;

    @Schema(description = "User who last updated the record", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_08", length = 100)
    private String updatedBy;

    @Schema(
        description = "The setting definition this value belongs to",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        //foreignKey = @ForeignKey(name = "T_02_04_04_FK_01"),
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
        name = "F_10",
        referencedColumnName = "F_00",
        //foreignKey = @ForeignKey(name = "T_02_04_04_FK_01"),
        nullable = false
    )
    private SettingScopeType scopeType;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        if (this.effectiveFrom == null) {
            this.effectiveFrom = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
