package com.devmatheus.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.User;
import com.devmatheus.vendas.repositories.UserRepository;
import com.devmatheus.vendas.services.exceptions.DatabaseException;
import com.devmatheus.vendas.services.exceptions.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public User create(User user){
    return repository.save(user);
  }

  public User update(Long id, User update){
    try {
      User user = repository.getReferenceById(id);
      updateData(user, update);
      return repository.save(user);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(id);
    }
  }

  private void updateData(User user, User update) {
    user.setName(update.getName());
    user.setEmail(update.getEmail());
    user.setPhone(update.getPhone());
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    Optional<User> user = repository.findById(id);
    return user.orElseThrow(() -> new NotFoundException(id));
  }

  public void delete(Long id){
    try {
      repository.findById(id).orElseThrow(() -> new NotFoundException(id));
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
