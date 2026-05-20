package dz.procsys.api.core.procurement.archive.dto.request;

public record CreateArchiveFolderRequest(String code, String description, Long archiveBoxId) {
}
