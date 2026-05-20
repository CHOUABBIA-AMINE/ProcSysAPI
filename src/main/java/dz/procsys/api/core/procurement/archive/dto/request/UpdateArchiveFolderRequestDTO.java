package dz.procsys.api.core.procurement.archive.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateArchiveFolderRequestDTO(
    @NotBlank @Size(max = 50) String code,
    @Size(max = 200) String description,
    @NotNull Long archiveBoxId
) {
}
