package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProductActor extends BaseInteractor<Pair<Long, Product>, Uni<Long>> {

    @Override
    public Uni<Long> run(Pair<Long, Product> request) {
        return Uni.createFrom().item(1L);
    }
}
