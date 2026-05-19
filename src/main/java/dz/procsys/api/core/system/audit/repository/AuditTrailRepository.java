/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditTrailRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditTrail;

/**
 * Repository for AuditTrail entity.
 * Provides trail-based forensic reconstruction queries.
 */
@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long>, JpaSpecificationExecutor<AuditTrail> {

    /**
     * Finds a trail by its unique trail code.
     *
     * @param trailCode the unique trail code
     * @return Optional containing the AuditTrail
     */
    Optional<AuditTrail> findByTrailCode(String trailCode);

    /**
     * Retrieves all trails for a given correlation ID.
     *
     * @param correlationId the correlation identifier
     * @param pageable      pagination parameters
     * @return page of AuditTrail
     */
    Page<AuditTrail> findByCorrelationId(String correlationId, Pageable pageable);

    /**
     * Retrieves all trails for a given platform module.
     *
     * @param module   the platform module code
     * @param pageable pagination parameters
     * @return page of AuditTrail
     */
    Page<AuditTrail> findByModule(String module, Pageable pageable);

    /**
     * Retrieves all trails by status (OPEN, SEALED, ARCHIVED).
     *
     * @param status   the trail status
     * @param pageable pagination parameters
     * @return page of AuditTrail
     */
    Page<AuditTrail> findByStatus(String status, Pageable pageable);

    /**
     * Retrieves all OPEN trails opened within a time range.
     *
     * @param from     start of range
     * @param to       end of range
     * @param pageable pagination parameters
     * @return page of AuditTrail
     */
    Page<AuditTrail> findByStatusAndOpenedAtBetween(String status, Date from, Date to, Pageable pageable);

    /**
     * Loads an AuditTrail with its records eagerly for full forensic reconstruction.
     *
     * @param trailCode the unique trail code
     * @return Optional of fully loaded AuditTrail
     */
    @EntityGraph(attributePaths = {"records", "records.actor", "records.resource", "records.action", "records.diffs"})
    Optional<AuditTrail> findWithRecordsByTrailCode(String trailCode);

    /**
     * Checks if a trail exists with a given code.
     *
     * @param trailCode the trail code
     * @return true if exists
     */
    boolean existsByTrailCode(String trailCode);
}