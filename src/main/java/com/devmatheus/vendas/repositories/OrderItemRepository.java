package com.devmatheus.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmatheus.vendas.entities.OrderItem;
import com.devmatheus.vendas.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
