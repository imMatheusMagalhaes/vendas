package com.devmatheus.vendas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.Product;
import com.devmatheus.vendas.services.ProductService;
import com.devmatheus.vendas.util.GenerateURI;

@RestController
@RequestMapping(value = "/product")
public class ProductResources {

  @Autowired
  ProductService service;

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    product = service.create(product);
    URI uri = GenerateURI.get(product.getId(), "/{id}");
    return ResponseEntity.created(uri).body(product);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
    product = service.update(id, product);
    return ResponseEntity.ok().body(product);
  }

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

  @DeleteMapping(value = "/{id}")
  ResponseEntity<Object> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
