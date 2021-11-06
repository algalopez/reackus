package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GetAllProductsActor extends BaseInteractor<Long, Uni<List<Product>>> {

    @Override
    public Uni<List<Product>> run(Long typeId) {
        ProductType productType = new ProductType(1L, "product type 1");
        Product product = new Product(1L, productType, "product 1");
        return Uni.createFrom().item(Collections.singletonList(product));
    }
}
