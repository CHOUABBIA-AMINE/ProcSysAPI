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
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CustodyHolder")
@Table(name = "T_01_01_10", uniqueConstraints = {
    @UniqueConstraint(name = "T_01_01_10_UK_01", columnNames = { "F_01" })
})
public class CustodyHolder extends GenericModel {

    @NotBlank
    @Size(max = 100)
    @Column(name = "F_01", nullable = false, length = 100)
    private String actorIdentifier;

    @Size(max = 200)
    @Column(name = "F_02", length = 200)
    private String displayName;
}
