package com.algalopez.reackus.core.shared;

import com.algalopez.reackus.api.shared.JsonPatchHelper;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonPatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class JsonPatchHelperTest {

  @Inject
  JsonPatchHelper jsonPatchHelper;

  @Test
  void jsonPatch() {
    JsonPatch jsonPatch = Json.createPatchBuilder().replace("/name", "name 2").build();
    ProductType productType = new ProductType(1L, "name 1");

    ProductType patchedProductType =
        jsonPatchHelper.applyPatch(productType, jsonPatch, ProductType.class);

    ProductType expectedProductType = new ProductType(1L, "name 2");
    assertEquals(expectedProductType, patchedProductType);
  }
}
