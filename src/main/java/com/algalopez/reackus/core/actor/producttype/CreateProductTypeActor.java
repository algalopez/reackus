package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProductTypeActor extends BaseInteractor<ProductType, Uni<Long>> {

    @Override
    public Uni<Long> run(ProductType productType) {
        return Uni.createFrom().item(1L);
    }
}
