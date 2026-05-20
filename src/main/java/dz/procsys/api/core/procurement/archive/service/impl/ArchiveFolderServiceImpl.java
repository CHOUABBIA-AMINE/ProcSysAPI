package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequest;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.service.ArchiveFolderService;
import org.springframework.stereotype.Service;

@Service
public class ArchiveFolderServiceImpl implements ArchiveFolderService {

    @Override
    public ArchiveFolderResponse create(CreateArchiveFolderRequest request) {
        return new ArchiveFolderResponse(null, request.code(), request.description(), request.archiveBoxId());
    }
}
