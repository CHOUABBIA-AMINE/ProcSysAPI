/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingHistory
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SettingHistory Entity - Extends GenericModel.
 * Represents an immutable audit trail of changes applied to settings.
 */
@Schema(name = "SettingHistory", description = "Audit trail record capturing previous values of a setting")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingHistory")
@Table(name = "T_00_00_09")
public class SettingHistory extends GenericModel {
    
    @Schema(
        description = "Scope reference (Global, Org ID, User ID) where change occurred",
        example = "GLOBAL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Scope reference is mandatory")
    @Size(max = 100, message = "Scope reference must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String scopeReference;

    @Schema(
        description = "The value before the change (null if created)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_02", columnDefinition = "TEXT")
    private String oldValue;
    
    @Schema(
        description = "The new value after the change (null if deleted)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03", columnDefinition = "TEXT")
    private String newValue;

    @Schema(
        description = "Reason or justification for the change",
        example = "Security compliance requirement",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Column(name = "F_04", length = 500)
    private String reason;
    
    @Schema(description = "Timestamp when the change was made", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Column(name = "F_05", nullable = false, updatable = false)
    private Date createdAt;

    @Schema(description = "User who made the change", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_06", length = 100, updatable = false)
    private String createdBy;

    @Schema(
        description = "The setting definition that was changed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_09_FK_01"),
        nullable = false
    )
    private SettingDefinition settingDefinition;
    
    @Schema(
        description = "The nature of the change (CREATE, UPDATE, DELETE)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Change type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_00_09_FK_01"),
        nullable = false
    )
    private SettingChangeType changeType;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}
