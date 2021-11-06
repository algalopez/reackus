package com.algalopez.reackus.core.actor.product

import com.algalopez.reackus.core.actor.producttype.GetProductTypeActor
import com.algalopez.reackus.core.model.ProductType
import spock.lang.Specification

class GetProductTypeActorSpec extends Specification {

    private GetProductTypeActor getProductTypeActor = new GetProductTypeActor()

    def "get existing product type"() {
        when:
        ProductType actual = getProductTypeActor.run(1).await().indefinitely()

        then:
        def expected = new ProductType(1, "product 1")
        actual == expected
    }
}
