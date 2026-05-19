/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditEvent
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Class
 * @Layer       : Model
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.model;

import java.util.Date;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a domain-level audit event arriving from any platform module via the event-driven pipeline.
 * This entity captures the raw inbound event before it is enriched and promoted to an AuditRecord.
 * Acts as the entry point of the audit pipeline: Domain Event → AuditEvent → AuditRecord.
 * Immutable; never updated after persistence.
 */
@Schema(
    name = "AuditEvent",
    description = "Raw inbound domain event received by the audit pipeline before enrichment and promotion to AuditRecord"
)
@Setter
@Getter
@ToString(exclude = {"eventType"})
@EqualsAndHashCode(callSuper = true, exclude = {"eventType"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AuditEvent")
@Table(
    name = "T_00_04_11",
    indexes = {
        @Index(name = "IDX__T_00_04_11__CORRELATION", columnList = "F_01"),
        @Index(name = "IDX__T_00_04_11__RECEIVED", columnList = "F_02"),
        @Index(name = "IDX__T_00_04_11__TYPE", columnList = "F_04"),
        @Index(name = "IDX__T_00_04_11__STATUS", columnList = "F_05"),
        @Index(name = "IDX__T_00_04_11__SOURCE", columnList = "F_06")
    }
)
public class AuditEvent extends GenericModel {

    /**
     * Correlation identifier extracted from the inbound domain event.
     */
    @Schema(
        description = "Correlation ID extracted from the inbound domain event",
        example = "corr-9f4a2b1e-c3d5-4a8f-b2e0-1a2b3c4d5e6f",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 200
    )
    @NotBlank(message = "Correlation ID is mandatory")
    @Size(max = 200, message = "Correlation ID must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String correlationId;

    /**
     * Timestamp when this event was received by the audit pipeline.
     */
    @Schema(
        description = "Timestamp when this event was received by the audit pipeline",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Received timestamp is mandatory")
    @PastOrPresent(message = "Received timestamp must be in the past or present")
    @Column(name = "F_02", nullable = false)
    private Date receivedAt;

    /**
     * Raw JSON payload of the inbound domain event.
     */
    @Schema(
        description = "Raw JSON payload of the inbound domain event",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Raw payload is mandatory")
    @Column(name = "F_03", columnDefinition = "TEXT", nullable = false)
    private String rawPayload;

    /**
     * Classification of the event type (FK to AuditEventType).
     */
    @Schema(
        description = "Classification of the inbound event type",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Event type is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_04",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "FK__T_00_04_11__EVENT_TYPE"),
        nullable = false
    )
    private AuditEventType eventType;

    /**
     * Processing status of this event: RECEIVED, PROCESSING, PROCESSED, FAILED.
     */
    @Schema(
        description = "Processing status of this event in the audit pipeline",
        example = "PROCESSED",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 50
    )
    @NotBlank(message = "Processing status is mandatory")
    @Size(max = 50, message = "Processing status must not exceed 50 characters")
    @Column(name = "F_05", length = 50, nullable = false)
    private String processingStatus;

    /**
     * Source module that emitted this domain event.
     */
    @Schema(
        description = "Platform module that emitted this domain event",
        example = "system.setting",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    @NotBlank(message = "Source module is mandatory")
    @Size(max = 100, message = "Source module must not exceed 100 characters")
    @Column(name = "F_06", length = 100, nullable = false)
    private String sourceModule;

    /**
     * Error description if pipeline processing failed for this event.
     */
    @Schema(
        description = "Error description if pipeline processing failed",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_07", columnDefinition = "TEXT")
    private String processingError;

    /**
     * Reference to the AuditRecord promoted from this event (null until processing completes).
     */
    @Schema(
        description = "ID of the AuditRecord promoted from this event after enrichment",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Column(name = "F_08")
    private Long promotedAuditRecordId;
}