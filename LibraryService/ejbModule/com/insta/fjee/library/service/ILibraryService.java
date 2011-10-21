package com.insta.fjee.library.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.dto.ExemplaryDTO;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.exception.EntityNotFoundException;

@Remote
public interface ILibraryService { 
	
	public List<BookDto> searchBookByName(String name);

	public BookDto findBookByISBN(String isbn);
	
	public List<BookDto> searchBookByAuthor(String lastName, String firstName);

	public List<AuthorDto> searchAuthorByLastName(String lastName);
	
	public List<AuthorDto> searchAuthorByFirstName(String firstName);

	public List<AuthorDto> searchAuthorByBookName(String bookName);
	
	long bookCount();
	
	/**
	 * 	Create a new author
	 * 
	 * @param in - author
	 * @return author created
	 */
	public AuthorDto createAuthor(AuthorDto in);
	
	/**
	 * 	Update a author
	 * 
	 * @param in - author
	 * @return author updated
	 * @throws EntityNotFoundException
	 */
	public AuthorDto updateAuthor(AuthorDto in) throws EntityNotFoundException;
	
	/**
	 * 	Delete a author
	 * 
	 * @param in - author
	 * @throws EntityNotFoundException
	 */
	public void deleteAuthor(AuthorDto in) throws EntityNotFoundException;

	/**
	 * 	Add a book with a number of copies
	 * 
	 * @param in - book
	 * @param nbExemplary
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public BookDto addBook(BookDto in, int exemplary) throws EntityNotFoundException;
	
	/**
	 * Add a number of copies
	 * 
	 * @param in
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public ExemplaryDTO addExemplary(ExemplaryDTO in) throws EntityNotFoundException, BookNotFoundException;

	
	/**
	 * 	Delete a number of copies
	 * 
	 * @param in
	 * @return
	 * @throws EntityNotFoundException
	 * @throws BookNotFoundException
	 */
	public ExemplaryDTO deleteExemplary(ExemplaryDTO in) throws EntityNotFoundException, BookNotFoundException;
}
