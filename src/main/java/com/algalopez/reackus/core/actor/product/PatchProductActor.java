package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import com.algalopez.reackus.core.model.Product;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonMergePatch;

@ApplicationScoped
public class PatchProductActor extends BaseInteractor<Pair<Integer, JsonMergePatch>, Uni<Product>> {

    @Override
    public Uni<Product> run(Pair<Integer, JsonMergePatch> patch) {
        return Uni.createFrom().item(new Product(1, "product 1"));
    }

        /*
    @PatchMapping(path = ["/{id}"], consumes = ["application/merge-patch+json"])
fun jsonMergePatchBook(
    @PathVariable id: String,
    @RequestBody patch: JsonNode
): Mono<ResponseEntity<Book>> {
    return Mono.fromSupplier {
        val original: JsonNode = objectMapper.valueToTree(getBook(id))
        val patched: JsonNode = JsonMergePatch.fromJson(patch).apply(original)
        val patchedBook: Book =
            objectMapper.treeToValue(patched) ?: throw RuntimeException("Could not convert json back to book")
        updateBook(patchedBook)
        ResponseEntity.ok(patchedBook)
    }
}
     */
}
