/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Shelf
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
 * Shelf Entity - Extends GenericModel.
 * Represents shelves in rooms with multilingual designations.
 */
@Schema(
    name = "Shelf",
    description = "Represents a physical shelf in a room, used to store archive boxes, with code and multilingual designations"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Shelf")
@Table(
    name = "T_01_01_04",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_01_04_UK_01", columnNames = { "F_01" })
    }
)
public class Shelf extends GenericModel {
	
    @Schema(
        description = "Shelf code within the room (e.g., S1, S2, A, B)",
        example = "S1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Shelf code is mandatory")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the shelf",
        example = "الرف الأول",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationAr;
	
    @Schema(
        description = "English designation of the shelf",
        example = "First shelf",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the shelf (primary label)",
        example = "Étagère 1",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Room where this shelf is located",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Room is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_04_FK_01"),
        nullable = false
    )
    private Room room;
}