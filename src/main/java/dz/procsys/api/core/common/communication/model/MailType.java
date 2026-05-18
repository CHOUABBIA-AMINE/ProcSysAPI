/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: MailType
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.procsys.api.core.common.communication.model;

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
 * MailType Entity - Extends GenericModel
 * Represents type of mail with multilingual designations.
 */
@Schema(
    name = "MailType",
    description = "Reference entity describing the functional type of a mail (request, complaint, information, etc.)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MailType")
@Table(
    name = "T_01_02_02",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_02_02_UK_01", columnNames = { "F_03" })
    }
)
public class MailType extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the mail type",
        example = "نوع المراسلة",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the mail type",
        example = "Complaint",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the mail type",
        example = "Plainte",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
}