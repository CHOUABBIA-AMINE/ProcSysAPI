/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentType
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Document
 *
 **/

package dz.procsys.api.core.common.document.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DocumentType Entity - Extends GenericModel
 * Represents types of documents with multilingual designations.
 */
@Schema(
    name = "DocumentType",
    description = "Reference entity describing available document types in the system"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DocumentType")
@Table(
    name = "T_01_03_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_03_01_UK_01", columnNames = { "F_03" })
    }
)
public class DocumentType extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the document type",
        example = "نوع الوثيقة بالعربية",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the document type",
        example = "Consultation file",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the document type",
        example = "Dossier de consultation",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
	
    @Schema(
        description = "Short code of the document type",
        example = "CONS_FILE",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 10
    )
    @NotBlank(message = "Document type code is mandatory")
    @Size(max = 10, message = "Code must not exceed 10 characters")
    @Column(name = "F_04", length = 10, nullable = false)
    private String code;
}