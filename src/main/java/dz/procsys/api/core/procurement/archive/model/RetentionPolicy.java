package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode(callSuper = true) @NoArgsConstructor @AllArgsConstructor
@Entity(name = "RetentionPolicy")
@Table(name = "T_01_01_10")
public class RetentionPolicy extends GenericModel {
    @Column(name = "F_01", nullable = false, unique = true, length = 80)
    private String code;

    @Column(name = "F_02", nullable = false, length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_03", nullable = false, length = 50)
    private RetentionClass retentionClass;

    @Column(name = "F_04", nullable = false)
    private Integer retentionPeriodDays;
}
