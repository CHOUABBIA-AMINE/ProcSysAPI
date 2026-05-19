/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditArchiveRepository
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dz.procsys.api.core.system.audit.model.AuditArchive;

/**
 * Repository for AuditArchive entity.
 * Provides archive retrieval and compliance inspection queries.
 */
@Repository
public interface AuditArchiveRepository extends JpaRepository<AuditArchive, Long>, JpaSpecificationExecutor<AuditArchive> {

    /**
     * Finds an archive batch by its unique archive code.
     *
     * @param archiveCode the archive code
     * @return Optional of AuditArchive
     */
    Optional<AuditArchive> findByArchiveCode(String archiveCode);

    /**
     * Retrieves all archives by status.
     *
     * @param status   COMPLETED, FAILED, PENDING_VERIFICATION
     * @param pageable pagination parameters
     * @return page of AuditArchive
     */
    Page<AuditArchive> findByStatus(String status, Pageable pageable);

    /**
     * Retrieves all archives covering a time range.
     *
     * @param from     start coverage date
     * @param to       end coverage date
     * @param pageable pagination parameters
     * @return page of AuditArchive
     */
    Page<AuditArchive> findByCoverageStartDateGreaterThanEqualAndCoverageEndDateLessThanEqual(Date from, Date to, Pageable pageable);

    /**
     * Checks if an archive with the given code exists.
     *
     * @param archiveCode the archive code
     * @return true if exists
     */
    boolean existsByArchiveCode(String archiveCode);
}