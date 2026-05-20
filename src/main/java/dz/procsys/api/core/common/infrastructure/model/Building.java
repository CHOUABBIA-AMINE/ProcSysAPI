/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : Building
 *  @CreatedOn  : 06-26-2025
 *  @Updated    : 12-11-2025
 *
 *  @Type       : Class
 *  @Layer      : Model
 *  @Package    : Common / Infrastructure
 *
 **/

package dz.procsys.api.core.common.infrastructure.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Building Entity - Extends GenericModel.
 * Represents a physical building in the organization infrastructure,
 * with multilingual designations (Arabic, English, French).
 */
@Schema(
    name = "Building",
    description = "Represents a physical building in the organization infrastructure with multilingual designations."
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Building")
@Table(name = "T_01_01_01", uniqueConstraints = {@UniqueConstraint( name = "T_01_01_01_UK_01", columnNames = { "F_03" })})
public class Building extends GenericModel {

    /**
     * Building designation in Arabic.
     */
    @Schema(
        description = "Building designation in Arabic",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "المبنى أ",
        maxLength = 100
    )
    @NotBlank(message = "Arabic designation is mandatory")
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String designationAr;

    /**
     * Building designation in English.
     */
    @Schema(
        description = "Building designation in English",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "Building A",
        maxLength = 100
    )
    @NotBlank(message = "English designation is mandatory")
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100, nullable = false)
    private String designationEn;

    /**
     * Building designation in French (used as unique business key). 
     */
    @Schema(
        description = "Building designation in French (unique business key)",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "Bloc A",
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
}