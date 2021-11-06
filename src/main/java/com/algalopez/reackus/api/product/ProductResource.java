package com.algalopez.reackus.api.product;


import com.algalopez.reackus.api.common.UriException;
import com.algalopez.reackus.core.model.ProductType;
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

    private final ProductTypeHandler productTypeHandler;

    public ProductResource(final ProductTypeHandler productTypeHandler) {
        this.productTypeHandler = productTypeHandler;
    }

    @GET
    @Operation(summary = "Get products", description = "Get list of products")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponses> getAll() {

        return productTypeHandler.getAllProductTypes();
    }

    @GET
    @Operation(summary = "Get product", description = "Get product by id")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponse> get(@PathParam("id") Integer id) {

        return productTypeHandler.getProductType(id);
    }

    @POST
    @Operation(summary = "Create product", description = "Create product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(ProductType productType) {

        return productTypeHandler.createProductType(productType)
                .map(ProductResource::buildUri)
                .map(newProductUri -> Response.created(newProductUri).build());
    }

    @DELETE
    @Operation(summary = "Delete product", description = "Delete product by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponse> delete(@PathParam("id") Integer id) {

        return productTypeHandler.getProductType(1);
    }

    @PATCH
    @Operation(summary = "Update product", description = "Update product by id")
    @Path("/{id}")
    @Consumes("application/merge-patch+json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ProductTypeResponse> patch(@PathParam("id") Integer id, JsonNode patch) {

        return productTypeHandler.getProductType(1);
    }

    private static URI buildUri(Integer id) {
        try {
            return new URI("/%s/%d".formatted(RESOURCE, id));
        } catch (URISyntaxException e) {
            log.trace("", e);
            throw new UriException(e);
        }
    }
}
