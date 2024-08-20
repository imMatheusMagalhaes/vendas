package com.devmatheus.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.Product;
import com.devmatheus.vendas.repositories.ProductRepository;
import com.devmatheus.vendas.services.exceptions.DatabaseException;
import com.devmatheus.vendas.services.exceptions.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public Product create(Product product) {
    return repository.save(product);
  }

  public Product update(Long id, Product update) {
    try {
      Product product = repository.getReferenceById(id);
      updateData(product, update);
      return repository.save(product);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(id);
    }
  }

  private void updateData(Product product, Product update) {
    product.setName(update.getName());
    product.setDescription(update.getDescription());
    product.setPrice(update.getPrice());
    product.setImgUrl(update.getImgUrl());
  }

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(Long id) {
    Optional<Product> user = repository.findById(id);
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
