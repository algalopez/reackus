package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.util.DbUtils;
import com.algalopez.reackus.core.exception.ResourceNotFoundException;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class GetProductTypeActorTest {

  @Inject GetProductTypeActor getProductTypeActor;

  @Inject DbUtils dbUtils;

  @AfterEach
  void cleanDatabase() {
    dbUtils.executeUpdate("DELETE FROM product_type WHERE id IN (1)");
  }

  @Test
  void getProductType() {
    dbUtils.executeUpdate("INSERT INTO product_type (id, name) VALUES (1, 'product type 1')");
    ProductType expectedProductType = new ProductType(1L, "product type 1");

    ProductType productType = getProductTypeActor.run(1L).await().indefinitely();

    assertEquals(expectedProductType, productType);
  }

  @Test
  void getProductType_whenNotFound() {

    assertThrows(
        ResourceNotFoundException.class, () -> getProductTypeActor.run(1L).await().indefinitely());
  }
}
