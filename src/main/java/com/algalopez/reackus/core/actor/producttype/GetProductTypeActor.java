package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.shared.BaseInteractor;
import com.algalopez.reackus.core.exception.ResourceNotFoundException;
import com.algalopez.reackus.core.model.ProductType;
import com.algalopez.reackus.repository.product.ProductTypeAdapter;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetProductTypeActor extends BaseInteractor<Long, Uni<ProductType>> {

  private final ProductTypeAdapter productTypeAdapter;

  public GetProductTypeActor(ProductTypeAdapter productTypeAdapter) {
    this.productTypeAdapter = productTypeAdapter;
  }

  @Override
  public Uni<ProductType> run(Long id) {
    return productTypeAdapter
        .findById(id)
        .onItem()
        .ifNull()
        .failWith(new ResourceNotFoundException());
  }
}
