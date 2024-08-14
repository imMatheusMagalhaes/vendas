package com.devmatheus.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmatheus.vendas.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
