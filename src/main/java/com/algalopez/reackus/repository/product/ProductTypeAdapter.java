package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.core.model.ProductType;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductTypeAdapter {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public ProductTypeAdapter(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    public Uni<ProductType> findById(Long id) {
        return productTypeRepository.findById(id).map(productTypeMapper::toModel);
    }
}
