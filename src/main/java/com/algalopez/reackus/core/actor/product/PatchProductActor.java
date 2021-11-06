package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;
import org.javatuples.Triplet;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class PatchProductActor extends BaseInteractor<Triplet<Long, Long, JsonMergePatch>, Uni<Product>> {

    @Override
    public Uni<Product> run(Triplet<Long, Long, JsonMergePatch> patch) {
        ProductType productType = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(new Product(1L, productType, "product 1"));
    }

}
