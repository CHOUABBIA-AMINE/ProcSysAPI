package dz.procsys.api.core.procurement.archive.dto.request;

public record DispositionDecisionRequest(Long retentionAssignmentId, boolean approved, String note) {}
