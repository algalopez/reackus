package com.algalopez.reackus.api.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.json.*;
import java.io.StringReader;

@ApplicationScoped
public class JsonPatchHelper {

  private final ObjectMapper objectMapper;

  public JsonPatchHelper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  public <T> T applyPatch(T source, JsonPatch jsonPatch, Class<T> type) {

    String sourceStr = objectMapper.writeValueAsString(source);
    JsonReader jsonReader = Json.createReader(new StringReader(sourceStr));
    JsonObject objarr = jsonReader.readObject();
    jsonReader.close();
    JsonStructure diff = jsonPatch.apply(objarr);
    return objectMapper.readValue(diff.toString(), type);
  }
}
