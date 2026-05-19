/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditEventTypeRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditEventType;

/**
 * Repository for AuditEventType master data entity.
 */
@Repository
public interface AuditEventTypeRepository extends JpaRepository<AuditEventType, Long>, JpaSpecificationExecutor<AuditEventType> {

    /**
     * Finds an event type by its unique code.
     *
     * @param code the event type code
     * @return Optional of AuditEventType
     */
    Optional<AuditEventType> findByCode(String code);

    /**
     * Retrieves all active event types.
     *
     * @param isActive the active flag
     * @param pageable pagination parameters
     * @return page of AuditEventType
     */
    Page<AuditEventType> findByIsActive(Boolean isActive, Pageable pageable);

    /**
     * Checks if an event type with the given code exists.
     *
     * @param code the code
     * @return true if exists
     */
    boolean existsByCode(String code);
}