package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteProductActor extends BaseInteractor<Integer, Uni<Void>> {

    @Override
    public Uni<Void> run(Integer integer) {
        return Uni.createFrom().item(null);
    }
}
