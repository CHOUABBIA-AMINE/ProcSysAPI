package dz.procsys.api.core.procurement.archive.repository.projection;

public interface ArchiveLocationSearchView {
    Long getId();
    String getCode();
    String getFacilityCode();
    String getRoomCode();
    String getShelfCode();
    String getShelfLevelCode();
}
