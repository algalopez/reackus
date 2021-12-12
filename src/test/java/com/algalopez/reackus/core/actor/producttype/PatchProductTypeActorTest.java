package com.algalopez.reackus.core.actor.producttype;

import io.quarkus.test.junit.QuarkusTest;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonMergePatch;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class PatchProductTypeActorTest {

  @Inject PatchProductTypeActor patchProductTypeActor;

  @Test
  void test() {
    JsonMergePatch mergePatch =
        Json.createMergePatch(Json.createObjectBuilder().add("name", "Product type 2").build());

    assertThrows(
        UnsupportedOperationException.class,
        () -> patchProductTypeActor.run(new Pair<>(1L, mergePatch)).await().indefinitely());
  }
}
