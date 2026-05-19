/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditIngestionServiceImpl
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Class
 *  @Layer      : Service / Implementation
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.procsys.api.core.system.audit.model.AuditAction;
import dz.procsys.api.core.system.audit.model.AuditActor;
import dz.procsys.api.core.system.audit.model.AuditContext;
import dz.procsys.api.core.system.audit.model.AuditEvent;
import dz.procsys.api.core.system.audit.model.AuditGranularityType;
import dz.procsys.api.core.system.audit.model.AuditRecord;
import dz.procsys.api.core.system.audit.model.AuditResource;
import dz.procsys.api.core.system.audit.repository.AuditEventRepository;
import dz.procsys.api.core.system.audit.repository.AuditGranularityTypeRepository;
import dz.procsys.api.core.system.audit.repository.AuditRecordRepository;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of AuditIngestionService.
 * Performs context enrichment, diff computation and hash chaining,
 * then appends immutable AuditRecord entries into the audit ledger.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuditIngestionServiceImpl implements dz.procsys.api.core.system.audit.service.AuditIngestionService {

    private final AuditEventRepository auditEventRepository;
    private final AuditRecordRepository auditRecordRepository;
    private final AuditGranularityTypeRepository auditGranularityTypeRepository;

    @Override
    public AuditRecord ingestEvent(AuditEvent auditEvent) {
        // Persist inbound event for traceability of event ingestion itself.
        AuditEvent persistedEvent = auditEventRepository.save(auditEvent);

        // In a full implementation, enrichment, diff computation and mapping from rawPayload
        // would be driven by event type and resource type configuration.
        // Here we construct a simplified, but structurally correct AuditRecord.

        AuditActor actor = buildActorSnapshotFromEvent(persistedEvent);
        AuditResource resource = buildResourceSnapshotFromEvent(persistedEvent);
        AuditAction action = buildActionFromEvent(persistedEvent);
        AuditContext context = buildContextFromEvent(persistedEvent);

        AuditGranularityType granularityType = auditGranularityTypeRepository
            .findByCode("FIELD_LEVEL")
            .orElseThrow(() -> new IllegalStateException("Granularity type FIELD_LEVEL is not configured"));

        AuditRecord record = AuditRecord.builder()
            .correlationId(persistedEvent.getCorrelationId())
            .eventTimestamp(new Date())
            .beforeState(null)        // filled by domain-specific enrichment in a real pipeline
            .afterState(persistedEvent.getRawPayload())
            .hashChainValue("")       // temporary, will be replaced after computing chain
            .previousHashValue(null)
            .actor(actor)
            .resource(resource)
            .action(action)
            .context(context)
            .auditTrail(null)
            .granularityType(granularityType)
            .diffs(null)              // populated by diff computation in full implementation
            .build();

        // Compute hash chain: retrieve latest record for same correlation, compute new hash.
        String previousHash = auditRecordRepository
            .findTopByCorrelationIdOrderByEventTimestampDesc(persistedEvent.getCorrelationId())
            .map(AuditRecord::getHashChainValue)
            .orElse(null);

        record.setPreviousHashValue(previousHash);
        record.setHashChainValue(computeHashChainValue(previousHash, record));

        AuditRecord persistedRecord = auditRecordRepository.save(record);

        persistedEvent.setProcessingStatus("PROCESSED");
        persistedEvent.setPromotedAuditRecordId(persistedRecord.getId());
        auditEventRepository.save(persistedEvent);

        return persistedRecord;
    }

    private AuditActor buildActorSnapshotFromEvent(AuditEvent event) {
        // In a real implementation, this would be built from event payload metadata.
        AuditActor actor = new AuditActor();
        actor.setActorReferenceId(null);
        actor.setActorType("SYSTEM");
        actor.setUsername("system");
        actor.setFullName("System");
        actor.setIpAddress(null);
        actor.setSessionId(null);
        actor.setUserAgent(null);
        return actor;
    }

    private AuditResource buildResourceSnapshotFromEvent(AuditEvent event) {
        AuditResource resource = new AuditResource();
        resource.setResourceReferenceId(null);
        resource.setResourceIdentifier(null);
        resource.setModule(event.getSourceModule());
        resource.setResourceLabel(null);
        resource.setResourceType(null);
        return resource;
    }

    private AuditAction buildActionFromEvent(AuditEvent event) {
        AuditAction action = new AuditAction();
        action.setActionType(null);
        action.setStatus("SUCCESS");
        action.setDurationMs(null);
        action.setOutcomeDescription("Event ingested into audit ledger");
        action.setErrorMessage(null);
        return action;
    }

    private AuditContext buildContextFromEvent(AuditEvent event) {
        AuditContext context = new AuditContext();
        context.setCorrelationId(event.getCorrelationId());
        context.setRequestId(null);
        context.setHandlerName(null);
        context.setHttpMethod(null);
        context.setServiceNode(null);
        context.setEnvironment(null);
        context.setMetadata(null);
        return context;
    }

    private String computeHashChainValue(String previousHash, AuditRecord record) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String base = (previousHash == null ? "" : previousHash)
                + record.getCorrelationId()
                + record.getEventTimestamp().getTime()
                + safe(record.getBeforeState())
                + safe(record.getAfterState());
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 not available for hash chain computation", ex);
        }
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}