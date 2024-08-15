package com.devmatheus.vendas.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum OrderStatus {
  WAITING_PAYMENT("waiting_payment"),
  PAID("paid"),
  SHIPPED("shipped"),
  DELIVERED("delivered"),
  CANCELED("canceled");

  private String status;

  private OrderStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public static OrderStatus statusOf(String status) {
    for (OrderStatus value : OrderStatus.values()) {
      if (value.getStatus().equalsIgnoreCase(status)) {
        return value;
      }
    }
    throw new IllegalIdentifierException("Invalid OrderStatus");
  }
}
