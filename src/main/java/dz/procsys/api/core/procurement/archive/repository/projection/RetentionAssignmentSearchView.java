package dz.procsys.api.core.procurement.archive.repository.projection;

import dz.procsys.api.core.procurement.archive.model.RetentionLifecycleState;

import java.time.LocalDate;

public interface RetentionAssignmentSearchView {
    Long getId();
    Long getRetentionPolicyId();
    String getRetentionPolicyCode();
    RetentionLifecycleState getLifecycleState();
    LocalDate getDisposalEligibilityDate();
    Long getDocumentRecordId();
    Long getArchiveFolderId();
}
