/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WorkflowDefinitionRepository
 *	@CreatedOn	: 05-15-2026
 *	@Updated	: 05-15-2026
 *
 *	@Type		: Repository
 *	@Layer		: Data Access
 *	@Package	: Business / Workflow / Repository
 *
 **/

package dz.procsys.api.core.business.workflow.repository;

import dz.procsys.api.core.business.workflow.model.WorkflowDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowDefinitionRepository extends JpaRepository<WorkflowDefinition, Long> {

    Optional<WorkflowDefinition> findByCodeAndVersion(String code, String version);

    List<WorkflowDefinition> findByBusinessContextAndActiveTrue(String businessContext);

    List<WorkflowDefinition> findByActiveTrue();

    boolean existsByCodeAndVersion(String code, String version);
}
