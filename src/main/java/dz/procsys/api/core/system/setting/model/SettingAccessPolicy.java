/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingAccessPolicy
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
import jakarta.persistence.ForeignKey;
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
 * SettingAccessPolicy Entity - Extends GenericModel.
 * Governs which roles or privileges can read or modify a specific setting definition.
 */
@Schema(name = "SettingAccessPolicy", description = "Access policy defining read/write permissions for a setting")
@Setter
@Getter
@ToString(exclude = {"settingDefinition"})
@EqualsAndHashCode(callSuper = true, exclude = {"settingDefinition"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingAccessPolicy")
@Table(name = "T_00_00_11", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_11_UK_01", columnNames = { "F_08", "F_01" })})
public class SettingAccessPolicy extends GenericModel {

    @Schema(
        description = "The role or privilege code granted access (e.g., ADMIN, SYS_ADMIN)",
        example = "ADMIN",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Role code is mandatory")
    @Size(max = 100, message = "Role code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String roleCode;
    
    @Schema(
        description = "True if the role can read this setting",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "CanRead flag is mandatory")
    @Column(name = "F_02", nullable = false)
    private Boolean canRead;
    
    @Schema(
        description = "True if the role can write/modify this setting",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "CanWrite flag is mandatory")
    @Column(name = "F_03", nullable = false)
    private Boolean canWrite;
    
    @Schema(description = "Record creation timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Column(name = "F_04", nullable = false, updatable = false)
    private Date createdAt;

    @Schema(description = "User who created the record", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_05", length = 100, updatable = false)
    private String createdBy;

    @Schema(description = "Record last update timestamp")
    @Column(name = "F_06")
    private Date updatedAt;

    @Schema(description = "User who last updated the record", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_07", length = 100)
    private String updatedBy;

    @Schema(
        description = "The setting definition protected by this policy",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_11_FK_01"),
        nullable = false
    )
    private SettingDefinition settingDefinition;
    
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        if (this.canRead == null) this.canRead = true;
        if (this.canWrite == null) this.canWrite = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
