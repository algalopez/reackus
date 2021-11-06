package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productType", source = "productType")
    @Mapping(target = "name", source = "name")
    ProductDTO toDTO(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productType", source = "productType")
    @Mapping(target = "name", source = "name")
    Product toModel(ProductDTO product);
}
