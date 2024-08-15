package com.devmatheus.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.Category;
import com.devmatheus.vendas.services.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryResources {

  @Autowired
  CategoryService service;

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

}
