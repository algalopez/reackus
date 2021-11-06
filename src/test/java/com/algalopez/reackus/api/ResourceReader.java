package com.algalopez.reackus.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public enum ResourceReader {
    PRODUCT_TYPE_1("api/product-type-1.json"),
    PRODUCT_TYPE_PATCH("api/product-type-patch.json"),
    PRODUCT_1("api/product-1.json"),
    PRODUCT_PATCH("api/product-patch.json");

    private final String fileName;
    ResourceReader(String fileName) {
        this.fileName = fileName;
    }

    public String read() {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            } else {
                throw new RuntimeException("Error resource not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource");
        }
    }
}
