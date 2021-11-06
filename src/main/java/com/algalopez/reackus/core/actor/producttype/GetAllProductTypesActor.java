package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GetAllProductTypesActor extends BaseInteractor<Void, Uni<List<ProductType>>> {

    @Override
    public Uni<List<ProductType>> run(Void ignore) {
        ProductType productType = new ProductType(1L, "product 1");
        return Uni.createFrom().item(Collections.singletonList(productType));
    }
}
