package com.devmatheus.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.Order;
import com.devmatheus.vendas.repositories.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository repository;

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> user = repository.findById(id);
    return user.get();
  }
}
