package com.insta.fjee.library.stock.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.insta.fjee.library.stock.dto.AuthorDTO;
import com.insta.fjee.library.stock.dto.BookDTO;
import com.insta.fjee.library.stock.dto.ExemplaryDTO;
import com.insta.fjee.library.stock.eao.AuthorEAO;
import com.insta.fjee.library.stock.eao.BookEAO;
import com.insta.fjee.library.stock.entity.Author;
import com.insta.fjee.library.stock.entity.Book;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;
import com.insta.fjee.library.stock.util.Conversion;

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
 
	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public AuthorDTO addAuthor(AuthorDTO authorDTO)
	{
		try {
			authorDTO.setId(null); // on ne renseigne pas le id
			Author author = conv.fromDTO(authorDTO);
			authorEAO.saveOrUpdate(author);
			return conv.fromEntity(author);
		} catch (EntityNotFoundException e) {
			// ne doit jamais arriver
			return null;
		}		
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws EntityNotFoundException 
	{
		Author author = conv.fromDTO(authorDTO);
		authorEAO.saveOrUpdate(author);
		return conv.fromEntity(author);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public void deleteAuthor(AuthorDTO authorDTO) throws EntityNotFoundException 
	{
		Author author = conv.fromDTO(authorDTO);
		authorEAO.delete(author);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByFirstName(String fistName)
	{
		List<Author> authors = null;
		authors = authorEAO.findAuthorByFirstName(fistName);
		return conv.fromEntityAuthor(authors);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByLastName(String lastName)
	{
		List<Author> authors = null;
		authors = authorEAO.findAuthorByLastName(lastName);
		return conv.fromEntityAuthor(authors);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByBookName(String bookName) {
		List<Author> authors = null;
		authors = authorEAO.findAuthorByBookName(bookName);
		return conv.fromEntityAuthor(authors);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public BookDTO addBook(BookDTO bookDTO, int exemplary) throws EntityNotFoundException
	{
		Book book = conv.fromDTO(bookDTO);
		book.setExemplary(exemplary);
		bookEAO.saveOrUpdate(book);
		return conv.fromEntity(book);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public ExemplaryDTO addExemplary(ExemplaryDTO exemplaryDTO)
			throws EntityNotFoundException, BookNotFoundException 
	{
		Book book = conv.fromDTO(exemplaryDTO);
		book.setExemplary(book.getExemplary()+exemplaryDTO.getNb());
		bookEAO.saveOrUpdate(book);
		return conv.fromEntityToExemplary(book);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public ExemplaryDTO deleteExemplary(ExemplaryDTO exemplaryDTO)
			throws EntityNotFoundException, BookNotFoundException 
	{

		Book book = conv.fromDTO(exemplaryDTO);
		if (exemplaryDTO.getNb() >= book.getExemplary()) {
			bookEAO.delete(book); //FIXME delete book?
		}
		else {
			book.setExemplary(book.getExemplary()-exemplaryDTO.getNb());
			bookEAO.saveOrUpdate(book);
		}
		return conv.fromEntityToExemplary(book);
	}
	
	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public ExemplaryDTO getExemplary(String isbn) throws BookNotFoundException 
	{
		Book book = bookEAO.findBookByISBN(isbn);
		return conv.fromEntityToExemplary(book);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public long getExemplaryNumber(String isbn) 
	{
		return bookEAO.countBooks(isbn);
	}
	
	/**
	 * 	@see {ILibraryService}
	 */
    @Override
	public long getCountBook()
	{
		return bookEAO.countBooks();
	}
    
	/**
	 * 	@see {ILibraryService}
	 */
    @Override 
    public BookDTO findBookByISBN(String isbn) throws BookNotFoundException
    {
    	Book book = bookEAO.findBookByISBN(isbn);
    	return conv.fromEntity(book);
    }

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<BookDTO> searchBookByName(String name) 
	{
		List<Book> books = null;
		books = bookEAO.findBookByName(name);
		return conv.fromEntityBook(books);
	}
	
	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<BookDTO> searchBookByGenre(String genre)
	{
		List<Book> books = null;
		books = bookEAO.findBookByGenre(genre);
		return conv.fromEntityBook(books);
	}

	/**
	 * 	@see {ILibraryService}
	 */
	@Override
	public List<BookDTO> searchBookByAuthor(String lastName, String firstName) 
	{
		List<Book> books = null;
		books = bookEAO.findBookByAuthor(firstName, lastName);
		return conv.fromEntityBook(books);
	}
}
