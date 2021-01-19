package com.nw.spring.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Title is required.")
	private String title;
	private String edition;
	//private String author;
	private String description;
	//private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;
	
	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Author> authors;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/*
	 * public String getAuthor() { return author; } public void setAuthor(String
	 * author) { this.author = author; }
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	*/
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		if(authors == null) {
			authors = new ArrayList<>();
		}
		authors.add(author);
	}
	
	public boolean hasAuthor(Author author) {
		for(Author a : getAuthors()) {
			if(a.getId() == author.getId()) {
				return true;
			}
		}
		return false;
	}
}
