package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.AssignRetentionPolicyRequest;
import dz.procsys.api.core.procurement.archive.dto.request.DispositionDecisionRequest;
import dz.procsys.api.core.procurement.archive.dto.request.LegalHoldRequest;
import dz.procsys.api.core.procurement.archive.dto.response.RetentionAssignmentResponse;
import dz.procsys.api.core.procurement.archive.model.*;
import dz.procsys.api.core.procurement.archive.repository.*;
import dz.procsys.api.core.procurement.archive.service.RetentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetentionServiceImpl implements RetentionService {
    private final RetentionPolicyRepository retentionPolicyRepository;
    private final RetentionAssignmentRepository retentionAssignmentRepository;
    private final LegalHoldRepository legalHoldRepository;
    private final RetentionLifecycleEventRepository retentionLifecycleEventRepository;
    private final DocumentRecordRepository documentRecordRepository;
    private final ArchiveFolderRepository archiveFolderRepository;

    @Override
    public RetentionAssignmentResponse assignPolicy(AssignRetentionPolicyRequest request) {
        RetentionPolicy policy = retentionPolicyRepository.findById(request.retentionPolicyId()).orElseThrow();
        RetentionAssignment assignment = new RetentionAssignment();
        assignment.setRetentionPolicy(policy);
        assignment.setDocumentRecord(request.documentRecordId() == null ? null : documentRecordRepository.findById(request.documentRecordId()).orElse(null));
        assignment.setArchiveFolder(request.archiveFolderId() == null ? null : archiveFolderRepository.findById(request.archiveFolderId()).orElse(null));
        assignment.setStartTrigger(request.startTrigger());
        assignment.setStartDate(request.startDate());
        assignment.setRetentionPeriodDays(policy.getRetentionPeriodDays());
        assignment.setDisposalEligibilityDate(request.startDate().plusDays(policy.getRetentionPeriodDays()));
        assignment.setLifecycleState(RetentionLifecycleState.ACTIVE);
        RetentionAssignment saved = retentionAssignmentRepository.save(assignment);
        logEvent(saved, RetentionEventType.POLICY_ASSIGNED, "Policy assigned");
        return toResponse(saved);
    }

    @Override
    public RetentionAssignmentResponse calculateEligibility(Long retentionAssignmentId) {
        RetentionAssignment assignment = retentionAssignmentRepository.findById(retentionAssignmentId).orElseThrow();
        assignment.setDisposalEligibilityDate(assignment.getStartDate().plusDays(assignment.getRetentionPeriodDays()));
        assignment.setLifecycleState(LocalDate.now().isBefore(assignment.getDisposalEligibilityDate()) ? RetentionLifecycleState.ACTIVE : RetentionLifecycleState.DUE_FOR_REVIEW);
        if (!canDispose(assignment.getId())) assignment.setLifecycleState(RetentionLifecycleState.BLOCKED_BY_HOLD);
        return toResponse(retentionAssignmentRepository.save(assignment));
    }

    @Override
    public boolean canDispose(Long retentionAssignmentId) {
        RetentionAssignment assignment = retentionAssignmentRepository.findById(retentionAssignmentId).orElseThrow();
        if (assignment.getDocumentRecord() != null && legalHoldRepository.existsByActiveTrueAndDocumentRecordId(assignment.getDocumentRecord().getId())) return false;
        return assignment.getArchiveFolder() == null || !legalHoldRepository.existsByActiveTrueAndArchiveFolderId(assignment.getArchiveFolder().getId());
    }

    @Override
    public void placeHold(LegalHoldRequest request) {
        RetentionAssignment assignment = retentionAssignmentRepository.findById(request.retentionAssignmentId()).orElseThrow();
        legalHoldRepository.save(new LegalHold(assignment.getDocumentRecord(), assignment.getArchiveFolder(), true, LocalDate.now(), null, request.reason()));
        assignment.setLifecycleState(RetentionLifecycleState.BLOCKED_BY_HOLD);
        retentionAssignmentRepository.save(assignment);
        logEvent(assignment, RetentionEventType.HOLD_PLACED, request.reason());
    }

    @Override
    public void removeHold(LegalHoldRequest request) {
        RetentionAssignment assignment = retentionAssignmentRepository.findById(request.retentionAssignmentId()).orElseThrow();
        legalHoldRepository.findByActive(true).stream()
            .filter(h -> (assignment.getDocumentRecord() != null && h.getDocumentRecord() != null && h.getDocumentRecord().getId().equals(assignment.getDocumentRecord().getId()))
                || (assignment.getArchiveFolder() != null && h.getArchiveFolder() != null && h.getArchiveFolder().getId().equals(assignment.getArchiveFolder().getId())))
            .forEach(h -> { h.setActive(false); h.setHoldReleasedDate(LocalDate.now()); legalHoldRepository.save(h);});
        calculateEligibility(assignment.getId());
        logEvent(assignment, RetentionEventType.HOLD_REMOVED, request.reason());
    }

    @Override
    public void dispositionDecision(DispositionDecisionRequest request) {
        RetentionAssignment assignment = retentionAssignmentRepository.findById(request.retentionAssignmentId()).orElseThrow();
        assignment.setLifecycleState(request.approved() ? RetentionLifecycleState.DISPOSED : RetentionLifecycleState.DUE_FOR_REVIEW);
        retentionAssignmentRepository.save(assignment);
        logEvent(assignment, request.approved() ? RetentionEventType.DISPOSITION_APPROVED : RetentionEventType.DISPOSITION_REJECTED, request.note());
    }

    @Override
    public List<RetentionAssignmentResponse> findByRetentionClass(RetentionClass retentionClass) {
        return retentionAssignmentRepository.findByRetentionPolicyRetentionClass(retentionClass).stream().map(this::toResponse).toList();
    }

    @Override
    public List<RetentionAssignmentResponse> dueForReview() {
        return retentionAssignmentRepository.findByDisposalEligibilityDateLessThanEqualAndLifecycleStateIn(LocalDate.now(), List.of(RetentionLifecycleState.ACTIVE, RetentionLifecycleState.DUE_FOR_REVIEW))
            .stream().map(this::toResponse).toList();
    }

    @Override
    public List<RetentionAssignmentResponse> byHoldStatus(boolean active) {
        return legalHoldRepository.findByActive(active).stream().map(h -> retentionAssignmentRepository.findAll().stream()
                .filter(a -> (a.getDocumentRecord() != null && h.getDocumentRecord() != null && a.getDocumentRecord().getId().equals(h.getDocumentRecord().getId()))
                    || (a.getArchiveFolder() != null && h.getArchiveFolder() != null && a.getArchiveFolder().getId().equals(h.getArchiveFolder().getId())))
                .findFirst().orElse(null))
            .filter(a -> a != null).map(this::toResponse).toList();
    }

    private void logEvent(RetentionAssignment assignment, RetentionEventType eventType, String note) {
        retentionLifecycleEventRepository.save(new RetentionLifecycleEvent(assignment, eventType, Instant.now(), note));
    }

    private RetentionAssignmentResponse toResponse(RetentionAssignment assignment) {
        return new RetentionAssignmentResponse(assignment.getId(), assignment.getRetentionPolicy().getId(), assignment.getRetentionPolicy().getRetentionClass(),
            assignment.getDocumentRecord() == null ? null : assignment.getDocumentRecord().getId(),
            assignment.getArchiveFolder() == null ? null : assignment.getArchiveFolder().getId(),
            assignment.getStartDate(), assignment.getRetentionPeriodDays(), assignment.getDisposalEligibilityDate(), assignment.getLifecycleState());
    }
}
