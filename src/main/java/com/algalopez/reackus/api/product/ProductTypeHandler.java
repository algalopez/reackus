package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.actor.producttype.*;
import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class ProductTypeHandler {

    private final ProductTypeMapper productTypeMapper;
    private final GetProductTypeActor getProductTypeActor;
    private final GetAllProductTypesActor getAllProductTypesActor;
    private final CreateProductTypeActor createProductTypeActor;
    private final DeleteProductTypeActor deleteProductTypeActor;
    private final PatchProductTypeActor patchProductTypeActor;

    public ProductTypeHandler(ProductTypeMapper productTypeMapper,
                              GetProductTypeActor getProductTypeActor,
                              GetAllProductTypesActor getAllProductTypesActor,
                              CreateProductTypeActor createProductTypeActor,
                              DeleteProductTypeActor deleteProductTypeActor,
                              PatchProductTypeActor patchProductTypeActor) {
        this.productTypeMapper = productTypeMapper;
        this.getProductTypeActor = getProductTypeActor;
        this.getAllProductTypesActor = getAllProductTypesActor;
        this.createProductTypeActor = createProductTypeActor;
        this.deleteProductTypeActor = deleteProductTypeActor;
        this.patchProductTypeActor = patchProductTypeActor;
    }

    public Uni<ProductTypeResponses> getAllProductTypes() {
        return getAllProductTypesActor.run(null)
                .onItem().transform(x -> x.stream().map(productTypeMapper::toDto).toList())
                .onItem().transform(ProductTypeResponses::new);
    }

    public Uni<ProductTypeResponse> getProductType(Integer id) {
        return getProductTypeActor.run(id)
                .onItem().transform(productTypeMapper::toDto)
                .onItem().transform(ProductTypeResponse::new);
    }

    public Uni<Integer> createProductType(ProductType productType) {
        return createProductTypeActor.run(productType);
    }

    public Uni<Void> deleteProductType(Integer id) {
        return deleteProductTypeActor.run(id);
    }

    public Uni<ProductTypeResponse> patchProductType(Integer id, JsonMergePatch patch) {
        return patchProductTypeActor.run(new Pair<>(id, patch))
                .onItem().transform(productTypeMapper::toDto)
                .onItem().transform(ProductTypeResponse::new);
    }
}
