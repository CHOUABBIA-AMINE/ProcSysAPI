/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditResourceMapper
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

import dz.procsys.api.core.system.audit.dto.response.AuditResourceResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditResource;

/**
 * MapStruct mapper for converting AuditResource entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditResourceMapper {

    /**
     * Maps an AuditResource entity to an AuditResourceResponseDto.
     * Maps nested resourceType.code to resourceTypeCode in the DTO.
     *
     * @param resource the AuditResource entity
     * @return mapped AuditResourceResponseDto
     */
    @Mapping(source = "resourceType.code", target = "resourceTypeCode")
    AuditResourceResponseDTO toDTO(AuditResource resource);
}