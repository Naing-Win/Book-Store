package com.nw.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
