package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.DbUtils;
import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ProductTypeRepositoryTest {

    @Inject
    ProductTypeRepository repository;

    @Inject
    DbUtils dbUtils;

    @AfterEach
    void cleanDatabase() {
        dbUtils.executeUpdate("DELETE FROM product_type WHERE id IN (1)");
    }

    @Test
    void findById() {
        dbUtils.executeUpdate("INSERT INTO product_type (id, name) VALUES (1, 'house')");

        ProductTypeEntity entity = repository.findById(1L).await().indefinitely();

        ProductTypeEntity expectedEntity = new ProductTypeEntity(1L, "house");
        assertEquals(expectedEntity, entity);
    }

}
