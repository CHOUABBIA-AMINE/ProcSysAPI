/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Folder
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.model;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ArchiveFolder Entity - Extends GenericModel.
 * Represents an archive folder stored inside a specific archive box.
 * A folder groups related mails and documents for traceable archiving.
 */
@Schema(name = "ArchiveFolder", description = "Represents an archive folder inside a specific archive box, grouping related mails and documents.")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveFolder")
@Table(name = "T_01_01_07", uniqueConstraints = {@UniqueConstraint(name = "T_01_01_07_UK_01", columnNames = { "F_01" })})
public class ArchiveFolder extends GenericModel {

    /**
     * Unique code identifying the archive folder within the archive system.
     */
    @Schema(
        description = "Unique code identifying the archive folder",
        example = "FOLDER-2026-001"
    )
    @NotBlank(message = "Archive folder code is mandatory")
    @Size(max = 50, message = "Archive folder code must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String code;

    /**
     * Optional description providing additional context about the folder content.
     */
    @Schema(
        description = "Optional description of the archive folder content",
        example = "Tender dossier - Project XYZ"
    )
    @Size(max = 200, message = "Archive folder description must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String description;

    /**
     * Archive box where this folder is physically stored.
     * Links the folder to a concrete physical container.
     */
    @Schema(
        description = "Archive box where this folder is physically stored"
    )
    @NotNull(message = "Archive box is mandatory for folder placement")
    @ManyToOne
    @JoinColumn(
        name = "F_03",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_01_07_FK_01"),
        nullable = false
    )
    private ArchiveBox archiveBox;

    /**
     * Mails associated with this archive folder.
     * The join table R_T010107_T010203 maintains the many-to-many relationship.
     */
    @Schema(
        description = "Mails associated with this archive folder"
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "R_T010107_T010203",
        joinColumns = @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T010107_T010203_FK_01")),
        inverseJoinColumns = @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T010107_T010203_FK_02")),
        uniqueConstraints = @UniqueConstraint(name = "R_T010107_T010203_UK_01", columnNames = { "F_01", "F_02" })
    )
    private List<Mail> mails;

    /**
     * Documents associated with this archive folder.
     * The join table R_T010107_T010302 maintains the many-to-many relationship.
     */
    @Schema(
        description = "Documents associated with this archive folder"
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "R_T010107_T010302",
        joinColumns = @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T010107_T010302_FK_01")),
        inverseJoinColumns = @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "R_T010107_T010302_FK_02")),
        uniqueConstraints = @UniqueConstraint(name = "R_T010107_T010302_UK_01",columnNames = { "F_01", "F_02" })
    )
    private List<Document> documents;
}