/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: MailNature
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
 * MailNature Entity - Extends GenericModel
 * Represents nature/category of mail with multilingual designations.
 */
@Schema(
    name = "MailNature",
    description = "Reference entity describing the nature of a mail (incoming, outgoing, internal, etc.)"
)
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MailNature")
@Table(
    name = "T_01_02_01",
    uniqueConstraints = {
        @UniqueConstraint(name = "T_01_02_01_UK_01", columnNames = { "F_03" })
    }
)
public class MailNature extends GenericModel {
	
    @Schema(
        description = "Arabic designation of the mail nature",
        example = "طبيعة المراسلة",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    @Column(name = "F_01", length = 100)
    private String designationAr;

    @Schema(
        description = "English designation of the mail nature",
        example = "Incoming mail",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    @Column(name = "F_02", length = 100)
    private String designationEn;
	
    @Schema(
        description = "French designation of the mail nature",
        example = "Courrier entrant",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "French designation is mandatory")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    @Column(name = "F_03", length = 100, nullable = false)
    private String designationFr;
}