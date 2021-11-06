package com.algalopez.reackus.core.actor.product;

import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@QuarkusTest
class DeleteProductActorTest {

    @Inject
    DeleteProductActor deleteProductActor;

    @Test
    void test() {
        assertDoesNotThrow(() -> deleteProductActor.run(new Pair<>(1L, 1L)).await().indefinitely());
    }
}
