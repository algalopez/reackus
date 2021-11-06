package com.algalopez.reackus.core.model;

import lombok.Builder;

public record Product(
        Long id,
        ProductType productType,
        String name
) {

    @Builder
    public Product {
        // Make lombok builder work with java records
    }

}
