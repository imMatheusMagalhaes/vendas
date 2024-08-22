package com.devmatheus.vendas.entities.dto;

import com.devmatheus.vendas.entities.enums.OrderStatus;

public record UpdateOrderStatusDto(OrderStatus status) {
}
