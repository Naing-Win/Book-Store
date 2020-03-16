package com.nw.spring.service;

import java.util.Optional;

import com.nw.spring.models.Category;

public interface CategoryService {
	
	Iterable<Category> findAll();
	Optional<Category> findById(long id);
	void save(Category category);
	void deleteById(long id);

}
