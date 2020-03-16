package com.nw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nw.spring.exception.ResourceNotFoundException;
import com.nw.spring.models.Book;
import com.nw.spring.service.BookServiceImpl;
import com.nw.spring.service.CategoryServiceImpl;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private BookServiceImpl bookService;

	@ModelAttribute
	public void categoriesAttributes(Model model) {
		model.addAttribute("categories", categoryService.findAll());
	}

	@GetMapping("/create")
	public String createBook(Book book, Model model) {
		return "book/create";
	}

	@PostMapping("/create")
	public String processCreateBook(@Valid Book book, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "book/create";
		}
		bookService.save(book);
		redirectAttributes.addFlashAttribute("message", "Book has been created.");
		return "redirect:/books";
	}

	@GetMapping
	public String findAll(Model model, @ModelAttribute("message") String message, @RequestParam(value = "category", required = false) String category) {
		if (category != null && !category.isEmpty()) {
			model.addAttribute("books", bookService.findByCategoryName(category));
		} else {
			model.addAttribute("books", bookService.findAll());
		}
		return "book/findAll";
	}

	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable long id, Model model) throws ResourceNotFoundException {
		Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.addAttribute("book", book);
		return "book/edit";
	}

	@PostMapping("/edit/{id}")
	public String updateBook(@PathVariable long id, @Valid Book book, BindingResult result, RedirectAttributes redirectAttributes) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			return "book/edit";
		}
		Book bk = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		bk.setId(book.getId());
		bk.setCategory(book.getCategory());
		bk.setAuthor(book.getAuthor());
		bk.setDescription(book.getDescription());
		bk.setEdition(book.getEdition());
		bk.setTitle(book.getTitle());
		bookService.save(bk);
		redirectAttributes.addFlashAttribute("message", "Book with ID " + book.getId() + " has been updated!");
		return "redirect:/books";
	}

	@GetMapping("/view/{id}")
	public String viewBookById(@PathVariable long id, Model model) throws ResourceNotFoundException {
		Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.addAttribute("book", book);
		return "book/view";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model) throws ResourceNotFoundException {
		Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.addAttribute("book", book);
		return "book/delete";
	}

	@PostMapping("/delete/{id}")
	public String processDeleteBook(@PathVariable long id, RedirectAttributes redirectAttributes)
			throws ResourceNotFoundException {
		//Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		bookService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Book with ID " + id + " has been deleted.");
		return "redirect:/books";
	}

}
