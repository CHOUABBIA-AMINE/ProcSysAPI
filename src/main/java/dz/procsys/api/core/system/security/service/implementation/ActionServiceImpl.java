package dz.procsys.api.core.system.security.service.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.action.Action;
import dz.procsys.api.core.system.security.dto.request.CreateActionRequest;
import dz.procsys.api.core.system.security.dto.request.UpdateActionRequest;
import dz.procsys.api.core.system.security.dto.response.ActionResponse;
import dz.procsys.api.core.system.security.dto.response.ActionSummary;
import dz.procsys.api.core.system.security.mapper.ActionMapper;
import dz.procsys.api.core.system.security.repository.ActionRepository;
import dz.procsys.api.core.system.security.service.ActionService;
import dz.procsys.api.platform.kernel.AbstractCrudService;
import dz.procsys.api.platform.kernel.ReferenceResolver;

@Service
public class ActionServiceImpl extends AbstractCrudService<CreateActionRequest, UpdateActionRequest, ActionResponse, ActionSummary, Action> implements ActionService {

    public ActionServiceImpl(ActionRepository repository, ActionMapper mapper, ReferenceResolver referenceResolver, ApplicationEventPublisher eventPublisher) {
        super(repository, mapper, referenceResolver, eventPublisher);
    }

    @Override
    protected Class<Action> getEntityClass() {
        return Action.class;
    }
}
