package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter @ToString @EqualsAndHashCode(callSuper = true) @NoArgsConstructor @AllArgsConstructor
@Entity(name = "LegalHold")
@Table(name = "T_01_01_12")
public class LegalHold extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_01"))
    private DocumentRecord documentRecord;

    @ManyToOne
    @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_12_FK_02"))
    private ArchiveFolder archiveFolder;

    @Column(name = "F_03", nullable = false)
    private boolean active;

    @Column(name = "F_04", nullable = false)
    private LocalDate holdPlacedDate;

    @Column(name = "F_05")
    private LocalDate holdReleasedDate;

    @Column(name = "F_06", length = 500)
    private String reason;
}
