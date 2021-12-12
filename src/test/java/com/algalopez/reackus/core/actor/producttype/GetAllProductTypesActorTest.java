package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.util.DbUtils;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GetAllProductTypesActorTest {

  @Inject GetAllProductTypesActor getAllProductTypesActor;

  @Inject DbUtils dbUtils;

  @AfterEach
  void cleanDatabase() {
    dbUtils.executeUpdate("DELETE FROM product_type WHERE id IN (1)");
  }

  @Test
  void getAllProductTypes() {
    dbUtils.executeUpdate("INSERT INTO product_type (id, name) VALUES (1, 'product type 1')");
    ProductType expectedProductType = new ProductType(1L, "product type 1");

    List<ProductType> productTypes = getAllProductTypesActor.run(null).await().indefinitely();

    assertEquals(List.of(expectedProductType), productTypes);
  }

  @Test
  void getAllProductTypes_whenNoElements() {
    List<ProductType> productTypes = getAllProductTypesActor.run(null).await().indefinitely();

    assertEquals(Collections.emptyList(), productTypes);
  }
}
