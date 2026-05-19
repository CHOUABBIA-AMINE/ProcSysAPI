/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditResourceTypeRepository
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

import dz.procsys.api.core.system.audit.model.AuditResourceType;

/**
 * Repository for AuditResourceType master data entity.
 */
@Repository
public interface AuditResourceTypeRepository extends JpaRepository<AuditResourceType, Long>, JpaSpecificationExecutor<AuditResourceType> {

    /**
     * Finds a resource type by its unique code.
     *
     * @param code the code
     * @return Optional of AuditResourceType
     */
    Optional<AuditResourceType> findByCode(String code);

    /**
     * Retrieves all resource types for a given module.
     *
     * @param module   the platform module
     * @param pageable pagination parameters
     * @return page of AuditResourceType
     */
    Page<AuditResourceType> findByModule(String module, Pageable pageable);

    /**
     * Retrieves all active resource types.
     *
     * @param isActive the active flag
     * @param pageable pagination parameters
     * @return page of AuditResourceType
     */
    Page<AuditResourceType> findByIsActive(Boolean isActive, Pageable pageable);

    /**
     * Checks if a resource type with the given code exists.
     *
     * @param code the code
     * @return true if exists
     */
    boolean existsByCode(String code);
}