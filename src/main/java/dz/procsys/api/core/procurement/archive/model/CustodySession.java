package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity(name = "CustodySession")
@Table(name = "T_01_01_11")
public class CustodySession extends GenericModel {

    @NotBlank
    @Size(max = 100)
    @Column(name = "F_01", nullable = false, length = 100)
    private String sessionKey;

    @Column(name = "F_02")
    private java.time.Instant openedAt;

    @Column(name = "F_03")
    private java.time.Instant closedAt;
}
