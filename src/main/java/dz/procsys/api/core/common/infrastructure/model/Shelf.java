/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : Shelf
 *  @CreatedOn  : 06-26-2025
 *  @Updated    : 12-11-2025
 *
 *  @Type       : Class
 *  @Layer      : Model
 *  @Package    : Common / Infrastructure
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
 * Shelf Entity - Extends GenericModel.
 * Represents a storage shelf located inside a specific room.
 * Shelves can be used to host archive boxes or other storage units.
 */
@Schema(
    name = "Shelf",
    description = "Represents a storage shelf located inside a specific room, used for placing archive boxes or other storage units."
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Shelf")
@Table(name = "T_01_01_03", uniqueConstraints = {@UniqueConstraint(name = "T_01_01_03_UK_01", columnNames = { "F_01" })})
public class Shelf extends GenericModel {

    /**
     * Shelf code unique within the room (e.g. S01).
     */
    @Schema(
        description = "Shelf code unique within the room",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "S01",
        maxLength = 20
    )
    @NotBlank(message = "Shelf code is mandatory")
    @Size(max = 20, message = "Shelf code must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
    private String code;

    /**
     * Room where this shelf is located.
     */
    @Schema(
        description = "Room where this shelf is located",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Room is mandatory for shelf placement")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_03_FK_01"), nullable = false)
    private Room room;
}