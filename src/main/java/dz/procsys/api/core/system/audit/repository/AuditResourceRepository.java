/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditResourceRepository
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

import dz.procsys.api.core.system.audit.model.AuditResource;

/**
 * Repository for AuditResource entity.
 * Supports resource-centric forensic queries across the audit ledger.
 */
@Repository
public interface AuditResourceRepository extends JpaRepository<AuditResource, Long>, JpaSpecificationExecutor<AuditResource> {

    /**
     * Retrieves all resource snapshots by external resource reference ID and module.
     *
     * @param resourceReferenceId the external resource reference ID
     * @param module              the owning platform module
     * @param pageable            pagination parameters
     * @return page of AuditResource
     */
    Page<AuditResource> findByResourceReferenceIdAndModule(Long resourceReferenceId, String module, Pageable pageable);

    /**
     * Retrieves snapshots by resource identifier.
     *
     * @param resourceIdentifier the human-readable identifier
     * @param pageable           pagination parameters
     * @return page of AuditResource
     */
    Page<AuditResource> findByResourceIdentifier(String resourceIdentifier, Pageable pageable);

    /**
     * Retrieves snapshots by resource type code.
     *
     * @param resourceTypeCode the resource type code
     * @param pageable         pagination parameters
     * @return page of AuditResource
     */
    Page<AuditResource> findByResourceTypeCode(String resourceTypeCode, Pageable pageable);

    /**
     * Lists all distinct resource identifiers for a given module.
     *
     * @param module the owning platform module
     * @return list of resource identifiers
     */
    List<String> findDistinctResourceIdentifierByModule(String module);
}