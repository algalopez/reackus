package com.algalopez.reackus.api.product;

import com.algalopez.reackus.api.ResourceReader;
import com.algalopez.reackus.core.actor.product.*;
import com.algalopez.reackus.core.model.Product;
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
        Mockito.when(getAllProductsActor.run(anyLong())).thenReturn(buildExpectedProducts());

        given()
                .when().get("/product-type/1/product")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("products[0].id", is(1))
                .body("products[0].productType.id", is(1))
                .body("products[0].productType.name", is("product type 1"))
                .body("products[0].name", is("product 1"));
    }

    @Test
    void getProduct() {
        Mockito.when(getProductActor.run(any())).thenReturn(buildExpectedProduct());

        given()
                .when().get("/product-type/1/product/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("product.id", is(1))
                .body("product.productType.id", is(1))
                .body("product.productType.name", is("product type 1"))
                .body("product.name", is("product 1"));
    }

    @Test
    void postProduct() {
        Uni<Long> newId = Uni.createFrom().item(1L);
        Mockito.when(createProductActor.run(any())).thenReturn(newId);
        String body = ResourceReader.PRODUCT_1.read();
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/product-type/1/product")
                .then()
                .statusCode(201)
                .header("location", is("http://localhost:8081/product-type/1/product/1"));
    }

    @Test
    void deleteProduct() {
        Mockito.when(deleteProductActor.run(any())).thenReturn(null);
        given()
                .when().delete("/product-type/1/product/1")
                .then()
                .statusCode(204);
    }

    @Test
    void patchProduct() {
        Mockito.when(patchProductActor.run(any())).thenReturn(buildExpectedProduct());
        String body = ResourceReader.PRODUCT_PATCH.read();
        given()
                .contentType("application/merge-patch+json")
                .body(body)
                .when().patch("/product-type/1/product/1")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.BODY)
                .body("product.id", is(1))
                .body("product.productType.id", is(1))
                .body("product.productType.name", is("product type 1"))
                .body("product.name", is("product 1"));
    }

    static Uni<List<Product>> buildExpectedProducts() {
        ProductType productType = new ProductType(1L, "product type 1");
        Product product = new Product(1L, productType, "product 1");
        return Uni.createFrom().item(List.of(product));
    }

    static Uni<Product> buildExpectedProduct() {
        ProductType productType = new ProductType(1L, "product type 1");
        Product product = new Product(1L, productType, "product 1");
        return Uni.createFrom().item(product);
    }

}