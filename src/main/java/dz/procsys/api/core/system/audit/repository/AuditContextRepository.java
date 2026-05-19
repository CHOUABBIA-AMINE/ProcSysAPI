/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditContextRepository
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Repository
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditContext;

/**
 * Repository for AuditContext entity.
 * Supports technical traceability queries by correlation, request and node.
 */
@Repository
public interface AuditContextRepository extends JpaRepository<AuditContext, Long>, JpaSpecificationExecutor<AuditContext> {

    /**
     * Retrieves contexts by correlation ID.
     *
     * @param correlationId the correlation identifier
     * @param pageable      pagination parameters
     * @return page of AuditContext
     */
    Page<AuditContext> findByCorrelationId(String correlationId, Pageable pageable);

    /**
     * Retrieves contexts by HTTP request ID.
     *
     * @param requestId the HTTP request ID
     * @param pageable  pagination parameters
     * @return page of AuditContext
     */
    Page<AuditContext> findByRequestId(String requestId, Pageable pageable);

    /**
     * Retrieves contexts by service node name.
     *
     * @param serviceNode the service node
     * @param pageable    pagination parameters
     * @return page of AuditContext
     */
    Page<AuditContext> findByServiceNode(String serviceNode, Pageable pageable);

    /**
     * Lists all distinct correlation IDs seen for a given service node.
     *
     * @param serviceNode the service node
     * @return list of correlation IDs
     */
    List<String> findDistinctCorrelationIdByServiceNode(String serviceNode);
}