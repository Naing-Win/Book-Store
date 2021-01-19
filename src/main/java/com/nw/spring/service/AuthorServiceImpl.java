package com.nw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nw.spring.models.Author;
import com.nw.spring.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	/*
	 * @Autowired private BookRepository bookRepository;
	 */
	@Override
	public void save(Author author) {
		// TODO Auto-generated method stub
		//Optional<Book> b = bookRepository.findById(book.getId());
		
		authorRepository.save(author);
	}

	@Override
	public List<Author> findAllAuthor() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}
	
	 @Override
	    public Optional<Author> getAuthorById(long id) {
	        return authorRepository.findById(id);
	    }

}
