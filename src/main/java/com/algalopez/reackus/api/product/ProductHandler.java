package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.actor.product.*;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class ProductHandler {

    private final ProductMapper productMapper;
    private final GetProductActor getProductActor;
    private final GetAllProductsActor getAllProductsActor;
    private final CreateProductActor createProductActor;
    private final DeleteProductActor deleteProductActor;
    private final PatchProductActor patchProductActor;

    public ProductHandler(ProductMapper productMapper,
                          GetProductActor getProductActor,
                          GetAllProductsActor getAllProductsActor,
                          CreateProductActor createProductActor,
                          DeleteProductActor deleteProductActor,
                          PatchProductActor patchProductActor) {
        this.productMapper = productMapper;
        this.getProductActor = getProductActor;
        this.getAllProductsActor = getAllProductsActor;
        this.createProductActor = createProductActor;
        this.deleteProductActor = deleteProductActor;
        this.patchProductActor = patchProductActor;
    }

    public Uni<ProductResponses> getAllProducts() {
        return getAllProductsActor.run(null)
                .onItem().transform(x -> x.stream().map(productMapper::toDto).toList())
                .onItem().transform(ProductResponses::new);
    }

    public Uni<ProductResponse> getProduct(Integer id) {
        return getProductActor.run(id)
                .onItem().transform(productMapper::toDto)
                .onItem().transform(ProductResponse::new);
    }

    public Uni<Integer> createProduct(Product product) {
        return createProductActor.run(product);
    }

    public Uni<Void> deleteProduct(Integer id) {
        return deleteProductActor.run(id);
    }

    public Uni<ProductResponse> patchProduct(Integer id, JsonMergePatch patch) {
        return patchProductActor.run(new Pair<>(id, patch))
                .onItem().transform(productMapper::toDto)
                .onItem().transform(ProductResponse::new);
    }
}
