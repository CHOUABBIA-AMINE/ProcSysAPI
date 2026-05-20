package dz.procsys.api.core.procurement.archive.dto.response;

import dz.procsys.api.core.procurement.archive.model.RetentionClass;
import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleState;
import java.time.LocalDate;

public record ArchiveFolderDetailsResponseDTO(
    Long id,
    String code,
    String description,
    Long archiveBoxId,
    String archiveBoxCode,
    String shelfCode,
    String roomCode,
    String facilityCode,
    String currentCustodyHolder,
    Long retentionAssignmentId,
    RetentionClass retentionClass,
    RetentionLifecycleState retentionLifecycleState,
    LocalDate disposalEligibilityDate
) {
}
