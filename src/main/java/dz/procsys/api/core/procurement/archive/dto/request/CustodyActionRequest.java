package dz.procsys.api.core.procurement.archive.dto.request;

public record CustodyActionRequest(
    String actorIdentifier,
    String fromActorIdentifier,
    String toActorIdentifier,
    Long archiveBoxId,
    Long archiveFolderId,
    Long documentRecordId,
    Long custodySessionId
) {
}
