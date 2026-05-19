/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActionMapper
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

import dz.procsys.api.core.system.audit.dto.response.AuditActionResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditAction;

/**
 * MapStruct mapper for converting AuditAction entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditActionMapper {

    /**
     * Maps an AuditAction entity to an AuditActionResponseDTO.
     * Maps nested actionType.code to actionTypeCode in the DTO.
     *
     * @param action the AuditAction entity
     * @return mapped AuditActionResponseDTO
     */
    @Mapping(source = "actionType.code", target = "actionTypeCode")
    AuditActionResponseDTO toDTO(AuditAction action);
}