package com.insta.fjee.library.stock.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOOK database table.
 * 
 */
@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private int exemplary;

	private String genre;

	private String isbn;

	private String name;

	//bi-directional many-to-one association to Author
    @ManyToOne
	@JoinColumn(name="ID_AUTHOR")
	private Author author;

    public Book() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getExemplary() {
		return this.exemplary;
	}

	public void setExemplary(int exemplary) {
		this.exemplary = exemplary;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}