package com.algalopez.reackus.core.actor.product

import com.algalopez.reackus.core.model.Product
import io.quarkus.test.junit.QuarkusTest
import spock.lang.Specification

@QuarkusTest
class GetProductActorSpec extends Specification {

    private GetProductActor getProductActor = new GetProductActor()

    def "get existing product"() {
        when:
        Product actual = getProductActor.run(1).await().indefinitely()

        then:
        def expected = new Product(1, "product 1")
        actual == expected
    }
}
