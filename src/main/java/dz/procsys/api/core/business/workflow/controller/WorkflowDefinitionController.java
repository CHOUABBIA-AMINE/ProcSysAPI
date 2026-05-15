/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowDefinitionController
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Controller
 *	@Layer		: API / Presentation
 *	@Package	: Business / Workflow / Controller
 *
 **/

package dz.procsys.api.core.business.workflow.controller;

import dz.procsys.api.core.business.workflow.dto.WorkflowDefinitionDTO;
import dz.procsys.api.core.business.workflow.service.WorkflowDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workflow/definitions")
@RequiredArgsConstructor
public class WorkflowDefinitionController {

    private final WorkflowDefinitionService workflowDefinitionService;

    @PostMapping
    public ResponseEntity<WorkflowDefinitionDTO> create(
            @RequestBody WorkflowDefinitionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(workflowDefinitionService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowDefinitionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowDefinitionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<WorkflowDefinitionDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(workflowDefinitionService.getAll(pageable));
    }

    @GetMapping("/active")
    public ResponseEntity<List<WorkflowDefinitionDTO>> getAllActive() {
        return ResponseEntity.ok(workflowDefinitionService.getAllActive());
    }

    @GetMapping("/active/context")
    public ResponseEntity<List<WorkflowDefinitionDTO>> getActiveByContext(
            @RequestParam String businessContext) {
        return ResponseEntity.ok(workflowDefinitionService.getActiveByContext(businessContext));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowDefinitionDTO> update(
            @PathVariable Long id,
            @RequestBody WorkflowDefinitionDTO dto) {
        return ResponseEntity.ok(workflowDefinitionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workflowDefinitionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
