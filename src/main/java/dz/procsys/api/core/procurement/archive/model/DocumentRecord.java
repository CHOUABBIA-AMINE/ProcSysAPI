package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(name = "DocumentRecord", description = "Archived record referencing external documents by scalar identifiers")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DocumentRecord")
@Table(name = "T_01_01_09")
public class DocumentRecord extends GenericModel {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "F_01", referencedColumnName = "F_00", foreignKey = @ForeignKey(name = "T_01_01_09_FK_01"), nullable = false)
    private ArchiveFolder archiveFolder;

    @NotNull
    @Column(name = "F_02", nullable = false)
    private Long documentId;

    @Column(name = "F_03")
    private Long mailId;
}
