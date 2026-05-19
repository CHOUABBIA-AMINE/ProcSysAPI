/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditRecordMapper
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.audit.dto.response.AuditRecordDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditRecordSummaryResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditTimelineItemResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditRecord;

/**
 * MapStruct mapper for converting AuditRecord entities to summary, detail and timeline DTOs.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        AuditActorMapper.class,
        AuditResourceMapper.class,
        AuditActionMapper.class,
        AuditContextMapper.class,
        AuditDiffMapper.class
    },
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditRecordMapper {

    /**
     * Maps an AuditRecord entity to a summary DTO for search and list screens.
     *
     * @param record the AuditRecord entity
     * @return mapped AuditRecordSummaryResponseDTO
     */
    @Mapping(source = "resource.module", target = "module")
    @Mapping(source = "resource.resourceIdentifier", target = "resourceIdentifier")
    @Mapping(source = "resource.resourceType.code", target = "resourceTypeCode")
    @Mapping(source = "actor.username", target = "actorUsername")
    @Mapping(source = "action.actionType.code", target = "actionTypeCode")
    @Mapping(source = "action.status", target = "status")
    @Mapping(target = "hasMaskedFields", expression = "java(record.getDiffs() != null && record.getDiffs().stream().anyMatch(d -> Boolean.TRUE.equals(d.getIsMasked())))")
    AuditRecordSummaryResponseDTO toSummaryDTO(AuditRecord record);

    /**
     * Maps an AuditRecord entity to a detail DTO for full forensic inspection.
     *
     * @param record the AuditRecord entity
     * @return mapped AuditRecordDetailResponseDTO
     */
    @Mapping(source = "beforeState", target = "beforeStateJson")
    @Mapping(source = "afterState", target = "afterStateJson")
    @Mapping(source = "granularityType.code", target = "granularityCode")
    @Mapping(source = "actor", target = "actor")
    @Mapping(source = "resource", target = "resource")
    @Mapping(source = "action", target = "action")
    @Mapping(source = "context", target = "context")
    @Mapping(source = "diffs", target = "diffs")
    AuditRecordDetailResponseDTO toDetailDTO(AuditRecord record);

    /**
     * Maps an AuditRecord entity to a timeline item DTO for actor or resource timelines.
     *
     * @param record the AuditRecord entity
     * @return mapped AuditTimelineItemResponseDTO
     */
    @Mapping(source = "id", target = "recordId")
    @Mapping(source = "eventTimestamp", target = "eventTimestamp")
    @Mapping(source = "action.actionType.code", target = "actionTypeCode")
    @Mapping(source = "action.status", target = "status")
    @Mapping(source = "resource.resourceIdentifier", target = "resourceIdentifier")
    @Mapping(source = "resource.resourceType.code", target = "resourceTypeCode")
    @Mapping(source = "action.outcomeDescription", target = "shortOutcomeDescription")
    AuditTimelineItemResponseDTO toTimelineItemDTO(AuditRecord record);

    /**
     * Maps a list of AuditRecord entities to a list of summary DTOs.
     *
     * @param records list of AuditRecord entities
     * @return list of AuditRecordSummaryResponseDTO
     */
    List<AuditRecordSummaryResponseDTO> toSummaryDTOList(List<AuditRecord> records);

    /**
     * Maps a list of AuditRecord entities to a list of timeline item DTOs.
     *
     * @param records list of AuditRecord entities
     * @return list of AuditTimelineItemResponseDTO
     */
    List<AuditTimelineItemResponseDTO> toTimelineItemDTOList(List<AuditRecord> records);
}