package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(name = "ArchiveLocation", description = "Physical location descriptor for archive storage")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveLocation")
@Table(name = "T_01_01_08", uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_08_UK_01", columnNames = { "F_01" })
})
public class ArchiveLocation extends GenericModel {

    @NotBlank
    @Size(max = 50)
    @Column(name = "F_01", nullable = false, length = 50)
    private String code;

    @Size(max = 100)
    @Column(name = "F_02", length = 100)
    private String bloc;

    @Size(max = 100)
    @Column(name = "F_03", length = 100)
    private String floor;

    @Size(max = 100)
    @Column(name = "F_04", length = 100)
    private String room;

    @Size(max = 100)
    @Column(name = "F_05", length = 100)
    private String shelf;

    @Size(max = 100)
    @Column(name = "F_06", length = 100)
    private String shelfLevel;
}
