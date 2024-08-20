package com.devmatheus.vendas.util;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GenerateURI {
  public static URI get(Object target, String path) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest().path(path)
        .buildAndExpand(target).toUri();
  }
}
