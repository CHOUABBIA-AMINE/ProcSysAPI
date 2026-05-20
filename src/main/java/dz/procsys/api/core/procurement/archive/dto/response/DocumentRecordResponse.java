package dz.procsys.api.core.procurement.archive.dto.response;

import dz.procsys.api.core.procurement.archive.model.DocumentClassification;
import java.time.LocalDate;

public record DocumentRecordResponse(
    Long id,
    Long archiveFolderId,
    Long documentId,
    Long mailId,
    DocumentClassification classification,
    String referenceNumber,
    LocalDate issueDate,
    String supplierReference,
    String contractReference,
    String fiscalPeriod
) {
}
