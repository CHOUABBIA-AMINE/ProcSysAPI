package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.request.UpdateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderDetailsResponseDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArchiveFolderService {
    ArchiveFolderResponse create(CreateArchiveFolderRequestDTO request);
    ArchiveFolderResponse update(Long id, UpdateArchiveFolderRequestDTO request);
    ArchiveFolderDetailsResponseDTO findById(Long id);
    ArchiveFolderDetailsResponseDTO findByCode(String code);
    Page<ArchiveFolderDetailsResponseDTO> search(String code, String facilityCode, String roomCode, String shelfCode, String boxCode, Pageable pageable);
}
