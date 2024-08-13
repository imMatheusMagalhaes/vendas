package com.devmatheus.vendas.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmatheus.vendas.entities.User;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

  @GetMapping
  ResponseEntity<User> findAll() {
    User u = new User(1l, "Matheus", "matheus@gmail.com", "999999", "12345");
    return ResponseEntity.ok().body(u);
  }
}
