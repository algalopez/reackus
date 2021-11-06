package com.algalopez.reackus.api.product;

public record ProductDTO(
        Long id,
        ProductTypeDTO productType,
        String name
) {
}
