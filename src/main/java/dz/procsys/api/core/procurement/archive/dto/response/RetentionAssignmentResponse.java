package dz.procsys.api.core.procurement.archive.dto.response;

import dz.procsys.api.core.procurement.archive.model.RetentionClass;
import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleState;

import java.time.LocalDate;

public record RetentionAssignmentResponse(Long id, Long retentionPolicyId, RetentionClass retentionClass, Long documentRecordId, Long archiveFolderId,
                                          LocalDate startDate, Integer retentionPeriodDays, LocalDate disposalEligibilityDate, RetentionLifecycleState lifecycleState) {}
