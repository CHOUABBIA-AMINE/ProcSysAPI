/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveQueryServiceImpl
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : Service / Implementation
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service.impl;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.audit.dto.request.AuditArchiveSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveSummaryResponseDTO;
import dz.procsys.api.core.system.audit.mapper.AuditArchiveMapper;
import dz.procsys.api.core.system.audit.model.AuditArchive;
import dz.procsys.api.core.system.audit.repository.AuditArchiveRepository;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of AuditArchiveQueryService.
 * Provides read-only access to archived audit batches.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditArchiveQueryServiceImpl implements dz.procsys.api.core.system.audit.service.AuditArchiveQueryService {

    private final AuditArchiveRepository auditArchiveRepository;
    private final AuditArchiveMapper auditArchiveMapper;

    @Override
    public Page<AuditArchiveSummaryResponseDTO> searchArchives(AuditArchiveSearchRequestDTO request) {
        Pageable pageable = createPageable(request.getPage(), request.getSize());

        if (Objects.nonNull(request.getFromCoverageDate()) && Objects.nonNull(request.getToCoverageDate())) {
            Date from = request.getFromCoverageDate();
            Date to = request.getToCoverageDate();
            Page<AuditArchive> page = auditArchiveRepository
                .findByCoverageStartDateGreaterThanEqualAndCoverageEndDateLessThanEqual(from, to, pageable);
            return page.map(auditArchiveMapper::toSummaryDTO);
        }

        if (Objects.nonNull(request.getStatus()) && !request.getStatus().isBlank()) {
            Page<AuditArchive> page = auditArchiveRepository.findByStatus(request.getStatus(), pageable);
            return page.map(auditArchiveMapper::toSummaryDTO);
        }

        Page<AuditArchive> page = auditArchiveRepository.findAll(pageable);
        return page.map(auditArchiveMapper::toSummaryDTO);
    }

    @Override
    public AuditArchiveDetailResponseDTO getArchiveDetail(Long archiveId) {
        AuditArchive archive = auditArchiveRepository.findById(archiveId)
            .orElseThrow(() -> new IllegalArgumentException("AuditArchive not found for id: " + archiveId));
        return auditArchiveMapper.toDetailDTO(archive);
    }

    private Pageable createPageable(Integer page, Integer size) {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 20 : size;
        return PageRequest.of(p, s);
    }
}