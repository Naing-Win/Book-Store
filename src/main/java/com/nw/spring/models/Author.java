package com.nw.spring.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String nRC;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
   // @Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	//@NotNull(message = "Please select your date of birth.")
	private Date dateOfBirth;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(
	//		name = "author_book",
	//		joinColumns = @JoinColumn(name = "author_id"),
	//		inverseJoinColumns = @JoinColumn(name = "book_id")
	//		)
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Book> books;

	public Author() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getnRC() {
		return nRC;
	}

	public void setnRC(String nRC) {
		this.nRC = nRC;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		if(books == null) {
			books = new ArrayList<>();
		}
		books.add(book);
	}
}
