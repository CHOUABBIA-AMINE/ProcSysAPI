/**
 *
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: Notification
 *	@CreatedOn	: 02-01-2026
 *	@UpdatedOn	: 02-01-2026
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.procsys.api.core.common.communication.model;

import java.time.LocalDateTime;

import dz.procsys.api.core.system.security.model.User;
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Notification entity for storing user notifications.
 */
@Schema(
    name = "Notification",
    description = "User notification entity used to inform users about events related to business entities"
)
@Setter
@Getter
@ToString(exclude = { "recipient", "type" })
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Notification")
@Table(
    name = "T_00_05_01",
    indexes = {
        @Index(name = "IDX_T000501_F04", columnList = "F_04"),
        @Index(name = "IDX_T000501_F07", columnList = "F_07"),
        @Index(name = "IDX_T000501_F09", columnList = "F_09")
    }
)
public class Notification extends GenericModel {
	
    @Schema(
        description = "Notification title",
        example = "New reading awaiting validation"
    )
    @NotBlank(message = "Notification title is mandatory")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(name = "F_01", length = 200, nullable = false)
    private String title;

    @Schema(
        description = "Notification message body",
        example = "A new reading has been submitted and is awaiting your validation."
    )
    @NotBlank(message = "Notification message is mandatory")
    @Size(max = 1000, message = "Message must not exceed 1000 characters")
    @Column(name = "F_02", length = 1000, nullable = false)
    private String message;

    @Schema(
        description = "Identifier of the related business entity (e.g. consultation id, contract id)",
        example = "CONS-2025-000123"
    )
    @Column(name = "F_03", length = 50)
    private String relatedEntityId;

    @Schema(
        description = "Type of the related business entity",
        example = "Consultation"
    )
    @Column(name = "F_04", length = 50)
    private String relatedEntityType;

    @Schema(
        description = "Read status flag",
        example = "false"
    )
    @Builder.Default
    @Column(name = "F_05", nullable = false)
    private Boolean isRead = false;

    @Schema(
        description = "Timestamp when the notification was read",
        example = "2026-02-01T10:30:00"
    )
    @Column(name = "F_06")
    private LocalDateTime readAt;

    @Schema(
        description = "Creation timestamp of the notification",
        example = "2026-02-01T10:00:00"
    )
    @Builder.Default
    @Column(name = "F_07", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Schema(
        description = "Notification type definition (error, info, success, etc.)"
    )
    @NotNull(message = "Notification type is mandatory")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "F_08",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_05_01_FK_01"),
        nullable = false
    )
    private NotificationType type;

    @Schema(
        description = "Recipient user that will receive this notification"
    )
    @NotNull(message = "Recipient is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "F_09",
        referencedColumnName = "F_00",
        foreignKey = @ForeignKey(name = "T_00_05_01_FK_02"),
        nullable = false
    )
    private User recipient;

    public void markAsRead() {
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }
}