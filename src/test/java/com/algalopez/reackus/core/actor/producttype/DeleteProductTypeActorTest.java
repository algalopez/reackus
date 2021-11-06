package com.algalopez.reackus.core.actor.producttype;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@QuarkusTest
class DeleteProductTypeActorTest {

    @Inject
    DeleteProductTypeActor deleteProductTypeActor;

    @Test
    void test() {
        assertDoesNotThrow(() -> deleteProductTypeActor.run(1L).await().indefinitely());
    }
}
