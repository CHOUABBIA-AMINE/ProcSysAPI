/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcessEventController
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Controller
 *	@Layer		: API / Presentation
 *	@Package	: Business / Workflow / Controller
 *
 **/

package dz.procsys.api.core.business.workflow.controller;

import dz.procsys.api.core.business.workflow.dto.ProcessEventDTO;
import dz.procsys.api.core.business.workflow.service.ProcessEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workflow/events")
@RequiredArgsConstructor
public class ProcessEventController {

    private final ProcessEventService processEventService;

    /**
     * Retrieve chronological audit trail for a workflow instance.
     * GET /api/workflow/events/instance?workflowInstanceId=42
     */
    @GetMapping("/instance")
    public ResponseEntity<List<ProcessEventDTO>> getByInstance(
            @RequestParam Long workflowInstanceId) {
        return ResponseEntity.ok(processEventService.getEventsByInstance(workflowInstanceId));
    }

    /**
     * Full audit trail for a business aggregate.
     * GET /api/workflow/events/audit?businessContext=CONSULTATION&businessKey=42
     */
    @GetMapping("/audit")
    public ResponseEntity<List<ProcessEventDTO>> getAuditTrail(
            @RequestParam String businessContext,
            @RequestParam String businessKey) {
        return ResponseEntity.ok(processEventService.getAuditTrail(businessContext, businessKey));
    }
}
