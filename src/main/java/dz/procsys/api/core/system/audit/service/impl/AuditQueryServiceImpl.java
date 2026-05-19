/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditQueryServiceImpl
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : Service / Implementation
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service.impl;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.audit.dto.request.AuditActorTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditCorrelationRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditResourceTimelineRequestDTO;
import dz.procsys.api.core.system.audit.dto.request.AuditSearchRequestDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordSummaryResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditTimelineItemResponseDTO;
import dz.procsys.api.core.system.audit.mapper.AuditRecordMapper;
import dz.procsys.api.core.system.audit.model.AuditRecord;
import dz.procsys.api.core.system.audit.repository.AuditRecordRepository;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of AuditQueryService.
 * Performs read-only queries against the immutable audit ledger.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditQueryServiceImpl implements dz.procsys.api.core.system.audit.service.AuditQueryService {

    private final AuditRecordRepository auditRecordRepository;
    private final AuditRecordMapper auditRecordMapper;

    @Override
    public Page<AuditRecordSummaryResponseDTO> searchAuditRecords(AuditSearchRequestDTO request) {
        Pageable pageable = createPageable(request.getPage(), request.getSize());

        // For now, use repository methods to cover common filters.
        // More complex combinations can be implemented with JPA Specifications
        // while keeping this service read-only and side-effect-free.

        if (Objects.nonNull(request.getCorrelationId()) && !request.getCorrelationId().isBlank()) {
            Page<AuditRecord> page = auditRecordRepository.findByCorrelationId(request.getCorrelationId(), pageable);
            return page.map(auditRecordMapper::toSummaryDTO);
        }

        if (Objects.nonNull(request.getActorReferenceId())) {
            Page<AuditRecord> page = auditRecordRepository.findByActorActorReferenceId(request.getActorReferenceId(), pageable);
            return page.map(auditRecordMapper::toSummaryDTO);
        }

        if (Objects.nonNull(request.getResourceTypeCode())
            && Objects.nonNull(request.getFromTimestamp())
            && Objects.nonNull(request.getToTimestamp())) {
            Page<AuditRecord> page = auditRecordRepository.findByResourceResourceTypeCodeAndEventTimestampBetween(
                request.getResourceTypeCode(),
                request.getFromTimestamp(),
                request.getToTimestamp(),
                pageable
            );
            return page.map(auditRecordMapper::toSummaryDTO);
        }

        if (Objects.nonNull(request.getFromTimestamp()) && Objects.nonNull(request.getToTimestamp())) {
            Page<AuditRecord> page = auditRecordRepository.findByEventTimestampBetween(
                request.getFromTimestamp(),
                request.getToTimestamp(),
                pageable
            );
            return page.map(auditRecordMapper::toSummaryDTO);
        }

        // Default: return all records paged, mapped to summary DTOs.
        Page<AuditRecord> page = auditRecordRepository.findAll(pageable);
        return page.map(auditRecordMapper::toSummaryDTO);
    }

    @Override
    public Page<AuditTimelineItemResponseDTO> getActorTimeline(AuditActorTimelineRequestDTO request) {
        Pageable pageable = createPageable(request.getPage(), request.getSize());
        Page<AuditRecord> page = auditRecordRepository.findByActorActorReferenceId(
            request.getActorReferenceId(),
            pageable
        );
        return page.map(auditRecordMapper::toTimelineItemDTO);
    }

    @Override
    public Page<AuditTimelineItemResponseDTO> getResourceTimeline(AuditResourceTimelineRequestDTO request) {
        Pageable pageable = createPageable(request.getPage(), request.getSize());
        Page<AuditRecord> page = auditRecordRepository.findByResourceResourceReferenceIdAndResourceModule(
            request.getResourceReferenceId(),
            request.getModule(),
            pageable
        );
        return page.map(auditRecordMapper::toTimelineItemDTO);
    }

    @Override
    public Page<AuditRecordSummaryResponseDTO> getByCorrelationId(AuditCorrelationRequestDTO request) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE); // correlation view returns complete chain
        Page<AuditRecord> page = auditRecordRepository.findByCorrelationId(
            request.getCorrelationId(),
            pageable
        );
        return new PageImpl<>(page.map(auditRecordMapper::toSummaryDTO).getContent());
    }

    @Override
    public AuditRecordDetailResponseDTO getRecordDetail(Long recordId) {
        AuditRecord record = auditRecordRepository.findWithFullDetailsById(recordId)
            .orElseThrow(() -> new IllegalArgumentException("AuditRecord not found for id: " + recordId));
        return auditRecordMapper.toDetailDTO(record);
    }

    private Pageable createPageable(Integer page, Integer size) {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 20 : size;
        return PageRequest.of(p, s);
    }
}