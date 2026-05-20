/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBox
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.model;

import dz.procsys.api.core.common.infrastructure.model.Shelf;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ArchiveBox Entity - Extends GenericModel.
 * Represents a physical archive box used exclusively for document archiving.
 * Each box is uniquely identified by a code and is placed on a specific shelf.
 */

@Schema(name = "ArchiveBox", description = "Represents a physical archive box used exclusively for document archiving, placed on a specific shelf.")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveBox")
@Table(name = "T_01_05_01", uniqueConstraints = {@UniqueConstraint(name = "T_01_05_01_UK_01", columnNames = { "F_01" })})
public class ArchiveBox extends GenericModel {

    /**
     * Unique code identifying the archive box (e.g. BOX-2026-001).
     */
    @Schema(
        description = "Unique code identifying the archive box",
        example = "BOX-2026-001"
    )
    @NotBlank(message = "Archive box code is mandatory")
    @Size(max = 50, message = "Archive box code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;

    /**
     * Optional human-readable description of the archive box content or purpose.
     */
    @Schema(
        description = "Optional description of the archive box content or purpose",
        example = "Procurement contracts - FY 2026"
    )
    @Size(max = 200, message = "Archive box description must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String description;

    /**
     * Shelf where this archive box is physically located.
     * Links the archive-specific container to the generic infrastructure layout.
     */
    @Schema(
        description = "Shelf where this archive box is physically located"
    )
    @NotNull(message = "Shelf is mandatory for archive box placement")
    @ManyToOne
    @JoinColumn(name = "F_03", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_05_01_FK_01"), nullable = false)
    private Shelf shelf;
}