package com.algalopez.reackus.core.actor.producttype;

import com.algalopez.reackus.core.shared.BaseInteractor;
import com.algalopez.reackus.repository.product.ProductTypeAdapter;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteProductTypeActor extends BaseInteractor<Long, Uni<Void>> {

  private final ProductTypeAdapter productTypeAdapter;

  public DeleteProductTypeActor(ProductTypeAdapter productTypeAdapter) {
    this.productTypeAdapter = productTypeAdapter;
  }

  @Override
  public Uni<Void> run(Long id) {
    return productTypeAdapter.delete(id);
  }
}
