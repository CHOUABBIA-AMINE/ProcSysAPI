/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditContext
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Captures the technical execution context in which an auditable event occurred.
 * Includes request origin, environment metadata, and traceability markers.
 * Immutable; never updated after persistence.
 */
@Schema(
    name = "AuditContext",
    description = "Technical execution context of an auditable event: request origin, environment, traceability markers"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditContext")
@Table(
    name = "T_00_03_08",
    indexes = {
        @Index(name = "IDX_T_00_03_08_01", columnList = "F_01"),
        @Index(name = "IDX_T_00_03_08_02", columnList = "F_02"),
        @Index(name = "IDX_T_00_03_08_03", columnList = "F_05")
    }
)
public class AuditContext extends GenericModel {

    /**
     * Correlation identifier linking related audit records across the platform.
     * This is the primary forensic reconstruction key.
     */
    @Schema(
        description = "Correlation ID linking all audit records within the same logical transaction",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Correlation ID is mandatory")
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String correlationId;

    /**
     * Unique identifier of the HTTP request that triggered the event.
     */
    @Schema(
        description = "HTTP request ID that triggered this audit event",
        example = "req-8c2d4f3a-...",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 200
    )
    @Size(max = 200, message = "Request ID must not exceed 200 characters")
    @Column(name = "F_02", length = 200)
    private String requestId;

    /**
     * Name of the service method or handler that triggered the event.
     */
    @Schema(
        description = "Name of the service method or handler that triggered the event",
        example = "SettingValueService.updateScopedValue",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 300
    )
    @Size(max = 300, message = "Handler name must not exceed 300 characters")
    @Column(name = "F_03", length = 300)
    private String handlerName;

    /**
     * HTTP method of the originating request (GET, POST, PUT, DELETE, PATCH).
     */
    @Schema(
        description = "HTTP method of the originating request",
        example = "PUT",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 10
    )
    @Size(max = 10, message = "HTTP method must not exceed 10 characters")
    @Column(name = "F_04", length = 10)
    private String httpMethod;

    /**
     * Microservice or application node that processed the event.
     */
    @Schema(
        description = "Name of the service or application node that processed the event",
        example = "procsys-api-node-01",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 100
    )
    @Size(max = 100, message = "Service node must not exceed 100 characters")
    @Column(name = "F_05", length = 100)
    private String serviceNode;

    /**
     * Platform environment in which the event occurred (PRODUCTION, STAGING, etc.).
     */
    @Schema(
        description = "Platform environment where the event occurred",
        example = "PRODUCTION",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 50
    )
    @Size(max = 50, message = "Environment must not exceed 50 characters")
    @Column(name = "F_06", length = 50)
    private String environment;

    /**
     * Additional JSON metadata bag for context-specific traceability fields.
     */
    @Schema(
        description = "Additional JSON metadata for context-specific traceability",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_07", columnDefinition = "TEXT")
    private String metadata;
}