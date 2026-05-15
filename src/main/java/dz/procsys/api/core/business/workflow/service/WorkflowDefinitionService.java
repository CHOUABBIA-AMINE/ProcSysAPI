/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowDefinitionService
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Service
 *	@Layer		: Business / Workflow / Service
 *	@Package	: Business / Workflow / Service
 *
 **/

package dz.procsys.api.core.business.workflow.service;

import dz.procsys.api.core.business.workflow.dto.WorkflowDefinitionDTO;
import dz.procsys.api.core.business.workflow.model.WorkflowDefinition;
import dz.procsys.api.core.business.workflow.repository.WorkflowDefinitionRepository;
import dz.procsys.api.platform.kernel.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class WorkflowDefinitionService
        extends GenericService<WorkflowDefinition, WorkflowDefinitionDTO, Long> {

    private final WorkflowDefinitionRepository workflowDefinitionRepository;

    @Override
    protected JpaRepository<WorkflowDefinition, Long> getRepository() {
        return workflowDefinitionRepository;
    }

    @Override
    protected String getEntityName() {
        return "WorkflowDefinition";
    }

    @Override
    protected WorkflowDefinitionDTO toDTO(WorkflowDefinition entity) {
        return WorkflowDefinitionDTO.fromEntity(entity);
    }

    @Override
    protected WorkflowDefinition toEntity(WorkflowDefinitionDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(WorkflowDefinition entity, WorkflowDefinitionDTO dto) {
        dto.updateEntity(entity);
    }

    @Override
    @Transactional
    public WorkflowDefinitionDTO create(WorkflowDefinitionDTO dto) {
        log.info("Creating WorkflowDefinition: code={}, version={}", dto.getCode(), dto.getVersion());
        return super.create(dto);
    }

    @Override
    @Transactional
    public WorkflowDefinitionDTO update(Long id, WorkflowDefinitionDTO dto) {
        log.info("Updating WorkflowDefinition ID={}", id);
        return super.update(id, dto);
    }

    // ---- custom queries ---------------------------------------------------

    public List<WorkflowDefinitionDTO> getActiveByContext(String businessContext) {
        log.debug("Loading active WorkflowDefinitions for context={}", businessContext);
        return workflowDefinitionRepository
                .findByBusinessContextAndActiveTrue(businessContext)
                .stream()
                .map(WorkflowDefinitionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<WorkflowDefinitionDTO> getAllActive() {
        return workflowDefinitionRepository.findByActiveTrue()
                .stream()
                .map(WorkflowDefinitionDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
