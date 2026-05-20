package dz.procsys.api.core.procurement.archive.model;

import dz.procsys.api.platform.kernel.model.GenericModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "F_04", nullable = false, length = 32)
    private DocumentClassification classification;

    @Column(name = "F_05", length = 120)
    private String referenceNumber;

    @Column(name = "F_06")
    private java.time.LocalDate issueDate;

    @Column(name = "F_07", length = 120)
    private String supplierReference;

    @Column(name = "F_08", length = 120)
    private String contractReference;

    @Column(name = "F_09", length = 32)
    private String fiscalPeriod;

    @AssertTrue(message = "Mandatory metadata is missing for the selected classification")
    public boolean isClassificationMetadataValid() {
        if (classification == null || isBlank(referenceNumber) || issueDate == null) {
            return false;
        }

        return switch (classification) {
            case PO, INVOICE, DELIVERY_NOTE, PAYMENT_FILE, RECEIPT -> !isBlank(supplierReference) && !isBlank(fiscalPeriod);
            case CONTRACT, AMENDMENT -> !isBlank(contractReference);
            case TENDER_FILE -> !isBlank(fiscalPeriod);
            case EVALUATION_FILE -> !isBlank(contractReference) && !isBlank(fiscalPeriod);
        };
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
