package dz.procsys.api.core.procurement.archive.dto.request;

import dz.procsys.api.core.procurement.archive.model.RetentionStartTrigger;

import java.time.LocalDate;

public record AssignRetentionPolicyRequest(Long retentionPolicyId, Long documentRecordId, Long archiveFolderId, RetentionStartTrigger startTrigger, LocalDate startDate) {}
