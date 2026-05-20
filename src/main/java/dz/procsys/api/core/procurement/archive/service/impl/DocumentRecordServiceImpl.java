package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.response.DocumentRecordResponse;
import dz.procsys.api.core.procurement.archive.model.DocumentClassification;
import dz.procsys.api.core.procurement.archive.model.DocumentRecord;
import dz.procsys.api.core.procurement.archive.repository.DocumentRecordRepository;
import dz.procsys.api.core.procurement.archive.service.DocumentRecordService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentRecordServiceImpl implements DocumentRecordService {

    private final DocumentRecordRepository documentRecordRepository;

    @Override
    public Page<DocumentRecordResponse> searchByClassification(
        DocumentClassification classification,
        String referenceNumber,
        String supplierOrContractReference,
        String fiscalPeriod,
        LocalDate issueDateFrom,
        LocalDate issueDateTo,
        Pageable pageable
    ) {
        return documentRecordRepository.searchByClassification(
            classification,
            referenceNumber,
            supplierOrContractReference,
            fiscalPeriod,
            issueDateFrom,
            issueDateTo,
            pageable
        ).map(this::toResponse);
    }

    private DocumentRecordResponse toResponse(DocumentRecord record) {
        return new DocumentRecordResponse(
            record.getId(),
            record.getArchiveFolder() == null ? null : record.getArchiveFolder().getId(),
            record.getDocumentId(),
            record.getMailId(),
            record.getClassification(),
            record.getReferenceNumber(),
            record.getIssueDate(),
            record.getSupplierReference(),
            record.getContractReference(),
            record.getFiscalPeriod()
        );
    }
}
