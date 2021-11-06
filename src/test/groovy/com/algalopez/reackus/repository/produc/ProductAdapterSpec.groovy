package com.algalopez.reackus.repository.produc

import com.algalopez.reackus.core.model.Product
import com.algalopez.reackus.core.model.ProductType
import com.algalopez.reackus.repository.product.*
import io.smallrye.mutiny.Uni
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class ProductAdapterSpec extends Specification {

    private ProductRepository productRepository = Mock(ProductRepository)
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class)
    private ProductAdapter productAdapter

    def setup() {
        productAdapter = new ProductAdapter(productRepository, productMapper)
    }

    void 'find existing product by Id'() {
        given: 'A existing product'
        def productId = 1L
        def existingProductType = new ProductTypeEntity(productId, "product type 1")
        def existingProduct = new ProductEntity(productId, existingProductType, "product 1")

        when:
        def actual = productAdapter.findById(productId).await().indefinitely()

        then:
        1 * productRepository.findById(productId) >> Uni.createFrom().item(existingProduct)
        actual == Product.builder()
                .id(existingProduct.getId())
                .productType(new ProductType(existingProductType.getId(), existingProductType.getName()))
                .name(existingProduct.getName())
                .build()
    }
}
