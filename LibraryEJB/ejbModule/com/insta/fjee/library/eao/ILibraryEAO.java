package com.insta.fjee.library.eao;

import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.EntityNotFoundException;
import com.insta.fjee.library.util.jpa.Entity;

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
	public Book findBookByISBN(String isbn);
	
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
	
	/**
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	public <T extends Entity> T findOrFail(Class<T> clazz, Integer id) throws EntityNotFoundException;

	/**
	 * 
	 * @param entity
	 */
	public <T extends Entity> void persist(T entity);
}
