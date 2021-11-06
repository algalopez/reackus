package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetProductActorTest {

    @Inject
    GetProductActor getProductActor;

    @Test
    void test() {
        ProductType expectedProductType = new ProductType(1L, "product type 1");
        Product expectedProduct = new Product(1L, expectedProductType, "product 1");

        Product product = getProductActor.run(new Pair<>(1L, 1L)).await().indefinitely();

        assertEquals(expectedProduct, product);
    }
}
