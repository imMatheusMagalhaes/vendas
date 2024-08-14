package com.devmatheus.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmatheus.vendas.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
