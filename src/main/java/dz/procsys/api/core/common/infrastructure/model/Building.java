/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Building
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Infrastructure
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
 * Building Entity - Extends GenericModel
 * Represents building blocks with multilingual designations
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Building")
@Table(name="T_01_01_01", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_01_UK_01", columnNames = { "F_03" })})
public class Building extends GenericModel {
	
	@Schema(
        description = "Execution status of the action",
        example = "Building A",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Status is mandatory")
    @Size(max = 100, message = "Status must not exceed 100 characters")
    @Column(name = "F_01", length = 100, nullable = true)
	private String designationAr;

	@Schema(
        description = "Execution status of the action",
        example = "Building A",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Status is mandatory")
    @Size(max = 100, message = "Status must not exceed 100 characters")
    @Column(name = "F_02", length = 100, nullable = true)
	private String designationEn;
	
	@Schema(
        description = "Execution status of the action",
        example = "Bloc A",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Status is mandatory")
    @Size(max = 100, message = "Status must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
	private String designationFr;
}
