package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequest;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.mapper.ArchiveFolderMapper;
import dz.procsys.api.core.procurement.archive.model.ArchiveBox;
import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import dz.procsys.api.core.procurement.archive.repository.ArchiveBoxRepository;
import dz.procsys.api.core.procurement.archive.repository.ArchiveFolderRepository;
import dz.procsys.api.core.procurement.archive.service.ArchiveFolderService;
import dz.procsys.api.exception.base.ErrorCode;
import dz.procsys.api.exception.business.BusinessException;
import dz.procsys.api.exception.business.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArchiveFolderServiceImpl implements ArchiveFolderService {

    private final ArchiveFolderRepository archiveFolderRepository;
    private final ArchiveBoxRepository archiveBoxRepository;

    @Override
    @Transactional
    public ArchiveFolderResponse create(CreateArchiveFolderRequest request) {
        if (archiveFolderRepository.existsByCode(request.code())) {
            throw new BusinessException(ErrorCode.BUS_DUPLICATE_RESOURCE,
                "Archive folder code already exists: " + request.code(),
                HttpStatus.CONFLICT);
        }

        ArchiveBox archiveBox = archiveBoxRepository.findById(request.archiveBoxId())
            .orElseThrow(() -> new ResourceNotFoundException("ArchiveBox", request.archiveBoxId()));

        ArchiveFolder archiveFolder = new ArchiveFolder();
        archiveFolder.setCode(request.code());
        archiveFolder.setDescription(request.description());
        archiveFolder.setArchiveBox(archiveBox);

        ArchiveFolder savedArchiveFolder = archiveFolderRepository.save(archiveFolder);
        return ArchiveFolderMapper.toResponse(savedArchiveFolder);
    }
}
