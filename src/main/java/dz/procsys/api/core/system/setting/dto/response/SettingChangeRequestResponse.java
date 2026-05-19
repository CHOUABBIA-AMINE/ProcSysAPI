/**
 *	
 *	@Author		: System
 *
 *	@Name		: SettingChangeRequestResponse
 *	@CreatedOn	: 05-19-2026
 *
 *	@Type		: Class
 *	@Layer		: DTO / Response
 *	@Package	: System / Setting
 *
 **/

package dz.procsys.api.core.system.setting.dto.response;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO for SettingChangeRequest.
 */
@Schema(name = "SettingChangeRequestResponse", description = "Response payload representing a setting change request in the approval workflow")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingChangeRequestResponse {

    /** Internal DB identifier. */
    @Schema(description = "Internal identifier", example = "1")
    private Long id;

    /** Setting definition ID. */
    @Schema(description = "Setting definition internal ID", example = "1")
    private Long settingDefinitionId;

    /** Setting definition code. */
    @Schema(description = "Setting definition key", example = "auth.session.timeout")
    private String settingDefinitionCode;

    /** Change type ID. */
    @Schema(description = "Change type internal ID", example = "1")
    private Long changeTypeId;

    /** Change type code. */
    @Schema(description = "Change type code", example = "UPDATE")
    private String changeTypeCode;

    /** Scope reference. */
    @Schema(description = "Scope reference to which the change applies", example = "GLOBAL")
    private String scopeReference;

    /** Proposed value. */
    @Schema(description = "The proposed new value", example = "60")
    private String proposedValue;

    /** Justification. */
    @Schema(description = "Business justification submitted by the requester", example = "Security audit SR-2026-04")
    private String justification;

    /** Current status. */
    @Schema(description = "Current status of the request", example = "PENDING", allowableValues = {"PENDING", "APPROVED", "REJECTED"})
    private String status;

    /** Approver remarks. */
    @Schema(description = "Remarks from the approver", example = "Approved — aligns with policy v2.4")
    private String approverRemarks;

    /** Decision timestamp. */
    @Schema(description = "Timestamp when a decision was made")
    private Date decidedAt;

    /** Decider username. */
    @Schema(description = "Username of the approver/rejector", example = "manager01")
    private String decidedBy;

    /** Optimistic lock version. */
    @Schema(description = "Optimistic lock version", example = "0")
    private Long version;

    /** Request submission timestamp. */
    @Schema(description = "Timestamp when the request was submitted")
    private Date createdAt;

    /** Requester username. */
    @Schema(description = "Username who submitted the request", example = "user01")
    private String createdBy;

    /** Last update timestamp. */
    @Schema(description = "Last update timestamp")
    private Date updatedAt;

    /** Last updater. */
    @Schema(description = "Last updater username", example = "manager01")
    private String updatedBy;
}
