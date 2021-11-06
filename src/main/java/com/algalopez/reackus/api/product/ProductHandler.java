package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.actor.product.*;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class ProductHandler {

    private final ProductMapper productMapper;
    private final CreateProductActor createProductActor;
    private final DeleteProductActor deleteProductActor;
    private final GetAllProductsActor getAllProductsActor;
    private final GetProductActor getProductActor;
    private final PatchProductActor patchProductActor;


    public ProductHandler(ProductMapper productMapper,
                          CreateProductActor createProductActor,
                          DeleteProductActor deleteProductActor,
                          GetAllProductsActor getAllProductsActor,
                          GetProductActor getProductActor,
                          PatchProductActor patchProductActor) {
        this.productMapper = productMapper;
        this.createProductActor = createProductActor;
        this.deleteProductActor = deleteProductActor;
        this.getAllProductsActor = getAllProductsActor;
        this.getProductActor = getProductActor;
        this.patchProductActor = patchProductActor;
    }

    public Uni<ProductResponses> getAllProducts(Long typeId) {
        return getAllProductsActor.run(typeId)
                .map(x -> x.stream().map(productMapper::toDTO).toList())
                .map(ProductResponses::new);
    }

    public Uni<ProductResponse> getProduct(Long typeId, Long id) {
        return getProductActor.run(new Pair<>(typeId, id))
                .map(productMapper::toDTO)
                .map(ProductResponse::new);
    }

    public Uni<Long> createProduct(Long typeId, ProductDTO product) {
        return Uni.createFrom().item(product)
                .map(productMapper::toModel)
                .flatMap(x -> createProductActor.run(new Pair<>(typeId, x)));
    }

    public Uni<Void> deleteProduct(Long typeId, Long id) {
        return deleteProductActor.run(new Pair<>(typeId, id));
    }

    public Uni<ProductResponse> patchProduct(Long typeId, Long id, String patch) {
        JsonMergePatch jsonMergePatch = Json.createMergePatch(Json.createValue(patch));
        return patchProductActor.run(new Triplet<>(typeId, id, jsonMergePatch))
                .map(productMapper::toDTO)
                .map(ProductResponse::new);
    }
}
