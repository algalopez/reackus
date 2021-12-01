package com.algalopez.reackus.core.service;

import com.algalopez.reackus.core.model.ActionType;
import com.algalopez.reackus.repository.action.ActionTypeAdapter;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionTypeService {

  private static final String CACHE_NAME = "action-type-cache";

  private final ActionTypeAdapter actionTypeAdapter;

  public ActionTypeService(final ActionTypeAdapter actionTypeAdapter) {
    this.actionTypeAdapter = actionTypeAdapter;
  }

  @CacheResult(cacheName = CACHE_NAME)
  public Uni<List<ActionType>> findAll() {
    return actionTypeAdapter.findAll();
  }

  @CacheInvalidateAll(cacheName = CACHE_NAME)
  public void invalidateAll() {
    // Invalidates all objects
  }
}
