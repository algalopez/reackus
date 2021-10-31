package com.algalopez.reackus.api.common;

public abstract class BaseInteractor<I, O> {

    protected abstract O run(I request);
}
