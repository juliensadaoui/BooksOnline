package com.insta.fjee.library.stock.eao;

import java.util.List;

import com.insta.fjee.library.stock.entity.Author;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;

public interface IAuthorEAO 
{
	/**
	 * 	Save or update a author
	 * 
	 * @param author 
	 */
	public void saveOrUpdate(Author author);
	
	/**
	 * 	Delete a author
	 * 
	 * @param author
	 */
	public void delete(Author author);
	
	/**
	 * 	Receive a author 
	 * 
	 * @param id - id of the book
	 * @return
	 * @throws EntityNotFoundException
	 */
	public Author findOrFail(Integer id) throws EntityNotFoundException;
	
	/**
	 * 	Receive a author 
	 * 
	 * @param id - id of the book
	 * @return
	 */
	public Author find(Integer id);

	/**
	 * 	Search authors by firstname
	 * 
	 * @param firstName - firstname of the author
	 * @return authors
	 */
	public List<Author> findAuthorByFirstName(String firstName);
	
	/**
	 * 	Search authors by lastName
	 * 
	 * @param lastName - lastName of the author
	 * @return authors
	 */
	public List<Author> findAuthorByLastName(String lastName);
	
	/**
	 * 	Search authors by book
	 * 
	 * @param bookName - name of the book
	 * @return authors
	 */
	public List<Author> findAuthorByBookName(String bookName);
}
