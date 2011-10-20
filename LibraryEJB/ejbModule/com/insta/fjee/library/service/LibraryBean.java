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
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
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
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public List<BookDto> searchBookByName(String name) {
		List<Book> books = null;
		books = eao.findBookByName(name);
		return conv.fromEntityBook(books);
	}

	@Override
	public BookDto findBookByISBN(String isbn) {
		Book book = null;
		try {
			book = eao.findBookByISBN(isbn);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conv.fromEntity(book);
	}

	@Override
	public List<BookDto> searchBookByAuthor(String lastName, String firstName) {
		List<Book> books = null;
		books = eao.findBookByAuthor(firstName, lastName);
		return conv.fromEntityBook(books);
	}

	@Override
	public List<AuthorDto> searchAuthorByLastName(String lastName) {
		List<Author> authors = null;
		authors = eao.findAuthorByLastName(lastName);
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public List<AuthorDto> searchAuthorByBookName(String bookName) {
		List<Author> authors = null;
		authors = eao.findAuthorByBookName(bookName);
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public AuthorDto storeAuthor(AuthorDto authorDto) throws EntityNotFoundException {
		Author author = conv.fromDto(authorDto);
		eao.persist(author);
		return conv.fromEntity(author);
	}

}
