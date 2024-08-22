package com.devmatheus.vendas.services.exceptions;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(){
      super("unauthorized");
  }
}
