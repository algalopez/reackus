package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProductActor extends BaseInteractor<Product, Uni<Integer>> {

    @Override
    public Uni<Integer> run(Product product) {
        return Uni.createFrom().item(1);
    }
}
