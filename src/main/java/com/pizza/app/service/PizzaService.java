package com.pizza.app.service;

import java.util.List;
import java.util.Optional;

import com.pizza.app.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pizza.app.model.Pizza;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;


	public List<Pizza> getAllPizza() {
		return pizzaRepository.findAll();
	}

	public void savePizza(Pizza pizza) {
		this.pizzaRepository.save(pizza);
	}

	public Pizza getPizzaById(long id) {
		Optional<Pizza> optional = pizzaRepository.findById(id);
		Pizza pizza = null;
		if (optional.isPresent()) {
			pizza = optional.get();
		} else {
			throw new RuntimeException(" Pizza not found for id :: " + id);
		}
		return pizza;
	}

	public void deletePizzaById(long id) {
		this.pizzaRepository.deleteById(id);
	}

	public Page<Pizza> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.pizzaRepository.findAll(pageable);
	}

	public List<Pizza> findByKeyword(String keyword) {
		return pizzaRepository.findByKeyword(keyword);
	}
}
