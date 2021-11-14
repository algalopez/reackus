package com.algalopez.reackus.core.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {
    NONE(0),
    IN_PROGRESS(1),
    DONE(2),
    ERROR(3);

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    static Status fromValue(Integer value) {
        return Arrays.stream(Status.values())
                .filter(z -> z.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status value: " + value));
    }
}
