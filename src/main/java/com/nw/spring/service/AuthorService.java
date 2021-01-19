package com.nw.spring.service;

import java.util.List;
import java.util.Optional;

import com.nw.spring.models.Author;

public interface AuthorService {
	
	public void save(Author author);
	public List<Author> findAllAuthor();
	Optional<Author> getAuthorById(long id);

}
