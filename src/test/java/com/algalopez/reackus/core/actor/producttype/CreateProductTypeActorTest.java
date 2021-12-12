package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.model.ProductType;
import com.algalopez.reackus.util.DbUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CreateProductTypeActorTest {

  @Inject CreateProductTypeActor createProductTypeActor;

  @Inject DbUtils dbUtils;

  @AfterEach
  void cleanDatabase() {
    dbUtils.executeUpdate("DELETE FROM product_type WHERE name IN ('product type 1')");
  }

  @Test
  void test() {
    ProductType productType = new ProductType(null, "product type 1");

    Long newId = createProductTypeActor.run(productType).await().indefinitely();

    assertTrue(newId > 0);
    Long dbId =
        dbUtils.executeQuery(
            "SELECT id FROM product_type WHERE name IN ('product type 1')", "id", Long.class);
    assertEquals(newId, dbId);
  }
}
