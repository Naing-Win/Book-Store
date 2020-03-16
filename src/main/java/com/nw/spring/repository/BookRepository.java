package com.nw.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
	Iterable<Book> findByCategoryName(String category);
}
