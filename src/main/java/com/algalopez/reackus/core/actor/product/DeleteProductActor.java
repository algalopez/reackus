package com.algalopez.reackus.core.actor.product;

import com.algalopez.reackus.api.common.BaseInteractor;
import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteProductActor extends BaseInteractor<Pair<Long, Long>, Uni<Void>> {

    @Override
    public Uni<Void> run(Pair<Long, Long> ignore) {
        return Uni.createFrom().item(() -> null);
    }
}
