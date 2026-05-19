/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditContextResponseDTO
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : DTO / Response
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Response DTO exposing technical execution context of an audit record.
 */
@Schema(
    name = "AuditContextResponseDto",
    description = "Technical execution context associated with an audit record"
)
@Setter
@Getter
@ToString
public class AuditContextResponseDTO {

    @Schema(
        description = "Correlation ID linking audit records within the same logical transaction",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f"
    )
    private String correlationId;

    @Schema(
        description = "HTTP request ID that triggered this audit event",
        example = "req-8c2d4f3a-..."
    )
    private String requestId;

    @Schema(
        description = "Name of the handler or service method that triggered the event",
        example = "SettingValueService.updateScopedValue"
    )
    private String handlerName;

    @Schema(
        description = "HTTP method of the originating request",
        example = "PUT"
    )
    private String httpMethod;

    @Schema(
        description = "Service or node that processed the event",
        example = "procsys-api-node-01"
    )
    private String serviceNode;

    @Schema(
        description = "Environment where the event occurred (e.g. PRODUCTION)",
        example = "PRODUCTION"
    )
    private String environment;
}