package com.algalopez.reackus.repository.action;

import com.algalopez.reackus.core.model.ActionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.Tuple;

@Mapper(componentModel = "cdi")
public interface ActionTypeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ActionType toModel(ActionTypeDto actionTypeDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ActionTypeDto toDto(ActionType actionType);

    static ActionTypeDto toDto(Tuple tuple) {
        Long id = Long.valueOf(tuple.get("id", Integer.class));
        String name = tuple.get("name", String.class);
        return new ActionTypeDto(id, name);
    }
}
