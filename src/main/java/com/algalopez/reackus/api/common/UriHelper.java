package com.algalopez.reackus.api.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@UtilityClass
public class UriHelper {

    public static URI buildUri(String resource) {
        try {
            return new URI(resource);
        } catch (URISyntaxException e) {
            log.trace("", e);
            throw new UriException(e);
        }
    }

}
