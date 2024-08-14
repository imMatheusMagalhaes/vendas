package com.devmatheus.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.Order;
import com.devmatheus.vendas.services.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderResources {

  @Autowired
  OrderService service;

  @GetMapping
  ResponseEntity<List<Order>> findAll() {
    List<Order> users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<Order> findById(@PathVariable Long id) {
    Order user = service.findById(id);
    return ResponseEntity.ok().body(user);
  }

}
