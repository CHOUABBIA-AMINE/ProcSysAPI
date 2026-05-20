package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.dto.response.DocumentRecordResponse;
import dz.procsys.api.core.procurement.archive.model.DocumentClassification;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentRecordService {

    Page<DocumentRecordResponse> searchByClassification(
        DocumentClassification classification,
        String referenceNumber,
        String supplierOrContractReference,
        String fiscalPeriod,
        LocalDate issueDateFrom,
        LocalDate issueDateTo,
        Pageable pageable
    );
}
