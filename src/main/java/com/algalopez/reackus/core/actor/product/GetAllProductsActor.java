package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GetAllProductsActor extends BaseInteractor<Void, Uni<List<Product>>> {

    @Override
    public Uni<List<Product>> run(Void ignore) {
        Product product = new Product(1, "product 1");
        return Uni.createFrom().item(Collections.singletonList(product));
    }
}
