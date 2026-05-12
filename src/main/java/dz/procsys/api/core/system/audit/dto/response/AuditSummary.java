package dz.procsys.api.core.system.audit.dto.response;

import java.util.Date;

public record AuditSummary(
        Long id,
        String entityName,
        String action,
        String username,
        Date timestamp,
        String status
) {}
