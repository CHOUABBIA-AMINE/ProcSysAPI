/**
 *
 * @Author      : CHOUABBIA Amine
 *
 * @Name        : AuditActorRepository
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

import dz.procsys.api.core.system.audit.model.AuditActor;

/**
 * Repository for AuditActor entity.
 * Supports actor-based lookup for forensic reconstruction and actor timeline queries.
 */
@Repository
public interface AuditActorRepository extends JpaRepository<AuditActor, Long>, JpaSpecificationExecutor<AuditActor> {

    /**
     * Retrieves all actor snapshots by external actor reference ID.
     *
     * @param actorReferenceId the external actor reference ID
     * @return list of AuditActor snapshots
     */
    List<AuditActor> findByActorReferenceId(Long actorReferenceId);

    /**
     * Retrieves all actor snapshots by username.
     *
     * @param username the username
     * @return list of AuditActor snapshots
     */
    List<AuditActor> findByUsername(String username);

    /**
     * Retrieves all actor snapshots by session ID.
     *
     * @param sessionId the session identifier
     * @return list of AuditActor snapshots
     */
    List<AuditActor> findBySessionId(String sessionId);
}