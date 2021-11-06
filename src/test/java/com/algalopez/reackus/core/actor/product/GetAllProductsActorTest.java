package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetAllProductsActorTest {

    @Inject
    GetAllProductsActor getAllProductsActor;

    @Test
    void test() {
        ProductType productType = new ProductType(1L, "product type 1");
        Product product = new Product(1L, productType, "product 1");

        List<Product> products = getAllProductsActor.run(1L).await().indefinitely();

        assertEquals(List.of(product), products);
    }
}
