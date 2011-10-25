package com.insta.fjee.library.stock.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.stock.dto.AuthorDTO;
import com.insta.fjee.library.stock.dto.BookDTO;
import com.insta.fjee.library.stock.dto.ExemplaryDTO;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;

@Remote
public interface ILibraryService { 
	
	/**
	 * 	Create a new author
	 * 
	 * @param in - author
	 * @return author created
	 */
	public AuthorDTO addAuthor(AuthorDTO authorDTO);
	
	/**
	 * 	Update a author
	 * 
	 * @param in - author
	 * @return author updated
	 * @throws EntityNotFoundException
	 */
	public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws EntityNotFoundException;
	
	/**
	 * 	Delete a author
	 * 
	 * @param in - author
	 * @throws EntityNotFoundException
	 */
	public void deleteAuthor(AuthorDTO authorDTO) throws EntityNotFoundException;
	
	public List<AuthorDTO> searchAuthorByLastName(String lastName);
	
	public List<AuthorDTO> searchAuthorByFirstName(String firstName);

	public List<AuthorDTO> searchAuthorByBookName(String bookName);
	
	
	/**
	 * 	Add a book with a number of copies
	 * 
	 * @param in - book
	 * @param nbExemplary
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public BookDTO addBook(BookDTO bookDTO, int exemplary) throws EntityNotFoundException;
	
	/**
	 * Add a number of copies
	 * 
	 * @param in
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public ExemplaryDTO addExemplary(ExemplaryDTO exemplaryDTO) throws EntityNotFoundException, BookNotFoundException;

	
	/**
	 * 	Delete a number of copies
	 * 
	 * @param in
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public ExemplaryDTO deleteExemplary(ExemplaryDTO exemplaryDTO) throws EntityNotFoundException, BookNotFoundException;

	/**
	 * 	Return the number of books
	 * 
	 * @return - number of books
	 */
	public long getCountBook();
	
	public BookDTO findBookByISBN(String isbn) throws BookNotFoundException;

	public List<BookDTO> searchBookByISBN(String isbn);
	
	public List<BookDTO> searchBookByName(String name);
	
	public List<BookDTO> searchBookByGenre(String genre);
	
	public List<BookDTO> searchBookByAuthor(String lastName, String firstName);
}
