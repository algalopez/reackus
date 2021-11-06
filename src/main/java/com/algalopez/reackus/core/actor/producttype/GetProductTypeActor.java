package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetProductTypeActor extends BaseInteractor<Long, Uni<ProductType>> {

    @Override
    public Uni<ProductType> run(Long ignore) {
        return Uni.createFrom().item(new ProductType(1L, "product type 1"));
    }
}
