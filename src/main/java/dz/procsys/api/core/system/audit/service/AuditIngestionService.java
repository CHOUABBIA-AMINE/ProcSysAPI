/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditIngestionService
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Service
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service;

import dz.procsys.api.core.system.audit.model.AuditEvent;
import dz.procsys.api.core.system.audit.model.AuditRecord;

/**
 * Service responsible for ingesting domain events into the audit ledger.
 * Implements the event-driven pipeline:
 * Domain Event -> AuditEvent -> enrichment + diff + hash chaining -> AuditRecord.
 */
public interface AuditIngestionService {

    /**
     * Ingests a raw AuditEvent and produces an immutable AuditRecord.
     * This method never modifies business data and only appends to the audit ledger.
     *
     * @param auditEvent the raw inbound audit event
     * @return the persisted AuditRecord
     */
    AuditRecord ingestEvent(AuditEvent auditEvent);
}