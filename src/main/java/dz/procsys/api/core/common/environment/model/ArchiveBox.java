/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBox
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.procsys.api.core.common.environment.model;

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
 * ArchiveBox Entity - Extends GenericModel.
 * Represents archive boxes with code, description and physical location.
 */
@Schema(
    name = "ArchiveBox",
    description = "Represents a physical archive box stored on a specific shelf and shelf floor"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveBox")
@Table(
    name = "T_01_01_06",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_01_06_UK_01", columnNames = { "F_01" })
    }
)
public class ArchiveBox extends GenericModel {
	
    @Schema(
        description = "Unique archive box code within the archive room",
        example = "BOX-2025-001",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Archive box code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;
	
    @Schema(
        description = "Optional description of the archive box content or purpose",
        example = "Contracts of year 2025 for procurement department",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Description must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String description;
	
    @Schema(
        description = "Shelf where this archive box is stored",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Shelf is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_06_FK_01"),
        nullable = false
    )
    private Shelf shelf;
	
    @Schema(
        description = "Shelf floor/level where this archive box is placed",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Shelf floor is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_06_FK_02"),
        nullable = false
    )
    private ShelfFloor shelfFloor;
}