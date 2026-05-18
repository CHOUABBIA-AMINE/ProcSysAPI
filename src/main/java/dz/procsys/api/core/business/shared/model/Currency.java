/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Currency
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Shared
 *
 **/

package dz.procsys.api.core.business.shared.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Currency Entity - Extends GenericModel.
 *
 * Database table: T_02_01_01
 * Primary key: F_00 (id) - inherited from GenericModel.
 */
@Schema(
    name = "Currency",
    description = "Reference entity describing currencies with code, multilingual designations and acronyms"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Currency")
@Table(
    name = "T_02_01_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_02_01_01_UK_01", columnNames = { "F_01" }),
        @UniqueConstraint(name = "T_02_01_01_UK_02", columnNames = { "F_02" }),
        @UniqueConstraint(name = "T_02_01_01_UK_03", columnNames = { "F_03" }),
        @UniqueConstraint(name = "T_02_01_01_UK_04", columnNames = { "F_04" }),
        @UniqueConstraint(name = "T_02_01_01_UK_05", columnNames = { "F_05" }),
        @UniqueConstraint(name = "T_02_01_01_UK_06", columnNames = { "F_06" }),
        @UniqueConstraint(name = "T_02_01_01_UK_07", columnNames = { "F_07" })
    }
)
public class Currency extends GenericModel {
	
    @Schema(
        description = "Currency code (e.g., DZD, USD, EUR)",
        example = "DZD",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Currency code is mandatory")
    @Size(max = 20, message = "Currency code must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
    private String code;
	
    @Schema(
        description = "Currency designation in Arabic",
        example = "الدينار الجزائري",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Arabic designation is mandatory")
    @Size(max = 50, message = "Arabic designation must not exceed 50 characters")
    @Column(name = "F_02", length = 50, nullable = false)
    private String designationAr;

    @Schema(
        description = "Currency designation in English",
        example = "Algerian Dinar",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "English designation is mandatory")
    @Size(max = 50, message = "English designation must not exceed 50 characters")
    @Column(name = "F_03", length = 50, nullable = false)
    private String designationEn;
	
    @Schema(
        description = "Currency designation in French",
        example = "Dinar algérien",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 50, message = "French designation must not exceed 50 characters")
    @Column(name = "F_04", length = 50, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Arabic acronym of the currency",
        example = "دج",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Arabic acronym is mandatory")
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    @Column(name = "F_05", length = 20, nullable = false)
    private String acronymAr;
	
    @Schema(
        description = "English acronym of the currency",
        example = "DA",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "English acronym is mandatory")
    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    @Column(name = "F_06", length = 20, nullable = false)
    private String acronymEn;
	
    @Schema(
        description = "French acronym of the currency",
        example = "DA",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "French acronym is mandatory")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    @Column(name = "F_07", length = 20, nullable = false)
    private String acronymFr;
}