package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.actor.producttype.*;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
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
                .map(x -> x.stream().map(productTypeMapper::toDTO).toList())
                .map(ProductTypeResponses::new);
    }

    public Uni<ProductTypeResponse> getProductType(Long id) {
        return getProductTypeActor.run(id)
                .map(productTypeMapper::toDTO)
                .map(ProductTypeResponse::new);
    }

    public Uni<Long> createProductType(ProductTypeDTO productType) {
        return Uni.createFrom().item(productType)
                .map(productTypeMapper::toModel)
                .flatMap(createProductTypeActor::run);
    }

    public Uni<Void> deleteProductType(Long id) {
        return deleteProductTypeActor.run(id);
    }

    public Uni<ProductTypeResponse> patchProductType(Long id, String patch) {
        JsonMergePatch jsonMergePatch = Json.createMergePatch(Json.createValue(patch));
        return patchProductTypeActor.run(new Pair<>(id, jsonMergePatch))
                .map(productTypeMapper::toDTO)
                .map(ProductTypeResponse::new);
    }
}
