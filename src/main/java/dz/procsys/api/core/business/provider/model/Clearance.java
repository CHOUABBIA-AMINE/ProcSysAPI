/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Clearance
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

import dz.procsys.api.core.system.utility.model.File;
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
@Entity(name = "Clearance")
@Table(name = "T_02_03_07")
@Schema(
    name = "Clearance",
    description = "Represents a clearance certificate (tax, CNAS, CASNOS, etc.) associated with a provider"
)
public class Clearance extends GenericModel {
	
    @Schema(
        description = "Type of clearance (e.g., TAX, CNAS, CASNOS)",
        example = "TAX",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Clearance type must not exceed 50 characters")
    @Column(name = "F_01", length = 50)
    private String type;
	
    @Schema(
        description = "Reference or number of the clearance document",
        example = "CL-2025-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Reference must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String reference;
	
    @Schema(
        description = "Issue date of the clearance",
        example = "2025-01-10",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03")
    private Date issueDate;
	
    @Schema(
        description = "Expiry date of the clearance",
        example = "2025-07-10",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04")
    private Date expiryDate;
	
    @Schema(
        description = "File attached that contains the scanned clearance document",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_07_FK_01"),
        nullable = true
    )
    private File file;
	
    @Schema(
        description = "Provider to which this clearance belongs",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Provider is mandatory for clearance")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_02_03_07_FK_02"),
        nullable = false
    )
    private Provider provider;
}