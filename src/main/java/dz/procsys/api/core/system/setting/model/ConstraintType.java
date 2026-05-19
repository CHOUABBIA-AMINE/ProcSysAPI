/**
 *	
 *	@Author		: System
 *
 *	@Name		: ConstraintType
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
 * ConstraintType Entity - Extends GenericModel.
 * Represents types of constraints applicable to setting values (e.g., MIN_VALUE, MAX_LENGTH, REGEX).
 */
@Schema(name = "ConstraintType", description = "Reference entity describing the type of constraint (e.g., REGEX, RANGE, IN_LIST)")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ConstraintType")
@Table(name = "T_00_00_01", uniqueConstraints = {@UniqueConstraint(name = "T_00_00_01_UK_01", columnNames = { "F_01" })})
public class ConstraintType extends GenericModel {

    @Schema(
        description = "Unique code of the constraint type",
        example = "REGEX",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;

    @Schema(
        description = "Description of the constraint validation logic",
        example = "Validates the setting value against a regular expression pattern",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 255
    )
    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(name = "F_02", length = 255)
    private String description;
    
    @Schema(
        description = "Defines the validator engine class or bean name used to evaluate this constraint",
        example = "regexValidator",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Validator bean name must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String validatorBean;
    
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

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
