package com.algalopez.reackus.api.product;

import com.algalopez.reackus.api.ResourceReader;
import com.algalopez.reackus.core.actor.producttype.*;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@QuarkusTest
class ProductTypeResourceTest {

    @InjectMock
    private GetAllProductTypesActor getAllProductTypesActor;

    @InjectMock
    private GetProductTypeActor getProductTypeActor;

    @InjectMock
    private CreateProductTypeActor createProductTypeActor;

    @InjectMock
    private DeleteProductTypeActor deleteProductTypeActor;

    @InjectMock
    private PatchProductTypeActor patchProductTypeActor;

    @Test
    void getAllProductTypes() {
        Mockito.when(getAllProductTypesActor.run(null)).thenReturn(buildExpectedProducts());

        given()
                .when().get("/product-type")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("productTypes[0].id", is(1))
                .body("productTypes[0].name", is("product type 1"));
    }

    @Test
    void getProductType() {
        Mockito.when(getProductTypeActor.run(1L)).thenReturn(buildExpectedProduct());

        given()
                .when().get("/product-type/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("productType.id", is(1))
                .body("productType.name", is("product type 1"));
    }

    @Test
    void postProductType() {
        Uni<Long> newId = Uni.createFrom().item(1L);
        Mockito.when(createProductTypeActor.run(any(ProductType.class))).thenReturn(newId);
        String body = ResourceReader.PRODUCT_TYPE_1.read();
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/product-type")
                .then()
                .statusCode(201)
                .header("location", is("http://localhost:8081/product-type/1"));
    }

    @Test
    void deleteProductType() {
        Mockito.when(deleteProductTypeActor.run(anyLong())).thenReturn(null);
        given()
                .when().delete("/product-type/1")
                .then()
                .statusCode(204);
    }

    @Test
    void patchProductType() {
        Mockito.when(patchProductTypeActor.run(any())).thenReturn(buildExpectedProduct());
        String body = ResourceReader.PRODUCT_TYPE_PATCH.read().trim();
        given()
                .contentType("application/merge-patch+json")
                .body(body)
                .when().patch("/product-type/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("productType.id", is(1))
                .body("productType.name", is("product type 1"));
    }

    static Uni<ProductType> buildExpectedProduct() {
        ProductType productType = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(productType);
    }

    static Uni<List<ProductType>> buildExpectedProducts() {
        ProductType productType = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(List.of(productType));
    }
}