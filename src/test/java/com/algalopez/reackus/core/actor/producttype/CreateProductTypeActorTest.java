package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CreateProductTypeActorTest {

    @Inject
    CreateProductTypeActor createProductTypeActor;

    @Test
    void test() {
        ProductType productType = new ProductType(1L, "product type 1");

        Long newId = createProductTypeActor.run(productType).await().indefinitely();

        assertEquals(1L, newId);
    }
}
