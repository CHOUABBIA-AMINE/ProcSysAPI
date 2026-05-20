package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
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
import lombok.ToString;

@Schema(name = "ArchiveBox", description = "Archive box linked to a physical archive location")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcurementArchiveBox")
@Table(name = "T_01_01_06", uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_06_UK_01", columnNames = { "F_01" })
})
public class ArchiveBox extends GenericModel {

    @NotBlank
    @Size(max = 50)
    @Column(name = "F_01", nullable = false, length = 50)
    private String code;

    @Size(max = 200)
    @Column(name = "F_02", length = 200)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "F_03", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_06_FK_01"), nullable = false)
    private ArchiveLocation archiveLocation;
}
