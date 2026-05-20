package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.request.UpdateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderDetailsResponseDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.mapper.ArchiveFolderMapper;
import dz.procsys.api.core.procurement.archive.model.ArchiveBox;
import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import dz.procsys.api.core.procurement.archive.repository.ArchiveBoxRepository;
import dz.procsys.api.core.procurement.archive.repository.ArchiveFolderRepository;
import dz.procsys.api.core.procurement.archive.repository.CustodyEventRepository;
import dz.procsys.api.core.procurement.archive.repository.RetentionAssignmentRepository;
import dz.procsys.api.core.procurement.archive.service.ArchiveFolderService;
import dz.procsys.api.exception.base.ErrorCode;
import dz.procsys.api.exception.business.BusinessException;
import dz.procsys.api.exception.business.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArchiveFolderServiceImpl implements ArchiveFolderService {

    private final ArchiveFolderRepository archiveFolderRepository;
    private final ArchiveBoxRepository archiveBoxRepository;
    private final CustodyEventRepository custodyEventRepository;
    private final RetentionAssignmentRepository retentionAssignmentRepository;

    @Override
    @Transactional
    public ArchiveFolderResponse create(CreateArchiveFolderRequestDTO request) {
        if (archiveFolderRepository.existsByCode(request.code())) {
            throw new BusinessException(ErrorCode.BUS_DUPLICATE_RESOURCE, "Archive folder code already exists: " + request.code(), HttpStatus.CONFLICT);
        }
        ArchiveFolder archiveFolder = new ArchiveFolder();
        ArchiveFolderMapper.applyCreate(request, archiveFolder);
        archiveFolder.setArchiveBox(resolveArchiveBox(request.archiveBoxId()));
        return ArchiveFolderMapper.toResponse(archiveFolderRepository.save(archiveFolder));
    }

    @Override
    @Transactional
    public ArchiveFolderResponse update(Long id, UpdateArchiveFolderRequestDTO request) {
        ArchiveFolder archiveFolder = archiveFolderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ArchiveFolder", id));
        if (archiveFolderRepository.existsByCodeAndIdNot(request.code(), id)) {
            throw new BusinessException(ErrorCode.BUS_DUPLICATE_RESOURCE, "Archive folder code already exists: " + request.code(), HttpStatus.CONFLICT);
        }
        ArchiveFolderMapper.applyUpdate(request, archiveFolder);
        archiveFolder.setArchiveBox(resolveArchiveBox(request.archiveBoxId()));
        return ArchiveFolderMapper.toResponse(archiveFolderRepository.save(archiveFolder));
    }

    @Override
    @Transactional(readOnly = true)
    public ArchiveFolderDetailsResponseDTO findById(Long id) {
        return toDetails(archiveFolderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ArchiveFolder", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public ArchiveFolderDetailsResponseDTO findByCode(String code) {
        return toDetails(archiveFolderRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("ArchiveFolder", code)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArchiveFolderDetailsResponseDTO> search(String code, String facilityCode, String roomCode, String shelfCode, String boxCode, Pageable pageable) {
        return archiveFolderRepository.search(code, facilityCode, roomCode, shelfCode, boxCode, pageable).map(this::toDetails);
    }

    private ArchiveFolderDetailsResponseDTO toDetails(ArchiveFolder archiveFolder) {
        return ArchiveFolderMapper.toDetailsResponse(
            archiveFolder,
            custodyEventRepository.findTopByArchiveFolderIdOrderByEventTimestampDescIdDesc(archiveFolder.getId()),
            retentionAssignmentRepository.findTopByArchiveFolderIdOrderByIdDesc(archiveFolder.getId())
        );
    }

    private ArchiveBox resolveArchiveBox(Long archiveBoxId) {
        return archiveBoxRepository.findById(archiveBoxId).orElseThrow(() -> new ResourceNotFoundException("ArchiveBox", archiveBoxId));
    }
}
