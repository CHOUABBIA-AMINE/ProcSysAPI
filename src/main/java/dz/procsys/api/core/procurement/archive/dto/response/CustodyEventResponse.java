package dz.procsys.api.core.procurement.archive.dto.response;

import dz.procsys.api.core.procurement.archive.model.CustodyEventType;

import java.time.Instant;

public record CustodyEventResponse(
    Long id,
    CustodyEventType eventType,
    Instant eventTimestamp,
    String actorIdentifier,
    String fromActorIdentifier,
    String toActorIdentifier,
    Long archiveBoxId,
    Long archiveFolderId,
    Long documentRecordId,
    Long custodySessionId
) {
}
