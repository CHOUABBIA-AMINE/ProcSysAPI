package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.dto.request.CustodyActionRequest;
import dz.procsys.api.core.procurement.archive.dto.response.CustodyEventResponse;

import java.util.List;
import java.util.Optional;

public interface CustodyService {
    CustodyEventResponse checkout(CustodyActionRequest request);

    CustodyEventResponse transfer(CustodyActionRequest request);

    CustodyEventResponse returnItem(CustodyActionRequest request);

    List<CustodyEventResponse> historyByFolder(Long folderId);

    List<CustodyEventResponse> historyByDocument(Long documentRecordId);

    Optional<String> currentHolderByFolder(Long folderId);

    Optional<String> currentHolderByDocument(Long documentRecordId);
}
