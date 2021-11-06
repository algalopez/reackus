package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CreateProductActorTest {

    @Inject
    CreateProductActor createProductActor;

    @Test
    void test() {
        ProductType productType = new ProductType(1L, "product type 1");
        Product product = new Product(1L, productType, "product 1");

        Long newId = createProductActor.run(new Pair<>(1L, product)).await().indefinitely();

        assertEquals(1L, newId);
    }
}
