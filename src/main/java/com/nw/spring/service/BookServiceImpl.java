package com.nw.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nw.spring.models.Book;
import com.nw.spring.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public Iterable<Book> findByCategoryName(String category) {
		// TODO Auto-generated method stub
		return bookRepository.findByCategoryName(category);
	}

	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		bookRepository.save(book);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
	}

}
