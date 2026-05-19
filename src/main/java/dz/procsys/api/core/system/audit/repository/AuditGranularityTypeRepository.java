/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditGranularityTypeRepository
 * @CreatedOn   : 05-19-2026
 *
 * @Type        : Interface
 * @Layer       : Repository
 * @Package     : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditGranularityType;

/**
 * Repository for AuditGranularityType master data entity.
 */
@Repository
public interface AuditGranularityTypeRepository extends JpaRepository<AuditGranularityType, Long>, JpaSpecificationExecutor<AuditGranularityType> {

    /**
     * Finds a granularity type by its unique code.
     *
     * @param code the granularity code
     * @return Optional of AuditGranularityType
     */
    Optional<AuditGranularityType> findByCode(String code);

    /**
     * Checks if a granularity type with the given code exists.
     *
     * @param code the code
     * @return true if exists
     */
    boolean existsByCode(String code);
}