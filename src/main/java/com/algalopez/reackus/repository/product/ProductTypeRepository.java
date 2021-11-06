package com.algalopez.reackus.repository.product;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @see ProductTypeEntity
 */
@ApplicationScoped
public class ProductTypeRepository implements PanacheRepository<ProductTypeEntity> {
}
