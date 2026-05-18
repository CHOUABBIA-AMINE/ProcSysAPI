/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Job
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.procsys.api.core.common.administration.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
 * Job Entity - Extends GenericModel.
 * Represents job positions with code and multilingual designations.
 */
@Schema(
    name = "Job",
    description = "Reference entity describing job positions with code, multilingual designations and owning structure"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Job")
@Table(
    name = "T_01_04_08",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_04_08_UK_01", columnNames = { "F_01" })
    }
)
public class Job extends GenericModel {
	
    @Schema(
        description = "Job code (short identifier for the position)",
        example = "SYSADM",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 10
    )
    @NotBlank(message = "Job code is mandatory")
    @Size(max = 10, message = "Job code must not exceed 10 characters")
    @Column(name = "F_01", length = 10, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the job",
        example = "مسؤول نظم المعلومات",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the job",
        example = "System Administrator",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the job (primary label)",
        example = "Administrateur Systèmes",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Structure that owns or uses this job position",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Structure is mandatory for job")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_08_FK_01"),
        nullable = false
    )
    private Structure structure;
}