package com.insta.fjee.library.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.dto.AuthorDTO;
import com.insta.fjee.library.dto.BookDTO;
import com.insta.fjee.library.dto.ExemplaryDTO;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.exception.EntityNotFoundException;

@Remote
public interface ILibraryService { 
	
	public List<BookDTO> searchBookByName(String name);

	public BookDTO findBookByISBN(String isbn);
	
	public List<BookDTO> searchBookByAuthor(String lastName, String firstName);

	public List<AuthorDTO> searchAuthorByLastName(String lastName);
	
	public List<AuthorDTO> searchAuthorByFirstName(String firstName);

	public List<AuthorDTO> searchAuthorByBookName(String bookName);
	
	long bookCount();
	
	/**
	 * 	Create a new author
	 * 
	 * @param in - author
	 * @return author created
	 */
	public AuthorDTO createAuthor(AuthorDTO authorDTO);
	
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
}
