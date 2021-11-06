package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.core.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productType", source = "productType")
    @Mapping(target = "name", source = "name")
    ProductEntity toEntity(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productType", source = "productType")
    @Mapping(target = "name", source = "name")
    Product toModel(ProductEntity product);
}
