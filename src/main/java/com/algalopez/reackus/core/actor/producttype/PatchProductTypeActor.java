package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class PatchProductTypeActor extends BaseInteractor<Pair<Long, JsonMergePatch>, Uni<ProductType>> {

    @Override
    public Uni<ProductType> run(Pair<Long, JsonMergePatch> patch) {
        return Uni.createFrom().item(new ProductType(1L, "product type 1"));
    }
}
