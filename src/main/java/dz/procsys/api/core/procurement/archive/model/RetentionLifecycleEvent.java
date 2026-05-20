package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter @Getter @ToString @EqualsAndHashCode(callSuper = true) @NoArgsConstructor @AllArgsConstructor
@Entity(name = "RetentionLifecycleEvent")
@Table(name = "T_01_01_13")
public class RetentionLifecycleEvent extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_13_FK_01"), nullable = false)
    private RetentionAssignment retentionAssignment;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_02", nullable = false, length = 60)
    private RetentionEventType eventType;

    @Column(name = "F_03", nullable = false)
    private Instant eventTimestamp;

    @Column(name = "F_04", length = 600)
    private String note;
}
