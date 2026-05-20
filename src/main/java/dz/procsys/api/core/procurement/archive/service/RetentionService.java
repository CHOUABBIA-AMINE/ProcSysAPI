package dz.procsys.api.core.procurement.archive.service;

import dz.procsys.api.core.procurement.archive.dto.request.AssignRetentionPolicyRequest;
import dz.procsys.api.core.procurement.archive.dto.request.DispositionDecisionRequest;
import dz.procsys.api.core.procurement.archive.dto.request.LegalHoldRequest;
import dz.procsys.api.core.procurement.archive.dto.response.RetentionAssignmentResponse;
import dz.procsys.api.core.procurement.archive.model.RetentionClass;

import java.util.List;

public interface RetentionService {
    RetentionAssignmentResponse assignPolicy(AssignRetentionPolicyRequest request);
    RetentionAssignmentResponse calculateEligibility(Long retentionAssignmentId);
    boolean canDispose(Long retentionAssignmentId);
    void placeHold(LegalHoldRequest request);
    void removeHold(LegalHoldRequest request);
    void dispositionDecision(DispositionDecisionRequest request);
    List<RetentionAssignmentResponse> findByRetentionClass(RetentionClass retentionClass);
    List<RetentionAssignmentResponse> dueForReview();
    List<RetentionAssignmentResponse> byHoldStatus(boolean active);
}
