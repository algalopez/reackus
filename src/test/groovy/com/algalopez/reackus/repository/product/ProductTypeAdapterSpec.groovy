package com.algalopez.reackus.repository.product


import com.algalopez.reackus.core.model.ProductType
import io.smallrye.mutiny.Uni
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class ProductTypeAdapterSpec extends Specification {

    private ProductTypeRepository productTypeRepository = Mock(ProductTypeRepository)
    private ProductTypeMapper productTypeMapper = Mappers.getMapper(ProductTypeMapper.class)
    private ProductTypeAdapter productTypeAdapter

    def setup() {
        productTypeAdapter = new ProductTypeAdapter(productTypeRepository, productTypeMapper)
    }

    void 'find existing product type by Id'() {
        given: 'A existing product type'
        def productId = 1L
        def existingProductType = new ProductTypeEntity(productId, "product type 1")

        when:
        def actual = productTypeAdapter.findById(productId).await().indefinitely()

        then:
        1 * productTypeRepository.findById(productId) >> Uni.createFrom().item(existingProductType)
        actual == new ProductType(existingProductType.getId(), existingProductType.getName())
    }
}
