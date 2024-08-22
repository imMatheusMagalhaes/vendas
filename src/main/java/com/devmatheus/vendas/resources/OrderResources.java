package com.devmatheus.vendas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.Order;
import com.devmatheus.vendas.entities.dto.CreateOrderDto;
import com.devmatheus.vendas.entities.dto.UpdateOrderStatusDto;
import com.devmatheus.vendas.entities.enums.OrderStatus;
import com.devmatheus.vendas.services.OrderService;
import com.devmatheus.vendas.util.GenerateURI;

@RestController
@RequestMapping(value = "/order")
public class OrderResources {

  @Autowired
  OrderService service;

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody List<CreateOrderDto> items) {
    Order order = service.create(items);
    URI uri = GenerateURI.get(order.getId(), "/{id}");
    return ResponseEntity.created(uri).body(order);
  }

  @PutMapping(value = "status/{id}")
  public ResponseEntity<OrderStatus> updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusDto dto) {
    OrderStatus status = service.updateStatus(id, dto);
    return ResponseEntity.ok().body(status);
  }

  @PutMapping(value = "pay/{id}")
  public ResponseEntity<Object> pay(@PathVariable Long id) {
    service.pay(id);
    return ResponseEntity.noContent().build();
  }

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
