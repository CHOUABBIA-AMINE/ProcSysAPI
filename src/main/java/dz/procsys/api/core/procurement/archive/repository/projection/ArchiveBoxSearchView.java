package dz.procsys.api.core.procurement.archive.repository.projection;

public interface ArchiveBoxSearchView {
    Long getId();
    String getCode();
    String getDescription();
    String getFacilityCode();
    String getRoomCode();
    String getShelfCode();
}
