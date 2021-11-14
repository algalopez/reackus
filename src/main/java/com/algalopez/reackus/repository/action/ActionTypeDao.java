package com.algalopez.reackus.repository.action;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Tuple;
import java.util.List;

@ApplicationScoped
public class ActionTypeDao {

    private final Mutiny.SessionFactory sf;

    ActionTypeDao(final Mutiny.SessionFactory sf) {
        this.sf = sf;
    }

    Uni<List<ActionTypeDto>> findAll() {
        return sf.withSession(session ->
                session.createNativeQuery("SELECT * FROM action_type;", Tuple.class)
                        .getResultList()).map(list -> list.stream().map(ActionTypeMapper::toDto).toList());
    }
}
