package dz.procsys.api.core.procurement.archive.repository.projection;

import dz.procsys.api.core.procurement.archive.model.CustodyEventType;

import java.time.Instant;

public interface CustodyEventSearchView {
    Long getId();
    CustodyEventType getEventType();
    Instant getEventTimestamp();
    String getActorIdentifier();
    Long getArchiveBoxId();
    Long getArchiveFolderId();
    Long getDocumentRecordId();
    Long getCustodySessionId();
}
