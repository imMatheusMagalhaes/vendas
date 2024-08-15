package com.devmatheus.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.Product;
import com.devmatheus.vendas.services.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductResources {

  @Autowired
  ProductService service;

  @GetMapping
  ResponseEntity<List<Product>> findAll() {
    List<Product> users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<Product> findById(@PathVariable Long id) {
    Product user = service.findById(id);
    return ResponseEntity.ok().body(user);
  }

}
