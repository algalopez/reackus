package com.algalopez.reackus.core.shared;

public abstract class BaseInteractor<I, O> {

    protected abstract O run(I request);
}
