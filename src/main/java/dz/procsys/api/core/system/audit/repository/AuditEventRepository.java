/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditEventRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditEvent;

/**
 * Repository for AuditEvent entity.
 * Provides pipeline processing queries: status-based, source-based, correlation-based,
 * and time-range queries for event ingestion monitoring.
 */
@Repository
public interface AuditEventRepository extends JpaRepository<AuditEvent, Long>, JpaSpecificationExecutor<AuditEvent> {

    /**
     * Retrieves events by processing status for pipeline monitoring.
     *
     * @param processingStatus the status: RECEIVED, PROCESSING, PROCESSED, FAILED
     * @param pageable         pagination parameters
     * @return page of AuditEvent
     */
    Page<AuditEvent> findByProcessingStatus(String processingStatus, Pageable pageable);

    /**
     * Retrieves events by source module.
     *
     * @param sourceModule the originating platform module
     * @param pageable     pagination parameters
     * @return page of AuditEvent
     */
    Page<AuditEvent> findBySourceModule(String sourceModule, Pageable pageable);

    /**
     * Retrieves events by correlation ID.
     *
     * @param correlationId the correlation ID
     * @param pageable      pagination parameters
     * @return page of AuditEvent
     */
    Page<AuditEvent> findByCorrelationId(String correlationId, Pageable pageable);

    /**
     * Retrieves all FAILED events within a time range for re-processing.
     *
     * @param from     start of the time range
     * @param to       end of the time range
     * @param pageable pagination parameters
     * @return page of failed AuditEvent
     */
    Page<AuditEvent> findByProcessingStatusAndReceivedAtBetween(String processingStatus, Date from, Date to, Pageable pageable);

    /**
     * Counts events by processing status for pipeline health monitoring.
     *
     * @param processingStatus the status code
     * @return count of events in that status
     */
    long countByProcessingStatus(String processingStatus);
}