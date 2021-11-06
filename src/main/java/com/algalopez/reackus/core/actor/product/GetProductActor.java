package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetProductActor extends BaseInteractor<Pair<Long, Long>, Uni<Product>> {

    @Override
    public Uni<Product> run(Pair<Long, Long> ignore) {
        ProductType productType = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(new Product(1L, productType, "product 1"));
    }
}
