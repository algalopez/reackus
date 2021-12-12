package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.shared.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import com.algalopez.reackus.repository.product.ProductTypeAdapter;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GetAllProductTypesActor extends BaseInteractor<Void, Uni<List<ProductType>>> {

    private final ProductTypeAdapter productTypeAdapter;

    public GetAllProductTypesActor(ProductTypeAdapter productTypeAdapter) {
        this.productTypeAdapter = productTypeAdapter;
    }

    @Override
    public Uni<List<ProductType>> run(Void ignore) {
        return productTypeAdapter.findAll();
    }
}
