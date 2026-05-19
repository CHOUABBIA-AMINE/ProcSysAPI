/**
 *
 *  @Author     : System
 *
 *  @Name       : AuditActorMapper
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

import dz.procsys.api.core.system.audit.dto.response.AuditActorResponseDTO;
import dz.procsys.api.core.system.audit.model.AuditActor;

/**
 * MapStruct mapper for converting AuditActor entities to response DTOs.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditActorMapper {

    /**
     * Maps an AuditActor entity to an AuditActorResponseDto.
     *
     * @param actor the AuditActor entity
     * @return mapped AuditActorResponseDto
     */
    AuditActorResponseDTO toDTO(AuditActor actor);
}