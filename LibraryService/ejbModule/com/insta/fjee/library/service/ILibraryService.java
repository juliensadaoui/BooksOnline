package com.insta.fjee.library.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.exception.EntityNotFoundException;

@Remote
public interface ILibraryService { 
	
	public List<BookDto> searchBookByName(String name);

	public List<BookDto> searchBookByISBN(String isbn);
	
	public List<BookDto> searchBookByAuthor(String lastName, String firstName);

	public List<AuthorDto> searchAuthorByLastName(String lastName);
	
	public List<AuthorDto> searchAuthorByFirstName(String firstName);

	public List<AuthorDto> searchAuthorByBookName(String bookName);
	
	long bookCount();
	
	public AuthorDto storeAuthor(AuthorDto in) throws EntityNotFoundException;

}
