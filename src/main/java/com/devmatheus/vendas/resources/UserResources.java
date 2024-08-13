package com.devmatheus.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.User;
import com.devmatheus.vendas.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

  @Autowired
  UserService service;

  @GetMapping
  ResponseEntity<List<User>> findAll() {
    List<User> users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<User> findById(@PathVariable Long id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(user);
  }

}
