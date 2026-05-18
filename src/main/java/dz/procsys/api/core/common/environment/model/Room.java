/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Room
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
 * Room Entity - Extends GenericModel.
 * Represents rooms in blocs/floors with multilingual designations.
 */
@Schema(
    name = "Room",
    description = "Represents a room located in a bloc and floor, with code and multilingual designations"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Room")
@Table(
    name = "T_01_01_03",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "T_01_01_03_UK_01",
            columnNames = { "F_05", "F_04" }
        )
    }
)
public class Room extends GenericModel {
	
    @Schema(
        description = "Room code within the bloc/floor (e.g., B1-101, S01)",
        example = "B1-101",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 20
    )
    @NotBlank(message = "Room code is mandatory")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "F_01", length = 20, nullable = false)
    private String code;
	
    @Schema(
        description = "Arabic designation of the room",
        example = "قاعة الاجتماعات",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationAr;
	
    @Schema(
        description = "English designation of the room",
        example = "Meeting room",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the room (primary label)",
        example = "Salle de réunion",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_04", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Bloc (building unit) where the room is located",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Bloc is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_03_FK_01"),
        nullable = false
    )
    private Bloc bloc;
	
    @Schema(
        description = "Floor where the room is located",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Floor is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_03_FK_02"),
        nullable = false
    )
    private Floor floor;
}