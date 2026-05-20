/**
 *
 *  @author     : CHOUABBIA Amine
 *
 *  @Name       : ArchiveQueryService
 *  @CreatedOn  : 05-20-2026
 *  @Updated    : 05-20-2026
 *
 *  @Type       : Interface
 *  @Layer      : Service
 *  @Package    : Common / Archive
 *
 **/

package dz.procsys.api.core.common.archive.service;

import dz.procsys.api.core.common.archive.dto.request.ArchiveBoxSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.request.ArchiveFolderSearchRequestDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveBoxResponseDTO;
import dz.procsys.api.core.common.archive.dto.response.ArchiveFolderResponseDTO;
import org.springframework.data.domain.Page;

/**
 * Read-only query service for archive containers (boxes and folders).
 */
public interface ArchiveQueryService {

    Page<ArchiveBoxResponseDTO> searchArchiveBoxes(ArchiveBoxSearchRequestDTO request);

    Page<ArchiveFolderResponseDTO> searchArchiveFolders(ArchiveFolderSearchRequestDTO request);
}