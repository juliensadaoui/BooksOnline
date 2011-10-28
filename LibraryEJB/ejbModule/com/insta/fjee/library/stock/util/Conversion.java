package com.insta.fjee.library.stock.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.insta.fjee.library.stock.dto.AuthorDTO;
import com.insta.fjee.library.stock.dto.BookDTO;
import com.insta.fjee.library.stock.dto.ExemplaryDTO;
import com.insta.fjee.library.stock.eao.AuthorEAO;
import com.insta.fjee.library.stock.eao.BookEAO;
import com.insta.fjee.library.stock.entity.Author;
import com.insta.fjee.library.stock.entity.Book;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;

@Stateless
public class Conversion
{

	@EJB
	AuthorEAO authorEAO;
	
	@EJB
	BookEAO bookEAO;

	public Conversion() {
	}

	// for Unit test
	public Conversion(AuthorEAO authorEAO, BookEAO bookEAO) {
		this.authorEAO = authorEAO;
		this.bookEAO = bookEAO;
	}

	public BookDTO fromEntity(Book book)
	{
		BookDTO result = new BookDTO();
		result.setId(book.getId());
		result.setName(book.getName());
		result.setGenre(book.getGenre());
		result.setIsbn(book.getIsbn());
		
		result.setAuthorId(book.getAuthor().getId());
		return result;
	}
	
	public AuthorDTO fromEntity(Author a)
	{
		AuthorDTO result = new AuthorDTO();
		result.setId(a.getId());
		result.setFirstName(a.getFirstName());
		result.setLastName(a.getLastName());
		List<String> books = new ArrayList<String>();
		for (Book book : a.getBooks()){
			books.add(book.getIsbn());
		}
		result.setBooks(books);
		return result;
	}
	
	public List<AuthorDTO> fromEntityAuthor(List<Author> authors)
	{
		List<AuthorDTO> result = new ArrayList<AuthorDTO>();
		for (Author a : authors) {
			result.add(fromEntity(a));
		}
		return result;
	}
	
	public List<BookDTO> fromEntityBook(List<Book> books)
	{
		List<BookDTO> result = new ArrayList<BookDTO>();
		for (Book b : books) {
			result.add(fromEntity(b));
		}
		return result;
	}

	public Author fromDto(AuthorDTO authorDto) throws EntityNotFoundException
	{
		Author result  = new Author();
		Integer id = authorDto.getId();
		if (Entity.isId(id)) {
			result = authorEAO.findOrFail(id);
		}
		else {
			result = new Author();
		}
		result.setLastName(authorDto.getLastName());
		result.setFirstName(authorDto.getFirstName());

		return result;
	}
	
	public Book fromDTO(BookDTO bookDto) throws EntityNotFoundException
	{
		Book result = new Book();
		Integer id = bookDto.getId();
		if (Entity.isId(id)) {
			result = bookEAO.findOrFail(id);
		}
		else {
			result = new Book();
		}
		result.setIsbn(bookDto.getIsbn());
		result.setName(bookDto.getName());
		result.setGenre(bookDto.getGenre());
		result.setAuthor(authorEAO.findOrFail(bookDto.getAuthorId()));
		return result;
	}
	
	public Book fromDTO(ExemplaryDTO exemplaryDTO) throws BookNotFoundException
	{
		return bookEAO.findBookByISBN(exemplaryDTO.getIsbn());
	}
	
	public ExemplaryDTO fromEntityToExemplary(Book book)
	{
		ExemplaryDTO exemplaryDTO = new ExemplaryDTO();
		exemplaryDTO.setIsbn(book.getIsbn());
		exemplaryDTO.setNb(book.getExemplary());
		return exemplaryDTO;
	}
}