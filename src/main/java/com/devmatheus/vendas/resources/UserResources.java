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

import com.devmatheus.vendas.entities.User;
import com.devmatheus.vendas.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

  @Autowired
  UserService service;

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User user) {
    user = service.create(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> create(@PathVariable Long id, @RequestBody User user) {
    user = service.update(id, user);
    return ResponseEntity.ok().body(user);
  }

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

  @DeleteMapping(value = "/{id}")
  ResponseEntity<String> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
