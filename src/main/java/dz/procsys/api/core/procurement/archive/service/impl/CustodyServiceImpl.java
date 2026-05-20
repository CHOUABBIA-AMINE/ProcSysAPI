package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.CustodyActionRequest;
import dz.procsys.api.core.procurement.archive.dto.response.CustodyEventResponse;
import dz.procsys.api.core.procurement.archive.model.*;
import dz.procsys.api.core.procurement.archive.repository.*;
import dz.procsys.api.core.procurement.archive.service.CustodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustodyServiceImpl implements CustodyService {

    private final CustodyEventRepository custodyEventRepository;
    private final CustodyHolderRepository custodyHolderRepository;
    private final CustodySessionRepository custodySessionRepository;
    private final ArchiveBoxRepository archiveBoxRepository;
    private final ArchiveFolderRepository archiveFolderRepository;
    private final DocumentRecordRepository documentRecordRepository;

    @Override
    public CustodyEventResponse checkout(CustodyActionRequest request) {
        return appendEvent(CustodyEventType.CHECKOUT, request);
    }

    @Override
    public CustodyEventResponse transfer(CustodyActionRequest request) {
        return appendEvent(CustodyEventType.TRANSFER, request);
    }

    @Override
    public CustodyEventResponse returnItem(CustodyActionRequest request) {
        return appendEvent(CustodyEventType.RETURN, request);
    }

    @Override
    public List<CustodyEventResponse> historyByFolder(Long folderId) {
        return custodyEventRepository.findByArchiveFolderIdOrderByEventTimestampAscIdAsc(folderId).stream().map(this::toResponse).toList();
    }

    @Override
    public List<CustodyEventResponse> historyByDocument(Long documentRecordId) {
        return custodyEventRepository.findByDocumentRecordIdOrderByEventTimestampAscIdAsc(documentRecordId).stream().map(this::toResponse).toList();
    }

    @Override
    public Optional<String> currentHolderByFolder(Long folderId) {
        return custodyEventRepository.findTopByArchiveFolderIdOrderByEventTimestampDescIdDesc(folderId)
            .map(CustodyEvent::getToHolder)
            .map(CustodyHolder::getActorIdentifier);
    }

    @Override
    public Optional<String> currentHolderByDocument(Long documentRecordId) {
        return custodyEventRepository.findTopByDocumentRecordIdOrderByEventTimestampDescIdDesc(documentRecordId)
            .map(CustodyEvent::getToHolder)
            .map(CustodyHolder::getActorIdentifier);
    }

    private CustodyEventResponse appendEvent(CustodyEventType eventType, CustodyActionRequest request) {
        CustodyEvent event = new CustodyEvent();
        event.setEventType(eventType);
        event.setEventTimestamp(Instant.now());
        event.setActorIdentifier(request.actorIdentifier());
        event.setFromHolder(resolveHolder(request.fromActorIdentifier()));
        event.setToHolder(resolveHolder(request.toActorIdentifier()));
        event.setArchiveBox(resolveArchiveBox(request.archiveBoxId()));
        event.setArchiveFolder(resolveArchiveFolder(request.archiveFolderId()));
        event.setDocumentRecord(resolveDocumentRecord(request.documentRecordId()));
        event.setCustodySession(resolveSession(request.custodySessionId()));
        return toResponse(custodyEventRepository.save(event));
    }

    private CustodyHolder resolveHolder(String actorIdentifier) {
        if (actorIdentifier == null || actorIdentifier.isBlank()) {
            return null;
        }
        return custodyHolderRepository.findByActorIdentifier(actorIdentifier)
            .orElseGet(() -> custodyHolderRepository.save(new CustodyHolder(actorIdentifier, null)));
    }

    private ArchiveBox resolveArchiveBox(Long id) {
        return id == null ? null : archiveBoxRepository.findById(id).orElse(null);
    }

    private ArchiveFolder resolveArchiveFolder(Long id) {
        return id == null ? null : archiveFolderRepository.findById(id).orElse(null);
    }

    private DocumentRecord resolveDocumentRecord(Long id) {
        return id == null ? null : documentRecordRepository.findById(id).orElse(null);
    }

    private CustodySession resolveSession(Long id) {
        return id == null ? null : custodySessionRepository.findById(id).orElse(null);
    }

    private CustodyEventResponse toResponse(CustodyEvent event) {
        return new CustodyEventResponse(
            event.getId(), event.getEventType(), event.getEventTimestamp(), event.getActorIdentifier(),
            event.getFromHolder() == null ? null : event.getFromHolder().getActorIdentifier(),
            event.getToHolder() == null ? null : event.getToHolder().getActorIdentifier(),
            event.getArchiveBox() == null ? null : event.getArchiveBox().getId(),
            event.getArchiveFolder() == null ? null : event.getArchiveFolder().getId(),
            event.getDocumentRecord() == null ? null : event.getDocumentRecord().getId(),
            event.getCustodySession() == null ? null : event.getCustodySession().getId());
    }
}
