package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.util.DbUtils;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Slf4j
class DeleteProductTypeActorTest {

  @Inject DeleteProductTypeActor deleteProductTypeActor;

  @Inject DbUtils dbUtils;

  @Test
  void test() {
    dbUtils.executeUpdate("INSERT INTO product_type (id, name) VALUES (25, 'product type 2')");

    assertDoesNotThrow(() -> deleteProductTypeActor.run(25L).await().indefinitely());

    Integer count =
        dbUtils.executeQuery("SELECT COUNT(*) as count FROM product_type;", "count", Integer.class);
    assertEquals(0, count);
  }
}
