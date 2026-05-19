/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditContextMapper
 *  @CreatedOn  : 05-19-2026
 *
 *  @Type       : Interface
 *  @Layer      : Mapper
 *  @Package    : System / Audit
 *
 **/

package dz.procsys.api.core.system.audit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dz.procsys.api.core.system.audit.dto.response.AuditContextResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditContext;

/**
 * MapStruct mapper for converting AuditContext entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditContextMapper {

    /**
     * Maps an AuditContext entity to an AuditContextResponseDTO.
     *
     * @param context the AuditContext entity
     * @return mapped AuditContextResponseDTO
     */
    AuditContextResponseDTO toDTO(AuditContext context);
}