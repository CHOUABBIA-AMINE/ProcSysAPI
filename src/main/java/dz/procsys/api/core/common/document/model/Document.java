/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Document
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Document
 *
 **/

package dz.procsys.api.core.common.document.model;

import java.util.Date;

import dz.procsys.api.core.system.utility.model.File;
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
 * Document Entity - Extends GenericModel
 * Represents documents with type, reference, and file attachments.
 */
@Schema(
    name = "Document",
    description = "Represents a business document with reference, subject, date, type and attached file"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Document")
@Table(
    name = "T_01_03_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_03_02_UK_01", columnNames = { "F_01" })
    }
)
public class Document extends GenericModel {
	
    @Schema(
        description = "Unique document reference within the owning structure or process",
        example = "CONS-2025-000123",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Document reference is mandatory")
    @Size(max = 50, message = "Document reference must not exceed 50 characters")
    @Column(name = "F_01", length = 50, nullable = false)
    private String reference;
	
    @Schema(
        description = "Short subject or title of the document",
        example = "Dossier de consultation N°12/2025",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @Size(max = 500, message = "Subject must not exceed 500 characters")
    @Column(name = "F_02", length = 500)
    private String subject;
	
    @Schema(
        description = "Official date of the document (signature or issue date)",
        example = "2025-12-10T00:00:00Z",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_03")
    private Date documentDate;
	
    @Schema(
        description = "Type of the document (e.g. CONSULTATION_FILE, CONTRACT, AMENDMENT)",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Document type is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_04",
        foreignKey = @ForeignKey(name = "T_01_03_02_FK_01"),
        nullable = false
    )
    private DocumentType documentType;
	
    @Schema(
        description = "Physical or electronic file attached to this document",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "File is mandatory")
    @ManyToOne
    @JoinColumn(
        name = "F_05",
        foreignKey = @ForeignKey(name = "T_01_03_02_FK_02"),
        nullable = false
    )
    private File file;
}