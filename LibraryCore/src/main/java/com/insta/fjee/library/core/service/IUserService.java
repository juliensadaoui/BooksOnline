package com.insta.fjee.library.core.service;

import java.util.List;

import javax.jws.WebService;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;

@WebService
public interface IUserService {

	/**
	 * 	Create a account of a new user
	 * 
	 * @param userDTO - new user
	 * @return user
	 */
	public UserDTO createUser(UserDTO userDTO);
	
	/**
	 * 	Search a author by lastname
	 * 
	 * @param lastName - lastname of the author
	 * @return list of authors
	 */
	public List<AuthorDTO> searchAuthorByLastName(String lastName);
	
	/**
	 * 	Search a author by firstname
	 * 
	 * @param firstName - firstname of the author
	 * @return list of authors
	 */
	public List<AuthorDTO> searchAuthorByFirstName(String firstName);

	/**
	 * 	Search a author by book
	 * 
	 * @param bookName - name of the book
	 * @return list of authors
	 */
	public List<AuthorDTO> searchAuthorByBookName(String bookName);
	
	/**
	 * 	Search a book by isbn
	 * 
	 * @param isbn - isbn of the book
	 * @return list of books
	 */
	public List<BookDTO> searchBookByISBN(String isbn);
	
	/**
	 * 	Search a book by name
	 * 
	 * @param name - name of the book
	 * @return list of books
	 */
	public List<BookDTO> searchBookByName(String name);
	
	/**
	 * 	Search a book by genre
	 * 
	 * @param genre - genre of the book
	 * @return
	 */
	public List<BookDTO> searchBookByGenre(String genre);
	
	/**
	 * 	Search a book by author
	 * 
	 * @param lastName - lastname of the author
	 * @param firstName - firstname of the author
	 * @return list of books
	 */
	public List<BookDTO> searchBookByAuthor(String lastName, String firstName);
}
