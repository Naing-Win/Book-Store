package com.nw.spring.service;

import java.util.Optional;

import com.nw.spring.models.Book;

public interface BookService {
	
	Iterable<Book> findAll();
	Optional<Book> findById(long id);
	Iterable<Book> findByCategoryName(String category);
	void save(Book book);
	void delete(long id);

}
