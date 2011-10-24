package com.insta.fjee.library.stock.exception;

import java.awt.print.Book;

public class BookNotFoundException extends Exception
{

	private static final long serialVersionUID = 1L;

	private String className;
	private String isbn;

	public BookNotFoundException(String isbn) {
		super("Entity of class " + Book.class.getSimpleName() + " with isbn " + isbn + " not found");
		this.className = Book.class.getSimpleName();
		this.isbn = isbn;
	}

	public String getClassName()
	{
		return className;
	}

	public String getIsbn()
	{
		return isbn;
	}

}
