package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetProductTypeActorTest {

    @Inject
    GetProductTypeActor getProductTypeActor;

    @Test
    void test() {
        ProductType expectedProductType = new ProductType(1L, "product type 1");

        ProductType productType = getProductTypeActor.run(1L).await().indefinitely();

        assertEquals(expectedProductType, productType);
    }
}
