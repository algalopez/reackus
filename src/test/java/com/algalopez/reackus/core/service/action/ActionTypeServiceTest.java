package com.algalopez.reackus.core.service.action;

import com.algalopez.reackus.util.DbUtils;
import com.algalopez.reackus.core.model.ActionType;
import com.algalopez.reackus.core.service.ActionTypeService;
import com.algalopez.reackus.profile.CacheTestProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(CacheTestProfile.class)
class ActionTypeServiceTest {

  @Inject ActionTypeService actionTypeService;

  @Inject DbUtils dbUtils;

  @AfterEach
  void cleanDatabase() {
    dbUtils.executeUpdate("DELETE FROM action_type WHERE id IN (1)");
  }

  @Test
  void actionTypeCache() {
    dbUtils.executeUpdate("INSERT INTO action_type (id, name) VALUES (1, 'buy')");
    actionTypeService.findAll().await().indefinitely();
    dbUtils.executeUpdate("UPDATE action_type SET name = 'sell' WHERE id = 1");

    List<ActionType> actionTypes = actionTypeService.findAll().await().indefinitely();

    assertEquals("buy", actionTypes.get(0).name());
  }

  @Test
  void actionTypeCache_invalidateAll() {
    dbUtils.executeUpdate("INSERT INTO action_type (id, name) VALUES (1, 'buy')");
    actionTypeService.findAll().await().indefinitely();
    actionTypeService.invalidateAll();
    dbUtils.executeUpdate("UPDATE action_type SET name = 'sell' WHERE id = 1");

    List<ActionType> actionTypes = actionTypeService.findAll().await().indefinitely();

    assertEquals("sell", actionTypes.get(0).name());
  }
}
