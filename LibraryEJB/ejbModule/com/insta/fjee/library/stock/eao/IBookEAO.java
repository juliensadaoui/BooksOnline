package com.insta.fjee.library.stock.eao;

import java.util.List;

import com.insta.fjee.library.stock.entity.Book;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;

public interface IBookEAO 
{
	/** 
	 * 	Compte le nombre de livres dans la zone de stockage
	 * 
	 * @return - nombre de livres
	 */
	public long countBooks();
	
	/**
	 * 	Compte le nombre d'exemplaire d'un livre
	 * 
	 * @param isbn
	 * @return
	 */
	public long countBooks(String isbn);
	
	/**
	 * 	Save or update a book
	 * 
	 * @param book 
	 */
	public void saveOrUpdate(Book book);
	
	/**
	 * 	Delete a book
	 * 
	 * @param bo
	 */
	public void delete(Book book);
	
	/**
	 * 	Receive a book 
	 * 
	 * @param id - id of the book
	 * @return
	 * @throws EntityNotFoundException
	 */
	public Book findOrFail(Integer id) throws EntityNotFoundException;
	
	/**
	 * 	Receive a book 
	 * 
	 * @param id - id of the book
	 * @return
	 */
	public Book find(Integer id);
	
	/**
	 * 	Search a book by isbn
	 * 
	 * @param isbn - isbn of the book
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
