/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Provider
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.procsys.api.core.business.provider.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dz.procsys.api.core.business.consultation.model.Submission;
import dz.procsys.api.core.common.administration.model.Country;
import dz.procsys.api.core.common.administration.model.State;
import dz.procsys.api.core.system.utility.model.File;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Provider Entity - Extends GenericModel.
 * Represents vendors/partners participating in procurement processes.
 */
@Schema(
    name = "Provider",
    description = "Represents a procurement provider (supplier/vendor) with identity, contact, banking and classification data"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Provider")
@Table(name = "T_02_03_04")
public class Provider extends GenericModel {
	
    @Schema(
        description = "Provider legal designation in Latin script",
        example = "SONATRACH SPA",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Latin designation is mandatory")
    @Size(max = 200, message = "Latin designation must not exceed 200 characters")
    @Column(name = "F_01", length = 200)
    private String designationLt;
	
    @Schema(
        description = "Provider legal designation in Arabic",
        example = "سوناطراك",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String designationAr;
	
    @Schema(
        description = "Latin acronym or short name of the provider",
        example = "SH",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Latin acronym must not exceed 20 characters")
    @Column(name = "F_03", length = 20)
    private String acronymLt;
	
    @Schema(
        description = "Arabic acronym of the provider",
        example = "س.ت",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 20
    )
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    @Column(name = "F_04", length = 20)
    private String acronymAr;
	
    @Schema(
        description = "Provider main address",
        example = "01 Rue des Pétroles, Hydra, Alger",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Address must not exceed 200 characters")
    @Column(name = "F_05", length = 200)
    private String address;
	
    @Schema(
        description = "Provider social capital amount",
        example = "100000000.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_06", precision = 18, scale = 2)
    private BigDecimal capital;
	
    @Schema(
        description = "Commercial registry number",
        example = "RC-16/00-1234567B16",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Commercial registry number must not exceed 200 characters")
    @Column(name = "F_07", length = 200)
    private String comercialRegistryNumber;
	
    @Schema(
        description = "Commercial registry registration date",
        example = "2010-01-15",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_08")
    private Date comercialRegistryDate;
	
    @Schema(
        description = "Tax identification number",
        example = "NIF-123456789012345",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Tax identity number must not exceed 200 characters")
    @Column(name = "F_09", length = 200)
    private String taxeIdentityNumber;
	
    @Schema(
        description = "Statistical identification number",
        example = "STAT-123456789",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Stat identity number must not exceed 200 characters")
    @Column(name = "F_10", length = 200)
    private String statIdentityNumber;
	
    @Schema(
        description = "Bank name where the provider account is held",
        example = "BEA Hydra",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Bank name must not exceed 200 characters")
    @Column(name = "F_11", length = 200)
    private String bank;
	
    @Schema(
        description = "Provider bank account number/IBAN",
        example = "001000123456789000-45",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Bank account must not exceed 50 characters")
    @Column(name = "F_12", length = 50)
    private String bankAccount;
	
    @Schema(
        description = "SWIFT/BIC code for international transfers",
        example = "BEA DZ ALX XXX",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "SWIFT number must not exceed 50 characters")
    @Column(name = "F_13", length = 50)
    private String swiftNumber;
	
    @Schema(
        description = "Contact phone numbers (comma-separated if multiple)",
        example = "+213 21 00 00 00, +213 770 00 00 00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Phone numbers must not exceed 200 characters")
    @Column(name = "F_14", length = 200)
    private String phoneNumbers;
	
    @Schema(
        description = "Contact fax numbers (comma-separated if multiple)",
        example = "+213 21 00 00 01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Fax numbers must not exceed 200 characters")
    @Column(name = "F_15", length = 200)
    private String faxNumbers;
	
    @Schema(
        description = "Main email address of the provider",
        example = "contact@provider.dz",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Email(message = "Mail must be a valid email address")
    @Size(max = 300, message = "Mail must not exceed 300 characters")
    @Column(name = "F_16", length = 300)
    private String mail;
	
    @Schema(
        description = "Provider website URL",
        example = "https://www.provider.dz",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Website must not exceed 200 characters")
    @Column(name = "F_17", length = 200)
    private String website;
	
    @Schema(
        description = "Logo file associated with the provider",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_18",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_04_FK_01"),
        nullable = true
    )
    private File logo;
	
    @Schema(
        description = "Economic nature of the provider (e.g. manufacturer, distributor)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Economic nature is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_19",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_04_FK_02"),
        nullable = false
    )
    private EconomicNature economicNature;
	
    @Schema(
        description = "Country where the provider is registered",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Country is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_20",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_04_FK_03"),
        nullable = false
    )
    private Country country;
	
    @Schema(
        description = "State/region where the provider is located (if applicable)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_21",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_04_FK_04"),
        nullable = true
    )
    private State state;
	
    @Schema(
        description = "Exclusions applied to this provider (suspensions, bans, etc.)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "provider")
    private List<ProviderExclusion> providerExclusions;
	
    @Schema(
        description = "Representatives authorized to act on behalf of the provider",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "provider")
    private List<ProviderRepresentative> providerRepresentatives;
	
    @Schema(
        description = "Clearance certificates associated with this provider",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "provider")
    private List<Clearance> clearances;
	
    @Schema(
        description = "Economic domains in which this provider operates",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "R_T020304_T020301",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020304_T020301_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T020304_T020301_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T020304_T020301_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<EconomicDomain> economicDomains;
	
    @Schema(
        description = "Submissions made by this provider in consultations/tenders",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @OneToMany(mappedBy = "tender")
    private List<Submission> submissions;
}