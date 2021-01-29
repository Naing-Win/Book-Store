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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Enter your name.")
	private String name;

	@NotBlank(message = "Enter your phone number.")
	private String phone;

	@Email(message = "Enter valid email.")
	@NotBlank(message = "Enter valid email.")
	private String email;

	@NotBlank(message = "Enter your address.")
	private String address;

	@NotBlank(message = "Enter your N.R.C number.")
	private String nRC;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	// @Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Please select your date of birth.")
	private Date dateOfBirth;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
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
		if (books == null) {
			books = new ArrayList<>();
		}
		books.add(book);
	}
}
