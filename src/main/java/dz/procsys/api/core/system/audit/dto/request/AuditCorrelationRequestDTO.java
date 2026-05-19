/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditCorrelationRequestDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Request
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request DTO for retrieving all audit records associated with a single correlation ID.
 */
@Schema(
    name = "AuditCorrelationRequestDto",
    description = "Request parameters for retrieving all audit records associated with a single correlation ID"
)
@Setter
@Getter
@ToString
public class AuditCorrelationRequestDTO {

    @Schema(
        description = "Correlation ID linking all audit records within the same logical transaction",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Correlation ID is mandatory")
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    private String correlationId;
}