package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter @ToString @EqualsAndHashCode(callSuper = true) @NoArgsConstructor @AllArgsConstructor
@Entity(name = "RetentionAssignment")
@Table(name = "T_01_01_11", indexes = {
    @Index(name = "T_01_01_11_IX_01", columnList = "F_01"),
    @Index(name = "T_01_01_11_IX_02", columnList = "F_07"),
    @Index(name = "T_01_01_11_IX_03", columnList = "F_08"),
    @Index(name = "T_01_01_11_IX_04", columnList = "F_02"),
    @Index(name = "T_01_01_11_IX_05", columnList = "F_03")
})
public class RetentionAssignment extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_11_FK_01"), nullable = false)
    private RetentionPolicy retentionPolicy;

    @ManyToOne
    @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_11_FK_02"))
    private DocumentRecord documentRecord;

    @ManyToOne
    @JoinColumn(name = "F_03", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_11_FK_03"))
    private ArchiveFolder archiveFolder;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_04", nullable = false, length = 50)
    private RetentionStartTrigger startTrigger;

    @Column(name = "F_05", nullable = false)
    private LocalDate startDate;

    @Column(name = "F_06", nullable = false)
    private Integer retentionPeriodDays;

    @Column(name = "F_07", nullable = false)
    private LocalDate disposalEligibilityDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_08", nullable = false, length = 50)
    private RetentionLifecycleState lifecycleState;
}
