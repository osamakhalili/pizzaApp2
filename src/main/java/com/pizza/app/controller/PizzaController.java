package com.pizza.app.controller;

import java.util.List;

import com.pizza.app.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pizza.app.model.Pizza;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	
	// display list of pizzas
	@GetMapping("/")
	public String viewHomePage(Model model,String keyword) {

		return findPaginated(1, "name", "asc", model,keyword);
	}
	
	@GetMapping("/showNewPizzaForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		return "new_pizza";
	}
	
	@PostMapping("/savePizza")
	public String saveEmployee(@ModelAttribute("pizza") Pizza pizza) {
		// save pizza to database
		pizzaService.savePizza(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Pizza from the service
		Pizza pizza = pizzaService.getPizzaById(id);
		
		// set pizza as a model attribute to pre-populate the form
		model.addAttribute("pizza", pizza);
		return "update_pizza";
	}
	
	@GetMapping("/deletePizza/{id}")
	public String deletePizza(@PathVariable (value = "id") long id) {
		
		// call delete pizza method
		this.pizzaService.deletePizzaById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,String keyword) {
		int pageSize = 5;
		
		Page<Pizza> page = pizzaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Pizza> listPizzas ;


		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		if (keyword !=null){

			listPizzas = pizzaService.findByKeyword(keyword);
		}else {

			listPizzas = page.getContent();
		}
		model.addAttribute("listPizzas", listPizzas);

		return "index";
	}

}
