/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentative
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.procsys.api.core.business.provider.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProviderRepresentative")
@Table(name = "T_02_03_06")
@Schema(
    name = "ProviderRepresentative",
    description = "Person authorized to represent the provider in procurement procedures"
)
public class ProviderRepresentative extends GenericModel {
	
    @Schema(
        description = "Full name of the representative",
        example = "Ahmed Ali",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Representative name is mandatory")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String name;
	
    @Schema(
        description = "Function or role of the representative within the provider organization",
        example = "General Manager",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Function must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String function;
	
    @Schema(
        description = "Contact phone number of the representative",
        example = "+213 770 00 00 00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Phone number must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String phoneNumber;
	
    @Schema(
        description = "Email address of the representative",
        example = "a.ali@provider.dz",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Email(message = "Email must be a valid email address")
    @Size(max = 200, message = "Email must not exceed 200 characters")
    @Column(name = "F_04", length = 200)
    private String email;
	
    @Schema(
        description = "Provider represented by this person",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Provider is mandatory for representative")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_06_FK_01"),
        nullable = false
    )
    private Provider provider;
}