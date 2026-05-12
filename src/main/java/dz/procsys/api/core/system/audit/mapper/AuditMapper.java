package dz.procsys.api.core.system.audit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dz.procsys.api.core.system.audit.dto.request.CreateAuditRequest;
import dz.procsys.api.core.system.audit.dto.request.UpdateAuditRequest;
import dz.procsys.api.core.system.audit.dto.response.AuditResponse;
import dz.procsys.api.core.system.audit.dto.response.AuditSummary;
import dz.procsys.api.core.system.audit.model.Audited;
import dz.procsys.api.platform.kernel.BaseMapper;

@Mapper(componentModel = "spring")
public interface AuditMapper extends BaseMapper<CreateAuditRequest, UpdateAuditRequest, AuditResponse, AuditSummary, Audited> {
    
    @Override
    void updateEntityFromRequest(UpdateAuditRequest dto, @MappingTarget Audited entity);
}
