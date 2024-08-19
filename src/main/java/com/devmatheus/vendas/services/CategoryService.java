package com.devmatheus.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.Category;
import com.devmatheus.vendas.repositories.CategoryRepository;
import com.devmatheus.vendas.services.exceptions.DatabaseException;
import com.devmatheus.vendas.services.exceptions.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  public Category create(Category category) {
    return repository.save(category);
  }

  public Category update(Long id, Category update) {
    try {
      Category category = repository.getReferenceById(id);
      updateData(category, update);
      return repository.save(category);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(id);
    }
  }

  private void updateData(Category category, Category update) {
    category.setName(update.getName());
  }

  public List<Category> findAll() {
    return repository.findAll();
  }

  public Category findById(Long id) {
    Optional<Category> user = repository.findById(id);
    return user.orElseThrow(() -> new NotFoundException(id));
  }

  public void delete(Long id) {
    try {
      repository.findById(id).orElseThrow(() -> new NotFoundException(id));
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
