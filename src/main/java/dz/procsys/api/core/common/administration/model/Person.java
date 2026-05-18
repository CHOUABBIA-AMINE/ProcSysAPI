/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Person
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 01-01-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.procsys.api.core.common.administration.model;

import java.util.Date;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * Person Entity - Base class for person-related entities.
 *
 * This entity represents basic information about a person in the system.
 * It serves as the parent class for specialized person types (e.g., Employee).
 */
@Schema(
    name = "Person",
    description = "Base entity representing a person, used as parent for specialized types such as Employee"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_01_04_09")
public class Person extends GenericModel {
	
    /**
     * Last name in Arabic.
     * Mapped to: T_01_04_09.F_01
     */
    @Schema(
        description = "Last name in Arabic",
        example = "أحمد",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Last name in Arabic is mandatory")
    @Size(max = 100, message = "Last name in Arabic must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = false)
    private String lastNameAr;
	
    /**
     * First name in Arabic.
     * Mapped to: T_01_04_09.F_02
     */
    @Schema(
        description = "First name in Arabic",
        example = "علي",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "First name in Arabic is mandatory")
    @Size(max = 100, message = "First name in Arabic must not exceed 100 characters")
    @Column(name = "F_02", length = 100, nullable = false)
    private String firstNameAr;
	
    /**
     * Last name in Latin script (English/French).
     * Mapped to: T_01_04_09.F_03
     */
    @Schema(
        description = "Last name in Latin script (English/French)",
        example = "Ahmed",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Last name in Latin script is mandatory")
    @Size(max = 100, message = "Last name in Latin script must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String lastNameLt;
	
    /**
     * First name in Latin script (English/French).
     * Mapped to: T_01_04_09.F_04
     */
    @Schema(
        description = "First name in Latin script (English/French)",
        example = "Ali",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "First name in Latin script is mandatory")
    @Size(max = 100, message = "First name in Latin script must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String firstNameLt;
	
    /**
     * Date of birth.
     * Mapped to: T_01_04_09.F_05
     */
    @Schema(
        description = "Date of birth",
        example = "1990-05-15",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_05")
    private Date birthDate;
	
    /**
     * Place of birth.
     * Mapped to: T_01_04_09.F_06
     */
    @Schema(
        description = "Place of birth (city or locality)",
        example = "Algiers",
        maxLength = 100
    )
    @Size(max = 100, message = "Birth place must not exceed 100 characters")
    @Column(name = "F_06", length = 100)
    private String birthPlace;
	
    /**
     * Country of origin (Foreign Key).
     * Mapped to: T_01_04_09.F_07
     * References: Country table.
     */
    @Schema(
        description = "Country of origin",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Country is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_09_FK_01"),
        nullable = false
    )
    private Country country;
}