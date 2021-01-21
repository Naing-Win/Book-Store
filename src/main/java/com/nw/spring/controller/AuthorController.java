package com.nw.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nw.spring.models.Author;
import com.nw.spring.service.AuthorServiceImpl;

@Controller
public class AuthorController {

	/*
	 * @Autowired private BookServiceImpl bookService;
	 */
	
	@Autowired
	private AuthorServiceImpl authorService;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@GetMapping("/authors")
	public String getAllAuthor(Model model) {
		model.addAttribute("authors", authorService.findAllAuthor());
		//model.addAttribute("books", bookService.findAll());
		return "author/find-all";
	}
	
	@GetMapping("/author/add")
	public String showAuthorForm(Model model) {
		model.addAttribute("author", new Author());
		return "author/add-author";
	}
	
	@PostMapping("/author/add")
	public String createAuthor(@Valid Author author, BindingResult result) {
		if(result.hasErrors()) {
			return "author/add-author";
		}
		authorService.save(author);
		return "redirect:/authors";
	}
	
	@GetMapping("/author/view/{id}")
	public String viewAuthorById(@PathVariable long id, Model model) {
		Author author = authorService.findById(id);
		model.addAttribute("author", author);
		return "author/view_author";
	}
	
	@GetMapping("/author/edit/{id}")
	public String editAuthorById(@PathVariable long id, Model model) {
		Author author = authorService.findById(id);
		model.addAttribute("author", author);
		return "author/add-author";
	}
	
	@PostMapping("/author/edit/{id}")
	public String editProcessAuthorById(@PathVariable long id, @Valid Author author, BindingResult result) {
		if (result.hasErrors()) {
			return "author/add-author";
		}
		Author au = authorService.findById(id);
		au.setId(author.getId());
		au.setName(author.getName());
		au.setAddress(author.getAddress());
		au.setDateOfBirth(author.getDateOfBirth());
		au.setEmail(author.getEmail());
		au.setnRC(author.getnRC());
		au.setPhone(author.getPhone());
		authorService.save(au);
		return "redirect:/authors";
	}
}
