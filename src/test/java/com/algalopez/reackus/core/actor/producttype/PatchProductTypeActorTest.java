package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonMergePatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PatchProductTypeActorTest {

    @Inject
    PatchProductTypeActor patchProductTypeActor;

    @Test
    void test() {
        ProductType expectedProductType = new ProductType(1L, "product type 1");
        JsonMergePatch jsonMergePatch = Json.createMergePatch(Json.createValue("{}"));

        ProductType productType = patchProductTypeActor.run(new Pair<>(1L, jsonMergePatch)).await().indefinitely();

        assertEquals(expectedProductType, productType);
    }
}
