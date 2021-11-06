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
    void testGetAllProducts() {
        Mockito.when(getAllProductTypesActor.run(null)).thenReturn(buildExpectedProducts());

        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("productTypes[0].id", is(1))
                .body("productTypes[0].name", is("product type 1"));
    }

    @Test
    void testGetProduct() {
        Mockito.when(getProductTypeActor.run(1)).thenReturn(buildExpectedProduct());

        given()
                .when().get("/product/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("productType.id", is(1))
                .body("productType.name", is("product type 1"));
    }

    @Test
    void testPostProduct() {
        Uni<Integer> newId = Uni.createFrom().item(1);
        Mockito.when(createProductTypeActor.run(any(ProductType.class))).thenReturn(newId);
        String body = ResourceReader.PRODUCT_1.read();
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/product")
                .then()
                .statusCode(201)
                .header("location", is("http://localhost:8081/product/1"));
    }

    static Uni<ProductType> buildExpectedProduct() {
        ProductType product = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(product);
    }

    static Uni<List<ProductType>> buildExpectedProducts() {
        ProductType product = new ProductType(1L, "product type 1");
        return Uni.createFrom().item(List.of(product));
    }
}