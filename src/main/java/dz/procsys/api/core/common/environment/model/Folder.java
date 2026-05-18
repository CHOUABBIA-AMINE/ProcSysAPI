/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Folder
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.procsys.api.core.common.environment.model;

import java.util.List;

import dz.procsys.api.core.common.communication.model.Mail;
import dz.procsys.api.core.common.document.model.Document;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
 * Folder Entity - Extends GenericModel.
 * Represents folders inside archive boxes, grouping mails and documents.
 */
@Schema(
    name = "Folder",
    description = "Represents a physical folder stored in an archive box, grouping mails and documents"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Folder")
@Table(
    name = "T_01_01_07",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_01_07_UK_01", columnNames = { "F_01" })
    }
)
public class Folder extends GenericModel {
	
    @Schema(
        description = "Folder code inside the archive box",
        example = "FOLD-2025-001",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Folder code is mandatory")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;
	
    @Schema(
        description = "Optional description of the folder content (time period, process, etc.)",
        example = "Consultation files for year 2025 - Structure X",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Description must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String description;
	
    @Schema(
        description = "Archive box where this folder is stored",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Archive box is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_07_FK_01"),
        nullable = false
    )
    private ArchiveBox archiveBox;
	
    @Schema(
        description = "Mails physically contained in this folder",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "R_T010107_T010203",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010107_T010203_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010107_T010203_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T010107_T010203_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Mail> mails;
	
    @Schema(
        description = "Documents physically contained in this folder",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "R_T010107_T010302",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010107_T010302_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010107_T010302_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T010107_T010302_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Document> documents;
}