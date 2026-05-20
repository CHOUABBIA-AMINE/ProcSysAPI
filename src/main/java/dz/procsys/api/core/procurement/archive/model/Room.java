package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveRoom")
@Table(name = "T_01_01_10", uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_10_UK_01", columnNames = { "F_01", "F_02" })
})
public class Room extends GenericModel {
    @NotBlank
    @Size(max = 50)
    @Column(name = "F_01", nullable = false, length = 50)
    private String code;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_10_FK_01"), nullable = false)
    private Facility facility;
}
