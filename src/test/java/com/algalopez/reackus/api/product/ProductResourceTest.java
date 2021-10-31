package com.algalopez.reackus.api.product;

import com.algalopez.reackus.api.ResourceReader;
import com.algalopez.reackus.core.actor.product.*;
import com.algalopez.reackus.core.model.Product;
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
class ProductResourceTest {

    @InjectMock
    private GetAllProductsActor getAllProductsActor;

    @InjectMock
    private GetProductActor getProductActor;

    @InjectMock
    private CreateProductActor createProductActor;

    @InjectMock
    private DeleteProductActor deleteProductActor;

    @InjectMock
    private PatchProductActor patchProductActor;

    @Test
    void testGetAllProducts() {
        Mockito.when(getAllProductsActor.run(null)).thenReturn(buildExpectedProducts());

        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("products[0].id", is(1))
                .body("products[0].name", is("product 1"));
    }

    @Test
    void testGetProduct() {
        Mockito.when(getProductActor.run(1)).thenReturn(buildExpectedProduct());

        given()
                .when().get("/product/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("product.id", is(1))
                .body("product.name", is("product 1"));
    }

    @Test
    void testPostProduct() {
        Uni<Integer> newId = Uni.createFrom().item(1);
        Mockito.when(createProductActor.run(any(Product.class))).thenReturn(newId);
        String body = ResourceReader.PRODUCT_1.read();
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/product")
                .then()
                .statusCode(201)
                .header("location", is("http://localhost:8081/product/1"));
    }

    static Uni<Product> buildExpectedProduct() {
        Product product = new Product(1, "product 1");
        return Uni.createFrom().item(product);
    }

    static Uni<List<Product>> buildExpectedProducts() {
        Product product = new Product(1, "product 1");
        return Uni.createFrom().item(List.of(product));
    }
}