/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditRecordRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditRecord;

/**
 * Repository for AuditRecord entity.
 * Provides forensic query operations: correlation-based, actor-based, resource-based,
 * time-range, and paginated access. Supports Specification-based dynamic filtering.
 */
@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Long>, JpaSpecificationExecutor<AuditRecord> {

    /**
     * Retrieves all audit records associated with a given correlation ID.
     *
     * @param correlationId the correlation identifier
     * @param pageable      pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByCorrelationId(String correlationId, Pageable pageable);

    /**
     * Retrieves all audit records for a given actor reference ID (forensic actor timeline).
     *
     * @param actorReferenceId the external actor reference ID
     * @param pageable         pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByActorActorReferenceId(Long actorReferenceId, Pageable pageable);

    /**
     * Retrieves all audit records for a given resource reference ID and resource module.
     *
     * @param resourceReferenceId the external resource reference ID
     * @param module              the platform module owning the resource
     * @param pageable            pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByResourceResourceReferenceIdAndResourceModule(Long resourceReferenceId, String module, Pageable pageable);

    /**
     * Retrieves all audit records within a given time range.
     *
     * @param from     start of the time range (inclusive)
     * @param to       end of the time range (inclusive)
     * @param pageable pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByEventTimestampBetween(Date from, Date to, Pageable pageable);

    /**
     * Retrieves all audit records belonging to a given audit trail.
     *
     * @param trailId  ID of the AuditTrail
     * @param pageable pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByAuditTrailId(Long trailId, Pageable pageable);

    /**
     * Finds the most recent audit record per correlation ID for hash chain verification.
     *
     * @param correlationId the correlation identifier
     * @return Optional containing the latest AuditRecord in the chain
     */
    Optional<AuditRecord> findTopByCorrelationIdOrderByEventTimestampDesc(String correlationId);

    /**
     * Retrieves all audit records for a given resource type code and time range.
     *
     * @param resourceTypeCode code of the AuditResourceType
     * @param from             start of the time range
     * @param to               end of the time range
     * @param pageable         pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByResourceResourceTypeCodeAndEventTimestampBetween(String resourceTypeCode, Date from, Date to, Pageable pageable);

    /**
     * Retrieves audit records by action type code.
     *
     * @param actionTypeCode code of the AuditActionType
     * @param pageable       pagination parameters
     * @return page of AuditRecord
     */
    Page<AuditRecord> findByActionActionTypeCode(String actionTypeCode, Pageable pageable);

    /**
     * Loads an AuditRecord with full actor, resource, action, context, and diffs eagerly.
     *
     * @param id the record ID
     * @return Optional containing the fully loaded AuditRecord
     */
    @EntityGraph(attributePaths = {"actor", "resource", "resource.resourceType", "action", "action.actionType", "context", "granularityType", "diffs"})
    Optional<AuditRecord> findWithFullDetailsById(Long id);

    /**
     * Lists all correlation IDs for a given actor reference ID (for forensic timeline reconstruction).
     *
     * @param actorReferenceId the actor reference ID
     * @return list of distinct correlation IDs
     */
    List<String> findDistinctCorrelationIdByActorActorReferenceId(Long actorReferenceId);

    /**
     * Checks if any audit record exists for a given correlation ID.
     *
     * @param correlationId the correlation identifier
     * @return true if at least one record exists
     */
    boolean existsByCorrelationId(String correlationId);
}