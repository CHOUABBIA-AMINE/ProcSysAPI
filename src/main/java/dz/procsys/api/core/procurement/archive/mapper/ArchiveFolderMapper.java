package dz.procsys.api.core.procurement.archive.mapper;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.request.UpdateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderDetailsResponseDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import dz.procsys.api.core.procurement.archive.model.CustodyEvent;
import dz.procsys.api.core.procurement.archive.model.RetentionAssignment;
import java.util.Optional;

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

    public static void applyCreate(CreateArchiveFolderRequestDTO request, ArchiveFolder archiveFolder) {
        archiveFolder.setCode(request.code());
        archiveFolder.setDescription(request.description());
    }

    public static void applyUpdate(UpdateArchiveFolderRequestDTO request, ArchiveFolder archiveFolder) {
        archiveFolder.setCode(request.code());
        archiveFolder.setDescription(request.description());
    }

    public static ArchiveFolderDetailsResponseDTO toDetailsResponse(ArchiveFolder archiveFolder,
                                                                     Optional<CustodyEvent> latestCustodyEvent,
                                                                     Optional<RetentionAssignment> retentionAssignment) {
        return new ArchiveFolderDetailsResponseDTO(
            archiveFolder.getId(),
            archiveFolder.getCode(),
            archiveFolder.getDescription(),
            archiveFolder.getArchiveBox() == null ? null : archiveFolder.getArchiveBox().getId(),
            archiveFolder.getArchiveBox() == null ? null : archiveFolder.getArchiveBox().getCode(),
            archiveFolder.getArchiveBox() == null || archiveFolder.getArchiveBox().getShelf() == null ? null : archiveFolder.getArchiveBox().getShelf().getCode(),
            archiveFolder.getArchiveBox() == null || archiveFolder.getArchiveBox().getShelf() == null || archiveFolder.getArchiveBox().getShelf().getRoom() == null ? null : archiveFolder.getArchiveBox().getShelf().getRoom().getCode(),
            archiveFolder.getArchiveBox() == null || archiveFolder.getArchiveBox().getShelf() == null || archiveFolder.getArchiveBox().getShelf().getRoom() == null || archiveFolder.getArchiveBox().getShelf().getRoom().getFacility() == null ? null : archiveFolder.getArchiveBox().getShelf().getRoom().getFacility().getCode(),
            latestCustodyEvent.map(CustodyEvent::getActorIdentifier).orElse(null),
            retentionAssignment.map(RetentionAssignment::getId).orElse(null),
            retentionAssignment.map(a -> a.getRetentionPolicy() == null ? null : a.getRetentionPolicy().getRetentionClass()).orElse(null),
            retentionAssignment.map(RetentionAssignment::getLifecycleState).orElse(null),
            retentionAssignment.map(RetentionAssignment::getDisposalEligibilityDate).orElse(null)
        );
    }
}
