package com.algalopez.reackus.api.product;


import com.algalopez.reackus.core.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static com.algalopez.reackus.api.product.ProductResource.RESOURCE;

@Slf4j
@ApplicationScoped
@Path(RESOURCE)
public class ProductResource {

    public static final String RESOURCE = "product";

    private final ProductHandler productHandler;

    public ProductResource(final ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @GET
    @Operation(summary = "Get products", description = "Get list of products")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponses> getAll() {

        return productHandler.getAllProducts();
    }

    @GET
    @Operation(summary = "Get product", description = "Get product by id")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponse> get(@PathParam("id") Integer id) {

        return productHandler.getProduct(id);
    }

    @POST
    @Operation(summary = "Create product", description = "Create product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Product product) {

        return productHandler.createProduct(product)
                .map(ProductResource::buildUri)
                .map(newProductUri -> Response.created(newProductUri).build());
    }

    @DELETE
    @Operation(summary = "Delete product", description = "Delete product by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponse> delete(@PathParam("id") Integer id) {

        return productHandler.getProduct(1);
    }

    @PATCH
    @Operation(summary = "Update product", description = "Update product by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponse> patch(@PathParam("id") Integer id, JsonNode patch) {

        return productHandler.getProduct(1);
    }

    private static URI buildUri(Integer id) {
        try {
            return new URI("/%s/%d".formatted(RESOURCE, id));
        } catch (URISyntaxException e) {
            log.trace("", e);
            throw new RuntimeException(e);
        }
    }
}
