package com.algalopez.reackus.api.product;

import com.algalopez.reackus.core.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductTypeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductTypeDTO toDto(ProductType productType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductType toModel(ProductTypeDTO productType);
}
