package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.api.common.BaseInteractor;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteProductTypeActor extends BaseInteractor<Integer, Uni<Void>> {

    @Override
    public Uni<Void> run(Integer integer) {
        return Uni.createFrom().item(null);
    }
}
