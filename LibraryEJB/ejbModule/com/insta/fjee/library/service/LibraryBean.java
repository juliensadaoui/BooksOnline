package com.insta.fjee.library.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.dto.ExemplaryDTO;
import com.insta.fjee.library.eao.AuthorEAO;
import com.insta.fjee.library.eao.BookEAO;
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
	AuthorEAO authorEAO;
	
	@EJB
	BookEAO bookEAO;

	@EJB
	Conversion conv;

	/**
	 * Default constructor.
	 */
	public LibraryBean() {
		// TODO Auto-generated constructor stub
	}

	public LibraryBean(AuthorEAO authorEAO, BookEAO bookEAO, Conversion conv) {
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
	public AuthorDto createAuthor(AuthorDto in)
	{
		try {
			in.setId(null); // on ne renseigne pas le id
			Author author = conv.fromDto(in);
			authorEAO.saveOrUpdate(author);
			return conv.fromEntity(author);
		} catch (EntityNotFoundException e) {
			// ne doit jamais arriver
			return null;
		}		
	}


	@Override
	public AuthorDto updateAuthor(AuthorDto in) throws EntityNotFoundException {
		Author author = conv.fromDto(in);
		authorEAO.saveOrUpdate(author);
		return conv.fromEntity(author);
	}

	@Override
	public void deleteAuthor(AuthorDto authorDTO) throws EntityNotFoundException 
	{
		Author author = conv.fromDto(authorDTO);
		authorEAO.delete(author);
	}

	@Override
	public BookDto addBook(BookDto bookDTO, int exemplary) throws EntityNotFoundException {
		Book book = conv.fromDTO(bookDTO);
		book.setExemplary(exemplary);
		bookEAO.saveOrUpdate(book);
		return conv.fromEntity(book);
	}

	@Override
	public ExemplaryDTO addExemplary(ExemplaryDTO exemplaryDTO)
			throws EntityNotFoundException, BookNotFoundException {
		Book book = conv.fromDTO(exemplaryDTO);
		book.setExemplary(book.getExemplary()+exemplaryDTO.getNb());
		bookEAO.saveOrUpdate(book);
		return conv.fromEntityToExemplary(book);
	}

	@Override
	public ExemplaryDTO deleteExemplary(ExemplaryDTO exemplaryDTO)
			throws EntityNotFoundException, BookNotFoundException {

		Book book = conv.fromDTO(exemplaryDTO);
		if (exemplaryDTO.getNb() > book.getExemplary()) {
			bookEAO.delete(book); //FIXME delete book?
		}
		else {
			book.setExemplary(book.getExemplary()-exemplaryDTO.getNb());
			bookEAO.saveOrUpdate(book);
		}
		return conv.fromEntityToExemplary(book);
	}

//	@Override
//	public AuthorDto insert() {
//    	Author author = new Author();
//    	
//    	author.setFirstName("Yann");
//    	author.setLastName("DUDICOURT");
//    	authorEAO.saveOrUpdate(author);
//		return conv.fromEntity(author);
//	}


}
