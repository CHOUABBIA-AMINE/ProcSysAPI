/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: SettingCategory
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
 * SettingCategory Entity - Extends GenericModel.
 * Represents a logical grouping or hierarchical category of settings.
 */
@Schema(name = "SettingCategory", description = "Logical grouping or hierarchical category of settings")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SettingCategory")
@Table(name = "T_00_00_04",
    uniqueConstraints = {@UniqueConstraint(name = "UK_T_00_00_04_01", columnNames = { "F_01" })},
    indexes = {
        @Index(name = "IDX_T_00_00_04_CODE", columnList = "F_01"),
        @Index(name = "IDX_T_00_00_04_PARENT", columnList = "F_05")
    })
public class SettingCategory extends GenericModel {

    @Schema(
        description = "Unique code of the category",
        example = "SECURITY_PARAMS",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String code;

    @Schema(
        description = "Display name of the category",
        example = "Security Parameters",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    @Column(name = "F_02", length = 200, nullable = false)
    private String name;

    @Schema(
        description = "Description of what the category contains",
        example = "Settings related to authentication and authorization",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String description;

    @Schema(
        description = "Indicates if this category is active and visible",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Active flag is mandatory")
    @Column(name = "F_04", nullable = false)
    private Boolean isActive;
    
    @Schema(description = "Parent category for hierarchical grouping", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK_T_00_00_04_01"),
        nullable = true
    )
    private SettingCategory parentCategory;

    @Schema(description = "Sub-categories", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SettingCategory> subCategories;

    @Schema(description = "Settings within this category", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SettingDefinition> settings;
}
