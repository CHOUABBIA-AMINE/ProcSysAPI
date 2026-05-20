package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequest;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;

public interface ArchiveFolderService {
    ArchiveFolderResponse create(CreateArchiveFolderRequest request);
}
