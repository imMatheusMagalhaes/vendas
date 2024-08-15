package com.devmatheus.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmatheus.vendas.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
