package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.core.model.ProductType;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProductTypeAdapter {

  private final ProductTypeRepository productTypeRepository;
  private final ProductTypeMapper productTypeMapper;

  public ProductTypeAdapter(
      ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
    this.productTypeRepository = productTypeRepository;
    this.productTypeMapper = productTypeMapper;
  }

  public Uni<List<ProductType>> findAll() {
    return productTypeRepository
        .findAll()
        .list()
        .map(productTypeList -> productTypeList.stream().map(productTypeMapper::toModel).toList());
  }

  public Uni<ProductType> findById(Long id) {
    return productTypeRepository.findById(id).map(productTypeMapper::toModel);
  }

  public Uni<Long> create(ProductType productType) {
    ProductTypeEntity productTypeEntity = productTypeMapper.toEntity(productType);

    return productTypeRepository.persistAndFlush(productTypeEntity).map(ProductTypeEntity::getId);
  }

  public Uni<Void> delete(Long id) {
    return Panache.withTransaction(
        () -> productTypeRepository.deleteById(id).replaceWith(() -> null));
  }

//  public Uni<ProductType> update(ProductType productType) {
//    return Panache.withTransaction(
//            () -> productTypeRepository.update(id));
//  }
}
