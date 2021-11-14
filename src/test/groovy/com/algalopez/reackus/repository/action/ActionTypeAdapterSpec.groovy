package com.algalopez.reackus.repository.action

import com.algalopez.reackus.core.model.ActionType
import io.smallrye.mutiny.Uni
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class ActionTypeAdapterSpec extends Specification {

    private ActionTypeDao actionTypeDao = Mock(ActionTypeDao)
    private ActionTypeMapper actionTypeMapper = Mappers.getMapper(ActionTypeMapper.class)
    private ActionTypeAdapter actionTypeAdapter

    def setup() {
        actionTypeAdapter = new ActionTypeAdapter(actionTypeDao, actionTypeMapper)
    }

    void 'find all existing action types'() {
        given: 'An existing action type'
        def existingActionType = new ActionTypeDto(1L, "action type 1")

        when:
        def actual = actionTypeAdapter.findAll().await().indefinitely()

        then:
        1 * actionTypeDao.findAll() >> Uni.createFrom().item([existingActionType])
        actual == [new ActionType(existingActionType.getId(), existingActionType.getName())]
    }
}
