package dz.procsys.api.core.procurement.archive.mapper;

import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;

public final class ArchiveFolderMapper {

    private ArchiveFolderMapper() {
    }

    public static ArchiveFolderResponse toResponse(ArchiveFolder archiveFolder) {
        return new ArchiveFolderResponse(
            archiveFolder.getId(),
            archiveFolder.getCode(),
            archiveFolder.getDescription(),
            archiveFolder.getArchiveBox() != null ? archiveFolder.getArchiveBox().getId() : null
        );
    }
}
