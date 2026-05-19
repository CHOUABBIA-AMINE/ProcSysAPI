/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditDiffMapper
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

import dz.procsys.api.core.system.audit.dto.response.AuditDiffResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditDiff;

/**
 * MapStruct mapper for converting AuditDiff entities to response DTOs.
 * Enforces explicit mapping of masking flag for traceability-safe responses.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditDiffMapper {

    /**
     * Maps an AuditDiff entity to an AuditDiffResponseDTO.
     *
     * @param diff the AuditDiff entity
     * @return mapped AuditDiffResponseDTO
     */
    @Mapping(source = "isMasked", target = "masked")
    AuditDiffResponseDTO toDTO(AuditDiff diff);

    /**
     * Maps a list of AuditDiff entities to a list of AuditDiffResponseDTO.
     *
     * @param diffs list of AuditDiff entities
     * @return list of AuditDiffResponseDTO
     */
    List<AuditDiffResponseDTO> toDTOList(List<AuditDiff> diffs);
}