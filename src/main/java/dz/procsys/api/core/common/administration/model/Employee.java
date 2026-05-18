/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Employee
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 01-01-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.procsys.api.core.common.administration.model;

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

/**
 * Employee Entity - Represents employees in the system.
 *
 * Extends Person and adds employee-specific information such as job,
 * organizational structure, and military rank.
 */
@Schema(
    name = "Employee",
    description = "Represents an employee of the organization, extending Person with job, structure and military rank"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Employee")
@Table(name = "T_01_04_10")
public class Employee extends Person {
	
    @Schema(
        description = "Employee registration number, unique within the organization",
        example = "EMP-2024-001",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Registration number must not exceed 50 characters")
    @Column(name = "F_11", length = 50)
    private String registrationNumber;
	
    @Schema(
        description = "Job/position of the employee",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Job is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_12",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_10_FK_01"),
        nullable = false
    )
    private Job job;
	
    @Schema(
        description = "Organizational structure or department where the employee works",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Structure is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_13",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_10_FK_02"),
        nullable = false
    )
    private Structure structure;
	
    @Schema(
        description = "Military rank of the employee when applicable",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Military rank is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_14",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_04_10_FK_03"),
        nullable = false
    )
    private MilitaryRank militaryRank;
}