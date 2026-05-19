/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditArchiveMapper
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.audit.dto.response.AuditArchiveDetailResponseDTO;
import dz.procsys.api.core.system.audit.dto.response.AuditArchiveSummaryResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditArchive;

/**
 * MapStruct mapper for converting AuditArchive entities to summary and detail DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditArchiveMapper {

    /**
     * Maps an AuditArchive entity to a summary DTO.
     *
     * @param archive the AuditArchive entity
     * @return mapped AuditArchiveSummaryResponseDTO
     */
    AuditArchiveSummaryResponseDTO toSummaryDTO(AuditArchive archive);

    /**
     * Maps an AuditArchive entity to a detailed DTO.
     *
     * @param archive the AuditArchive entity
     * @return mapped AuditArchiveDetailResponseDTO
     */
    @Mapping(source = "retentionPolicy.code", target = "retentionPolicyCode")
    AuditArchiveDetailResponseDTO toDetailDTO(AuditArchive archive);
}