package com.algalopez.reackus.repository.action;

import com.algalopez.reackus.DbUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
class ActionTypeDaoTest {

    @Inject
    ActionTypeDao actionTypeDao;

    @Inject
    DbUtils dbUtils;

    @AfterEach
    void cleanDatabase() {
        dbUtils.executeUpdate("DELETE FROM action_type WHERE id IN (1, 2)");
    }

    @Test
    void findAll() {
        dbUtils.executeUpdate("INSERT INTO action_type (id, name) VALUES (1, 'buy')");
        dbUtils.executeUpdate("INSERT INTO action_type (id, name) VALUES (2, 'sell')");

        List<ActionTypeDto> actualEntities = actionTypeDao.findAll().await().indefinitely();

        ActionTypeDto expectedEntity1 = new ActionTypeDto(1L, "buy");
        ActionTypeDto expectedEntity2 = new ActionTypeDto(2L, "sell");

        Assertions.assertEquals(expectedEntity1, actualEntities.get(0));
        Assertions.assertEquals(expectedEntity2, actualEntities.get(1));
    }


}
