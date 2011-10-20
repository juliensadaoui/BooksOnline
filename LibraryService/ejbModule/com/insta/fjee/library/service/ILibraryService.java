package com.insta.fjee.library.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
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
	
	public void deleteAuthor(AuthorDto in);
}
