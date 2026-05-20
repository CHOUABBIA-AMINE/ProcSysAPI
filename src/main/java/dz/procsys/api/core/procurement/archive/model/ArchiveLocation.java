package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Index;
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
import lombok.ToString;

@Schema(name = "ArchiveLocation", description = "Location alias for a shelf level")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveLocation")
@Table(name = "T_01_01_08", indexes = {
    @Index(name = "T_01_01_08_IX_01", columnList = "F_01"),
    @Index(name = "T_01_01_08_IX_02", columnList = "F_02")
}, uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_08_UK_01", columnNames = { "F_01" }),
    @UniqueConstraint(name = "T_01_01_08_UK_02", columnNames = { "F_02" })
})
public class ArchiveLocation extends GenericModel {

    @NotBlank
    @Size(max = 250)
    @Column(name = "F_01", nullable = false, length = 250)
    private String code;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "F_02", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_08_FK_01"), nullable = false)
    private ShelfLevel shelfLevel;
}
