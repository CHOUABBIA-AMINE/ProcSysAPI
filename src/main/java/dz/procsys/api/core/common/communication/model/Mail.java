/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Mail
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-30-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.procsys.api.core.common.communication.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.procsys.api.core.common.administration.model.Structure;
import dz.procsys.api.core.system.utility.model.File;
import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Mail Entity - Extends GenericModel
 * Represents mail correspondence with references and attachments.
 */
@Schema(
    name = "Mail",
    description = "Represents an incoming or outgoing mail with references, dates, nature, type, structure and attached file"
)
@Setter
@Getter
@ToString(exclude = { "referencedMails" }) // Avoid circular toString issues
@EqualsAndHashCode(callSuper = true, exclude = { "referencedMails" }) // Avoid circular hashCode issues
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Mail")
@Table(
    name = "T_01_02_03",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_02_03_UK_01", columnNames = { "F_01" })
    }
)
public class Mail extends GenericModel {
	
    @Schema(
        description = "Internal reference for the mail, unique within the owning structure",
        example = "COUR-2025-000045",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Mail reference must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String reference;
	
    @Schema(
        description = "Registry or record number attributed by the mail office",
        example = "125/2025",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Record number must not exceed 50 characters")
    @Column(name = "F_02", length = 50)
    private String recordNumber;
	
    @Schema(
        description = "Short subject or title of the mail",
        example = "Transmission du dossier de consultation",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Subject must not exceed 500 characters")
    @Column(name = "F_03", length = 500)
    private String subject;
	
    @Schema(
        description = "Official date of the mail (date of issuance or reception on the document)",
        example = "2025-06-20T00:00:00Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_04")
    private Date mailDate;
	
    @Schema(
        description = "Registry date when the mail was recorded by the administration",
        example = "2025-06-21T00:00:00Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_05")
    private Date recordDate;
	
    @Schema(
        description = "Nature of the mail (incoming, outgoing, internal, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Mail nature is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_06",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_02_03_FK_01"),
        nullable = false
    )
    private MailNature mailNature;
	
    @Schema(
        description = "Type of the mail (registration request, complaint, information, etc.)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Mail type is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_07",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_02_03_FK_02"),
        nullable = false
    )
    private MailType mailType;
	
    @Schema(
        description = "Structure (organization unit) that owns or is concerned by this mail",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Structure is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_02_03_FK_03"),
        nullable = false
    )
    private Structure structure;
	
    @Schema(
        description = "Electronic file that contains the scanned or generated mail document",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "File is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_01_02_03_FK_04"),
        nullable = false
    )
    private File file;
	
    /**
     * Many-to-Many relationship for referenced mails.
     * Holds other mails that are linked as references (e.g. previous exchanges in the same thread).
     */
    @Schema(
        description = "List of other mails referenced by this mail (conversation or related exchanges)",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinTable(
        name = "R_T010203_T010203",
        joinColumns = @JoinColumn(
            name = "F_01",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010203_T010203_FK_01")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "F_02",
            referencedColumnName = "F_00",
            foreignKey = @ForeignKey(name = "R_T010203_T010203_FK_02")
        ),
        uniqueConstraints = @UniqueConstraint(
            name = "R_T010203_T010203_UK_01",
            columnNames = { "F_01", "F_02" }
        )
    )
    private List<Mail> referencedMails = new ArrayList<>();
}