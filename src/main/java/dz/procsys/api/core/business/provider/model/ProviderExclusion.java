/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusion
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.procsys.api.core.business.provider.model;

import java.util.Date;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity(name = "ProviderExclusion")
@Table(name = "T_02_03_05")
@Schema(
    name = "ProviderExclusion",
    description = "Represents an exclusion decision applied to a provider for a given period and reason"
)
public class ProviderExclusion extends GenericModel {
	
    @Schema(
        description = "Start date of the exclusion period",
        example = "2025-01-01",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Exclusion start date is mandatory")
    @Column(name = "F_01")
    private Date startDate;
	
    @Schema(
        description = "End date of the exclusion period",
        example = "2025-12-31",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_02")
    private Date endDate;
	
    @Schema(
        description = "Reference or number of the exclusion decision",
        example = "DEC-EXCL-2025-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Decision reference must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String decisionReference;
	
    @Schema(
        description = "Additional comments regarding the exclusion",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Comment must not exceed 500 characters")
    @Column(name = "F_04", length = 500)
    private String comment;
	
    @Schema(
        description = "Provider that is excluded",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Provider is mandatory for exclusion")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_05_FK_01"),
        nullable = false
    )
    private Provider provider;
	
    @Schema(
        description = "Type of exclusion applied",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Exclusion type is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_05_FK_02"),
        nullable = false
    )
    private ExclusionType exclusionType;
}