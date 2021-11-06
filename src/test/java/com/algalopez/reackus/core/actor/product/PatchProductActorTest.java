package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.core.model.Product;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonMergePatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PatchProductActorTest {

    @Inject
    PatchProductActor patchProductActor;

    @Test
    void test() {
        ProductType expectedProductType = new ProductType(1L, "product type 1");
        Product expectedProduct = new Product(1L, expectedProductType, "product 1");
        JsonMergePatch jsonMergePatch = Json.createMergePatch(Json.createValue("{}"));

        Product product = patchProductActor.run(new Triplet<>(1L, 1L, jsonMergePatch)).await().indefinitely();

        assertEquals(expectedProduct, product);
    }
}
