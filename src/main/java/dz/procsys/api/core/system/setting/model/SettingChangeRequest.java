/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequest
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
 * SettingChangeRequest Entity - Extends GenericModel.
 * Represents a workflow request to change a setting value that requires approval.
 */
@Schema(
    name = "SettingChangeRequest",
    description = "Workflow request representing a pending change to a setting value"
)
@Setter
@Getter
@ToString(exclude = {"settingDefinition", "changeType"})
@EqualsAndHashCode(callSuper = true, exclude = {"settingDefinition", "changeType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingChangeRequest")
@Table(name = "T_SET_CHG_REQ")
public class SettingChangeRequest extends GenericModel {

    @Schema(
        description = "Scope reference (GLOBAL, ORG_ID, USER_ID)",
        example = "GLOBAL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Scope reference is mandatory")
    @Size(max = 100, message = "Scope reference must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String scopeReference;

    @Schema(
        description = "The proposed new value",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Proposed value is mandatory")
    @Column(name = "F_02", columnDefinition = "TEXT", nullable = false)
    private String proposedValue;
    
    @Schema(
        description = "Reason or justification submitted by the requester",
        example = "Increasing timeout due to business request",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String justification;
    
    @Schema(
        description = "The current status of the request (e.g., PENDING, APPROVED, REJECTED)",
        example = "PENDING",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Status is mandatory")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    @Column(name = "F_04", length = 50, nullable = false)
    private String status;
    
    @Schema(description = "Approver's remarks or rejection reason", maxLength = 500)
    @Size(max = 500)
    @Column(name = "F_05", length = 500)
    private String approverRemarks;

    @Schema(description = "Date and time when the request was approved or rejected")
    @Column(name = "F_06")
    private Date decidedAt;

    @Schema(description = "User who approved or rejected the request", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_07", length = 100)
    private String decidedBy;

    @Schema(description = "Record creation timestamp (Request Date)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Column(name = "F_08", nullable = false, updatable = false)
    private Date createdAt;

    @Schema(description = "User who created the record (Requester)", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_09", length = 100, updatable = false)
    private String createdBy;

    @Schema(description = "Record last update timestamp")
    @Column(name = "F_10")
    private Date updatedAt;

    @Schema(description = "User who last updated the record", maxLength = 100)
    @Size(max = 100)
    @Column(name = "F_11", length = 100)
    private String updatedBy;

    @Schema(
        description = "The setting definition requested to be changed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Setting definition is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_12",
        referencedColumnName = "F_00",
        //foreignKey = @ForeignKey(name = "T_02_04_04_FK_01"),
        nullable = false
    )
    private SettingDefinition settingDefinition;
    
    @Schema(
        description = "The type of change requested",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Change type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_13",
        referencedColumnName = "F_00",
        //foreignKey = @ForeignKey(name = "T_02_04_04_FK_01"),
        nullable = false
    )
    private SettingChangeType changeType;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
