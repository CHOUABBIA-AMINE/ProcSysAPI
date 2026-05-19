/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditActionTypeRepository
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

import dz.procsys.api.core.system.audit.model.AuditActionType;

/**
 * Repository for AuditActionType master data entity.
 */
@Repository
public interface AuditActionTypeRepository extends JpaRepository<AuditActionType, Long>, JpaSpecificationExecutor<AuditActionType> {

    /**
     * Finds an action type by its unique code.
     *
     * @param code the action type code
     * @return Optional of AuditActionType
     */
    Optional<AuditActionType> findByCode(String code);

    /**
     * Retrieves all active action types.
     *
     * @param isActive the active flag
     * @param pageable pagination parameters
     * @return page of AuditActionType
     */
    Page<AuditActionType> findByIsActive(Boolean isActive, Pageable pageable);

    /**
     * Checks if an action type with the given code exists.
     *
     * @param code the code
     * @return true if exists
     */
    boolean existsByCode(String code);
}