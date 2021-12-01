package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.DbUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ProductRepositoryTest {

  @Inject ProductRepository repository;

  @Inject DbUtils dbUtils;

  @AfterEach
  void cleanDatabase() {
    dbUtils.executeUpdate("DELETE FROM product_type WHERE id IN (1)");
    dbUtils.executeUpdate("DELETE FROM product WHERE id IN (1)");
  }

  @Test
  void findById() {
    dbUtils.executeUpdate("INSERT INTO product_type (id, name) VALUES (1, 'house')");
    dbUtils.executeUpdate("INSERT INTO product (id, type, name) VALUES (1, 1, 'H1')");

    ProductEntity entity = repository.findById(1L).await().indefinitely();

    ProductTypeEntity expectedTypeEntity = new ProductTypeEntity(1L, "house");
    ProductEntity expectedEntity = new ProductEntity(1L, expectedTypeEntity, "H1");
    assertEquals(expectedEntity, entity);
  }
}
