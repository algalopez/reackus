package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.shared.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import com.algalopez.reackus.repository.product.ProductTypeAdapter;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProductTypeActor extends BaseInteractor<ProductType, Uni<Long>> {

    private final ProductTypeAdapter productTypeAdapter;

    public CreateProductTypeActor(ProductTypeAdapter productTypeAdapter) {
        this.productTypeAdapter = productTypeAdapter;
    }

    @Override
    public Uni<Long> run(ProductType productType) {

        return productTypeAdapter.create(productType);
    }
}
