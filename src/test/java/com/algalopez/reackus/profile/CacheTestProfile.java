package com.algalopez.reackus.profile;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Collections;
import java.util.Map;

public class CacheTestProfile implements QuarkusTestProfile {

  @Override
  public Map<String, String> getConfigOverrides() {
    return Collections.singletonMap(
        "quarkus.cache.caffeine.\"action-type-cache\".expire-after-write", "300s");
  }

}
