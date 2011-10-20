package com.insta.fjee.library.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.eao.ILibraryEAO;
import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.exception.EntityNotFoundException;
import com.insta.fjee.library.util.Conversion;

/**
 * Session Bean implementation class LibraryBean
 */
@Stateless
@LocalBean
@WebService
public class LibraryBean implements ILibraryService 
{
	@EJB
	ILibraryEAO eao;

	@EJB
	Conversion conv;

	/**
	 * Default constructor.
	 */
	public LibraryBean() {
		// TODO Auto-generated constructor stub
	}

	public LibraryBean(ILibraryEAO eao, Conversion conv) {
		// for unit test
		this.eao = eao;
		this.conv = conv;
	}
    
    @Override
	public long bookCount()
	{
		return eao.countBooks();
	}


	@Override
	public List<AuthorDto> searchAuthorByFirstName(String fistName)
	{
		List<Author> authors = null;
		authors = eao.findAuthorByFirstName(fistName);
		return conv.fromEntity(authors);
	}

	@Override
	public List<BookDto> searchBookByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDto> searchBookByISBN(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDto> searchBookByAuthor(String lastName, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorDto> searchAuthorByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorDto> searchAuthorByBookName(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorDto storeAuthor(AuthorDto in) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
