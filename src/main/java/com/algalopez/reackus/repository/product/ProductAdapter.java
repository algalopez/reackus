package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;

public class ProductAdapter {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Uni<Product> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toModel);
    }
}
