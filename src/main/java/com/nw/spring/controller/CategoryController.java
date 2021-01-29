package com.nw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nw.spring.exception.ResourceNotFoundException;
import com.nw.spring.models.Category;
import com.nw.spring.service.CategoryServiceImpl;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@GetMapping({"", "/"})
	public String findAll(Model model) {
		Iterable<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "category/findAll";
	}
	
	@GetMapping("/create")
	public String createCategory(Model model) {
		model.addAttribute("category", new Category());
		return "category/create";
	}
	
	@PostMapping("/create")
	public String processCreateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "category/create";
		}
		categoryService.save(category);
		redirectAttributes.addFlashAttribute("message", "Category has been created!");
		return "redirect:/categories";
	}
	
	@GetMapping("/view/{id}")
	public String viewCategoryById(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
		Category category = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.addAttribute("category", category);
		return "category/view";
	}
	
	@GetMapping("/edit/{id}")
	public String editCategory(@PathVariable long id, Model model) throws ResourceNotFoundException {
		Category category = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.addAttribute("category", category);
		return "category/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String updateCategory(@PathVariable long id, @Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			return "category/edit";
		}
		Category c = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		c.setId(category.getId());
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		categoryService.save(c);
		redirectAttributes.addFlashAttribute("message", "Category with ID " + category.getId() + " has been updated!");
		return "redirect:/categories";
	}
	
}
