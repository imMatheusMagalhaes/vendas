package com.devmatheus.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmatheus.vendas.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
