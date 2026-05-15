/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowInstanceController
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Controller
 *	@Layer		: API / Presentation
 *	@Package	: Business / Workflow / Controller
 *
 **/

package dz.procsys.api.core.business.workflow.controller;

import dz.procsys.api.core.business.workflow.dto.WorkflowActionRequest;
import dz.procsys.api.core.business.workflow.dto.WorkflowInstanceDTO;
import dz.procsys.api.core.business.workflow.service.WorkflowInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workflow/instances")
@RequiredArgsConstructor
public class WorkflowInstanceController {

    private final WorkflowInstanceService workflowInstanceService;

    /**
     * Start a new workflow instance.
     * POST /api/workflow/instances/start
     * Body params: definitionId, businessContext, businessKey, startedBy
     */
    @PostMapping("/start")
    public ResponseEntity<WorkflowInstanceDTO> start(
            @RequestParam Long definitionId,
            @RequestParam String businessContext,
            @RequestParam String businessKey,
            @RequestParam String startedBy) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(workflowInstanceService.startWorkflow(
                definitionId, businessContext, businessKey, startedBy));
    }

    /**
     * Execute an action on a running instance.
     * POST /api/workflow/instances/{id}/action
     */
    @PostMapping("/{id}/action")
    public ResponseEntity<WorkflowInstanceDTO> executeAction(
            @PathVariable Long id,
            @RequestBody WorkflowActionRequest request) {
        return ResponseEntity.ok(workflowInstanceService.executeAction(id, request));
    }

    /**
     * Cancel a running instance.
     * POST /api/workflow/instances/{id}/cancel
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<WorkflowInstanceDTO> cancel(
            @PathVariable Long id,
            @RequestParam String actorUsername,
            @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(
            workflowInstanceService.cancelWorkflow(id, actorUsername, reason));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowInstanceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowInstanceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<WorkflowInstanceDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(workflowInstanceService.getAll(pageable));
    }

    @GetMapping("/by-status")
    public ResponseEntity<Page<WorkflowInstanceDTO>> getByStatus(
            @RequestParam String status,
            Pageable pageable) {
        return ResponseEntity.ok(workflowInstanceService.getByStatus(status, pageable));
    }

    @GetMapping("/by-context")
    public ResponseEntity<List<WorkflowInstanceDTO>> getByContextAndStatus(
            @RequestParam String businessContext,
            @RequestParam String status) {
        return ResponseEntity.ok(
            workflowInstanceService.getByContextAndStatus(businessContext, status));
    }

    @GetMapping("/lookup")
    public ResponseEntity<WorkflowInstanceDTO> findByContextAndKey(
            @RequestParam String businessContext,
            @RequestParam String businessKey) {
        return ResponseEntity.ok(
            workflowInstanceService.findByBusinessContextAndKey(businessContext, businessKey));
    }
}
