package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.dto.request.AssignRetentionPolicyRequest;
import dz.procsys.api.core.procurement.archive.dto.request.DispositionDecisionRequest;
import dz.procsys.api.core.procurement.archive.dto.request.LegalHoldRequest;
import dz.procsys.api.core.procurement.archive.dto.response.RetentionAssignmentResponse;
import dz.procsys.api.core.procurement.archive.model.RetentionClass;
import dz.procsys.api.core.procurement.archive.service.RetentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procurement/archive/retention")
@RequiredArgsConstructor
public class RetentionController {
    private final RetentionService retentionService;

    @PostMapping("/assignments")
    public RetentionAssignmentResponse assignPolicy(@RequestBody AssignRetentionPolicyRequest request) { return retentionService.assignPolicy(request); }

    @PostMapping("/assignments/{id}/calculate-eligibility")
    public RetentionAssignmentResponse calculate(@PathVariable Long id) { return retentionService.calculateEligibility(id); }

    @PostMapping("/holds/place")
    public void placeHold(@RequestBody LegalHoldRequest request) { retentionService.placeHold(request); }

    @PostMapping("/holds/remove")
    public void removeHold(@RequestBody LegalHoldRequest request) { retentionService.removeHold(request); }

    @PostMapping("/disposition")
    public void disposition(@RequestBody DispositionDecisionRequest request) { retentionService.dispositionDecision(request); }

    @GetMapping("/by-class/{retentionClass}")
    public List<RetentionAssignmentResponse> byClass(@PathVariable RetentionClass retentionClass) { return retentionService.findByRetentionClass(retentionClass); }

    @GetMapping("/due-for-review")
    public List<RetentionAssignmentResponse> dueForReview() { return retentionService.dueForReview(); }

    @GetMapping("/by-hold-status")
    public List<RetentionAssignmentResponse> byHoldStatus(@RequestParam boolean active) { return retentionService.byHoldStatus(active); }
}
