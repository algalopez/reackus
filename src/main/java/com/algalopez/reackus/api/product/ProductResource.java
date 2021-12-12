package com.algalopez.reackus.api.product;


import com.algalopez.reackus.api.shared.UriHelper;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.algalopez.reackus.api.product.ProductResource.RESOURCE;

@Slf4j
@ApplicationScoped
@Path(RESOURCE)
@Tag(name = "Product", description = "Product operations")
public class ProductResource {

    public static final String RESOURCE = "product-type/{typeId}/product";

    private final ProductHandler productHandler;

    public ProductResource(final ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @GET
    @Operation(summary = "Get products by type", description = "Get list of product by type")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponses> getAll(@PathParam("typeId") Long typeId) {
        return productHandler.getAllProducts(typeId);
    }

    @GET
    @Operation(summary = "Get product", description = "Get product by typeId and id")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponse> get(
            @PathParam("typeId") Long typeId,
            @PathParam("id") Long id) {

        return productHandler.getProduct(typeId, id);
    }

    @POST
    @Operation(summary = "Create product", description = "Create product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(
            @PathParam("typeId") Long typeId,
            ProductDTO product) {

        return productHandler.createProduct(typeId, product)
                .map(newId -> UriHelper.buildUri("product-type/%d/product/%d".formatted(typeId, newId)))
                .map(newProductUri -> Response.created(newProductUri).build());
    }

    @DELETE
    @Operation(summary = "Delete product", description = "Delete product by typeId and id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Void> delete(@PathParam("typeId") Long typeId, @PathParam("id") Long id) {

        return productHandler.deleteProduct(typeId, id);
    }

    @PATCH
    @Operation(summary = "Update product", description = "Update product by typeId and id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductResponse> patch2(@PathParam("typeId") Long typeId,
                                       @PathParam("id") Long id,
                                       String patch) {

        return productHandler.patchProduct(typeId, id, patch);
    }
}
