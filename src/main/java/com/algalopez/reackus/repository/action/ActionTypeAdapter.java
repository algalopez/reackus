package com.algalopez.reackus.repository.action;

import com.algalopez.reackus.core.model.ActionType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionTypeAdapter {

    private final ActionTypeDao actionTypeDao;
    private final ActionTypeMapper actionTypeMapper;

    public ActionTypeAdapter(ActionTypeDao actionTypeDao, ActionTypeMapper actionTypeMapper) {
        this.actionTypeDao = actionTypeDao;
        this.actionTypeMapper = actionTypeMapper;
    }

    public Uni<List<ActionType>> findAll() {
        return actionTypeDao.findAll()
                .map(actionTypeEntities -> actionTypeEntities.stream().map(actionTypeMapper::toModel).toList());
    }
}
