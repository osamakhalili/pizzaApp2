package com.pizza.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pizza.app.model.Pizza;

public interface PizzaService {
	List<Pizza> getAllPizza();
	void savePizza(Pizza pizza);
	Pizza getPizzaById(long id);
	void deletePizzaById(long id);
	Page<Pizza> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
