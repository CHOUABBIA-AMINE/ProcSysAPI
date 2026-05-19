/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActionRepository
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

import dz.procsys.api.core.system.audit.model.AuditAction;

/**
 * Repository for AuditAction entity.
 * Supports traceability queries by action type, status and outcome.
 */
@Repository
public interface AuditActionRepository extends JpaRepository<AuditAction, Long>, JpaSpecificationExecutor<AuditAction> {

    /**
     * Retrieves actions by action type code.
     *
     * @param actionTypeCode the action type code
     * @param pageable       pagination parameters
     * @return page of AuditAction
     */
    Page<AuditAction> findByActionTypeCode(String actionTypeCode, Pageable pageable);

    /**
     * Retrieves actions by execution status.
     *
     * @param status   the execution status (e.g. SUCCESS, FAILED, PARTIAL)
     * @param pageable pagination parameters
     * @return page of AuditAction
     */
    Page<AuditAction> findByStatus(String status, Pageable pageable);

    /**
     * Retrieves all actions whose outcome description contains the given keyword.
     *
     * @param keyword a substring to search in outcome descriptions
     * @return list of AuditAction
     */
    List<AuditAction> findByOutcomeDescriptionContainingIgnoreCase(String keyword);
}