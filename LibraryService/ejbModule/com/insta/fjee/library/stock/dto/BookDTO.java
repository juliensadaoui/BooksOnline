package com.insta.fjee.library.stock.dto;

public class BookDTO implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5364068601926663481L;

	private Integer id;

	private String genre;

	private String isbn;

	private String name;
	
	private Integer authorId;
	
	private String authorName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", genre=" + genre + ", isbn=" + isbn
				+ ", name=" + name + ", authorId=" + authorId + ", authorName="
				+ authorName + "]";
	}
}
