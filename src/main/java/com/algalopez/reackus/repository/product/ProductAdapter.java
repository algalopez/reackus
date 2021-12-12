package com.algalopez.reackus.repository.product;

import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProductAdapter {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductAdapter(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  public Uni<List<Product>> findAll() {
    return productRepository
        .findAll()
        .list()
        .map(productList -> productList.stream().map(productMapper::toModel).toList());
  }

  public Uni<Product> findById(Long id) {
    return productRepository.findById(id).map(productMapper::toModel);
  }

  public Uni<Long> create(Product product) {
    ProductEntity productEntity = productMapper.toEntity(product);
    return productRepository.persist(productEntity).map(ProductEntity::getId);
  }

  public Uni<Void> delete(Long id) {
    productRepository.deleteById(id);
    return Uni.createFrom().item(() -> null);
  }
}
