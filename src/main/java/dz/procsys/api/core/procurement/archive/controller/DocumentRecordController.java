package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.dto.response.DocumentRecordResponse;
import dz.procsys.api.core.procurement.archive.model.DocumentClassification;
import dz.procsys.api.core.procurement.archive.service.DocumentRecordService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/procurement/archive/document-records")
@RequiredArgsConstructor
public class DocumentRecordController {

    private final DocumentRecordService documentRecordService;

    @GetMapping("/search")
    public Page<DocumentRecordResponse> searchByClassification(
        @RequestParam(required = false) DocumentClassification classification,
        @RequestParam(required = false) String referenceNumber,
        @RequestParam(required = false) String supplierOrContractReference,
        @RequestParam(required = false) String fiscalPeriod,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueDateFrom,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueDateTo,
        Pageable pageable
    ) {
        return documentRecordService.searchByClassification(
            classification,
            referenceNumber,
            supplierOrContractReference,
            fiscalPeriod,
            issueDateFrom,
            issueDateTo,
            pageable
        );
    }
}
