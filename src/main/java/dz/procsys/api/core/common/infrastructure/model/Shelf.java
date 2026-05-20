/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Shelf
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
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Shelf Entity - Extends GenericModel
 * Represents shelves in rooms with multilingual designations
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Shelf")
@Table(name="T_01_01_04", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_04_UK_01", columnNames = { "F_01" })})
public class Shelf extends GenericModel {
	
	@Schema(
        description = "",
        example = "A99",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "")
    @Size(max = 20, message = "Must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
	private String code;
	
	@Schema(
        description = "",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_02",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_04_FK_01"),
        nullable = false
    )
	private Room room;
}
