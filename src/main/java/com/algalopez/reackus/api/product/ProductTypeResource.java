package com.algalopez.reackus.api.product;


import com.algalopez.reackus.api.common.UriHelper;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.algalopez.reackus.api.product.ProductTypeResource.RESOURCE;

@Slf4j
@ApplicationScoped
@Path(RESOURCE)
@Tag(name = "Product type", description = "Product type operations")
public class ProductTypeResource {

    public static final String RESOURCE = "product-type";

    private final ProductTypeHandler productTypeHandler;

    public ProductTypeResource(final ProductTypeHandler productTypeHandler) {
        this.productTypeHandler = productTypeHandler;
    }

    @GET
    @Operation(summary = "Get product types", description = "Get list of product types")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponses> getAll() {

        return productTypeHandler.getAllProductTypes();
    }

    @GET
    @Operation(summary = "Get product type", description = "Get product type by id")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponse> get(@PathParam("id") Long id) {

        return productTypeHandler.getProductType(id);
    }

    @POST
    @Operation(summary = "Create product type", description = "Create product type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(ProductTypeDTO productType) {

        return productTypeHandler.createProductType(productType)
                .map(newId -> UriHelper.buildUri("product-type/%d".formatted(newId)))
                .map(newProductUri -> Response.created(newProductUri).build());
    }

    @DELETE
    @Operation(summary = "Delete product type", description = "Delete product type by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Void> delete(@PathParam("id") Long id) {

        return productTypeHandler.deleteProductType(id);
    }

    @PATCH
    @Operation(summary = "Update product type", description = "Update product type by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponse> patch(@PathParam("id") Long id,
                                          String patch) {

        return productTypeHandler.patchProductType(id, patch);
    }

}
