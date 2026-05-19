/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditRetentionPolicyRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditRetentionPolicy;

/**
 * Repository for AuditRetentionPolicy entity.
 * Supports compliance and archival management queries.
 */
@Repository
public interface AuditRetentionPolicyRepository extends JpaRepository<AuditRetentionPolicy, Long>, JpaSpecificationExecutor<AuditRetentionPolicy> {

    /**
     * Finds a retention policy by its unique code.
     *
     * @param code the policy code
     * @return Optional of AuditRetentionPolicy
     */
    Optional<AuditRetentionPolicy> findByCode(String code);

    /**
     * Retrieves all active retention policies.
     *
     * @param isActive the active flag
     * @return list of AuditRetentionPolicy
     */
    List<AuditRetentionPolicy> findByIsActive(Boolean isActive);

    /**
     * Retrieves all policies applicable to a given resource type code.
     *
     * @param resourceTypeCode the resource type code
     * @return list of AuditRetentionPolicy
     */
    List<AuditRetentionPolicy> findByResourceTypeCode(String resourceTypeCode);

    /**
     * Checks if a policy with the given code exists.
     *
     * @param code the code
     * @return true if exists
     */
    boolean existsByCode(String code);
}