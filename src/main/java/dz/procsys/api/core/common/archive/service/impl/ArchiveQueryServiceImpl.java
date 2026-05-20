/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveQueryServiceImpl
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Class
 *  @Layer      : Service / Implementation
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.common.archive.dto.request.ArchiveBoxSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.request.ArchiveFolderSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveBoxResponseDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveFolderResponseDTO;
import dz.procsys.api.core.common.archive.mapper.ArchiveBoxMapper;
import dz.procsys.api.core.common.archive.mapper.ArchiveFolderMapper;
import dz.procsys.api.core.common.archive.model.ArchiveBox;
import dz.procsys.api.core.common.archive.model.ArchiveFolder;
import dz.procsys.api.core.common.archive.repository.ArchiveBoxRepository;
import dz.procsys.api.core.common.archive.repository.ArchiveFolderRepository;
import dz.procsys.api.core.common.archive.service.ArchiveQueryService;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of ArchiveQueryService.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArchiveQueryServiceImpl implements ArchiveQueryService {

    private final ArchiveBoxRepository archiveBoxRepository;
    private final ArchiveFolderRepository archiveFolderRepository;
    private final ArchiveBoxMapper archiveBoxMapper;
    private final ArchiveFolderMapper archiveFolderMapper;

    @Override
    public Page<ArchiveBoxResponseDTO> searchArchiveBoxes(ArchiveBoxSearchRequestDTO request) {
        Pageable pageable = toPageable(request.getPage(), request.getSize());
        Page<ArchiveBox> page = archiveBoxRepository.findAll(pageable);
        return page.map(archiveBoxMapper::toDTO);
    }

    @Override
    public Page<ArchiveFolderResponseDTO> searchArchiveFolders(ArchiveFolderSearchRequestDTO request) {
        Pageable pageable = toPageable(request.getPage(), request.getSize());
        Page<ArchiveFolder> page = archiveFolderRepository.findAll(pageable);
        return page.map(archiveFolderMapper::toDTO);
    }

    private Pageable toPageable(Integer page, Integer size) {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 20 : size;
        return PageRequest.of(p, s);
    }
}