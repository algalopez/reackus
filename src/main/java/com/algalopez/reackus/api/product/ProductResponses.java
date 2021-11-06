package com.algalopez.reackus.api.product;

import java.util.List;

public record ProductResponses(
        List<ProductDTO> products
) {
}
