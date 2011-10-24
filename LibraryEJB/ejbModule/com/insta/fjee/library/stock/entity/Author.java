package com.insta.fjee.library.stock.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AUTHOR database table.
 * 
 */
@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="author")
	private Set<Book> books;

    public Author() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}