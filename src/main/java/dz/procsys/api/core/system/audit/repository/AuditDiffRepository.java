/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditDiffRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditDiff;

/**
 * Repository for AuditDiff entity.
 * Provides diff-level forensic queries for a specific AuditRecord.
 */
@Repository
public interface AuditDiffRepository extends JpaRepository<AuditDiff, Long>, JpaSpecificationExecutor<AuditDiff> {

    /**
     * Retrieves all diffs for a given audit record ID.
     *
     * @param auditRecordId the ID of the parent AuditRecord
     * @return list of AuditDiff
     */
    List<AuditDiff> findByAuditRecordId(Long auditRecordId);

    /**
     * Retrieves diffs by field name for a given audit record.
     *
     * @param auditRecordId the ID of the parent AuditRecord
     * @param fieldName     the field name
     * @return list of AuditDiff
     */
    List<AuditDiff> findByAuditRecordIdAndFieldName(Long auditRecordId, String fieldName);

    /**
     * Retrieves non-masked diffs for a given audit record.
     *
     * @param auditRecordId the ID of the parent AuditRecord
     * @param isMasked      the masking flag
     * @return list of AuditDiff
     */
    List<AuditDiff> findByAuditRecordIdAndIsMasked(Long auditRecordId, Boolean isMasked);
}