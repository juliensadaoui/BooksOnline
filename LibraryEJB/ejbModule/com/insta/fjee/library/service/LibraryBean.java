package com.insta.fjee.library.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.eao.IAuthorEAO;
import com.insta.fjee.library.eao.IBookEAO;
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
	IAuthorEAO authorEAO;
	
	@EJB
	IBookEAO bookEAO;

	@EJB
	Conversion conv;

	/**
	 * Default constructor.
	 */
	public LibraryBean() {
		// TODO Auto-generated constructor stub
	}

	public LibraryBean(IAuthorEAO authorEAO, IBookEAO bookEAO, Conversion conv) {
		// for unit test
		this.authorEAO = authorEAO;
		this.bookEAO = bookEAO;
		this.conv = conv;
	}
    
    @Override
	public long bookCount()
	{
		return bookEAO.countBooks();
	}


	@Override
	public List<AuthorDto> searchAuthorByFirstName(String fistName)
	{
		List<Author> authors = null;
		authors = authorEAO.findAuthorByFirstName(fistName);
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public List<BookDto> searchBookByName(String name) {
		List<Book> books = null;
		books = bookEAO.findBookByName(name);
		return conv.fromEntityBook(books);
	}

	@Override
	public BookDto findBookByISBN(String isbn) {
		Book book = null;
		try {
			book = bookEAO.findBookByISBN(isbn);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conv.fromEntity(book);
	}

	@Override
	public List<BookDto> searchBookByAuthor(String lastName, String firstName) {
		List<Book> books = null;
		books = bookEAO.findBookByAuthor(firstName, lastName);
		return conv.fromEntityBook(books);
	}

	@Override
	public List<AuthorDto> searchAuthorByLastName(String lastName) {
		List<Author> authors = null;
		authors = authorEAO.findAuthorByLastName(lastName);
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public List<AuthorDto> searchAuthorByBookName(String bookName) {
		List<Author> authors = null;
		authors = authorEAO.findAuthorByBookName(bookName);
		return conv.fromEntityAuthor(authors);
	}

	@Override
	public AuthorDto createAuthor(AuthorDto authorDto)
	{
//		Author author;
//		try {
//			author = conv.fromDto(authorDto);
//			eao.persist(author);
//			return conv.fromEntity(author);
//		} catch (EntityNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	}


	@Override
	public AuthorDto updateAuthor(AuthorDto in) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthor(AuthorDto in) {
		// TODO Auto-generated method stub
		
	}
}
