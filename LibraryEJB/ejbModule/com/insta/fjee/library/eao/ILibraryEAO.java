package com.insta.fjee.library.eao;

import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;

@Remote
public interface ILibraryEAO {
	
	/** 
	 * 	Compte le nombre de livres dans la zone de stockage
	 * 
	 * @return - nombre de livres
	 */
	public long countBooks();
	
	/**
	 * 	
	 * 
	 * @param firstName
	 * @return
	 */
	public List<Author> findAuthorByFirstName(String firstName);
	
	/**
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Author> findAuthorByLastName(String lastName);
	
	/**
	 * 
	 * 
	 * @param bookName
	 * @return
	 */
	public List<Author> findAuthorByBookName(String bookName);
	
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Book findBookByISBN(String isbn) throws BookNotFoundException;
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Book> findBookByName(String name);
	
	/**
	 * 
	 * 
	 * @param genre
	 * @return
	 */
	public List<Book> findBookByGenre(String genre);
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<Book> findBookByAuthor(String firstName, String lastName);
	
}
