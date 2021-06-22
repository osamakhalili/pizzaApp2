package com.pizza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pizza.app.model.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
     @Query(value="select * from Pizza p where p.name like %:keyword% or p.pizza_type like %:keyword% or p.pizza_size like %:keyword% ",nativeQuery = true)
     List<Pizza> findByKeyword(@Param("keyword")String keyword);
}
