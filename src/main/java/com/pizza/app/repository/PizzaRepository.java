package com.pizza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizza.app.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{

}
