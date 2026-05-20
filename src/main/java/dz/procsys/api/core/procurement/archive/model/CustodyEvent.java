package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CustodyEvent")
@Table(name = "T_01_01_12", indexes = {
    @Index(name = "T_01_01_12_IX_01", columnList = "F_03"),
    @Index(name = "T_01_01_12_IX_02", columnList = "F_01"),
    @Index(name = "T_01_01_12_IX_03", columnList = "F_02"),
    @Index(name = "T_01_01_12_IX_04", columnList = "F_07"),
    @Index(name = "T_01_01_12_IX_05", columnList = "F_08"),
    @Index(name = "T_01_01_12_IX_06", columnList = "F_09")
})
public class CustodyEvent extends GenericModel {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "F_01", nullable = false, length = 20)
    private CustodyEventType eventType;

    @NotNull
    @Column(name = "F_02", nullable = false)
    private Instant eventTimestamp;

    @NotBlank
    @Column(name = "F_03", nullable = false, length = 100)
    private String actorIdentifier;

    @ManyToOne
    @JoinColumn(name = "F_04", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_01"))
    private CustodyHolder fromHolder;

    @ManyToOne
    @JoinColumn(name = "F_05", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_02"))
    private CustodyHolder toHolder;

    @ManyToOne
    @JoinColumn(name = "F_06", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_03"))
    private ArchiveBox archiveBox;

    @ManyToOne
    @JoinColumn(name = "F_07", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_04"))
    private ArchiveFolder archiveFolder;

    @ManyToOne
    @JoinColumn(name = "F_08", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_05"))
    private DocumentRecord documentRecord;

    @ManyToOne
    @JoinColumn(name = "F_09", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_06"))
    private CustodySession custodySession;
}
