package dz.procsys.api.core.procurement.archive.repository.projection;

import dz.procsys.api.core.procurement.archive.model.DocumentClassification;

import java.time.LocalDate;

public interface DocumentRecordSearchView {
    Long getId();
    DocumentClassification getClassification();
    String getReferenceNumber();
    LocalDate getIssueDate();
    String getSupplierReference();
    String getContractReference();
    String getFiscalPeriod();
    Long getArchiveFolderId();
    String getArchiveFolderCode();
}
