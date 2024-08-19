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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devmatheus.vendas.entities.Category;
import com.devmatheus.vendas.services.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryResources {

  @Autowired
  CategoryService service;

  @PostMapping
  public ResponseEntity<Category> create(@RequestBody Category category) {
    category = service.create(category);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
    return ResponseEntity.created(uri).body(category);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Category> create(@PathVariable Long id, @RequestBody Category category) {
    category = service.update(id, category);
    return ResponseEntity.ok().body(category);
  }

  @GetMapping
  ResponseEntity<List<Category>> findAll() {
    List<Category> users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<Category> findById(@PathVariable Long id) {
    Category user = service.findById(id);
    return ResponseEntity.ok().body(user);
  }

  @DeleteMapping(value = "/{id}")
  ResponseEntity<String> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
