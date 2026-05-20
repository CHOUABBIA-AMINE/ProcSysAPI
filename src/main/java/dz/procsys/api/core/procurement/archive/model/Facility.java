package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
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

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveFacility")
@Table(name = "T_01_01_09", uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_09_UK_01", columnNames = { "F_01" })
})
public class Facility extends GenericModel {

    @NotBlank
    @Size(max = 50)
    @Column(name = "F_01", nullable = false, length = 50)
    private String code;

    @Size(max = 200)
    @Column(name = "F_02", length = 200)
    private String name;
}
