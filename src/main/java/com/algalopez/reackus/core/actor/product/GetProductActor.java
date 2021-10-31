package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetProductActor extends BaseInteractor<Integer, Uni<Product>> {

    @Override
    public Uni<Product> run(Integer integer) {
        return Uni.createFrom().item(new Product(1, "product 1"));
    }
}
