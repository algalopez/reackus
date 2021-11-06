package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetAllProductTypesActorTest {

    @Inject
    GetAllProductTypesActor getAllProductTypesActor;

    @Test
    void test() {
        ProductType expectedProductType = new ProductType(1L, "product type 1");

        List<ProductType> productTypes = getAllProductTypesActor.run(null).await().indefinitely();

        assertEquals(List.of(expectedProductType), productTypes);
    }
}
