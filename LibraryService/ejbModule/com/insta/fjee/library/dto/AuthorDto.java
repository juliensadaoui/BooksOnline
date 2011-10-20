package com.insta.fjee.library.dto;

import java.util.List;

public class AuthorDto implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1984086140456124018L;

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private List<String> booksIsbn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getBooks() {
		return booksIsbn;
	}

	public void setBooks(List<String> books) {
		this.booksIsbn = books;
	}

	@Override
	public String toString() {
		return "AuthorDto [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", books=" + booksIsbn + "]";
	}
}