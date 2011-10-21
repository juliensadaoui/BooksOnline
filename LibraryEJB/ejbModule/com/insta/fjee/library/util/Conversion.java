package com.insta.fjee.library.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.dto.ExemplaryDTO;
import com.insta.fjee.library.eao.AuthorEAO;
import com.insta.fjee.library.eao.BookEAO;
import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.exception.EntityNotFoundException;

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

	public BookDto fromEntity(Book e)
	{
		BookDto result = new BookDto();
		result.setId(e.getId());
		result.setName(e.getName());
		result.setGenre(e.getGenre());
		result.setIsbn(e.getIsbn());
		
		result.setAuthorId(e.getAuthor().getId());
		return result;
	}
	
	public AuthorDto fromEntity(Author a)
	{
		AuthorDto result = new AuthorDto();
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
	
	public List<AuthorDto> fromEntityAuthor(List<Author> authors)
	{
		List<AuthorDto> result = new ArrayList<AuthorDto>();
		for (Author a : authors) {
			result.add(fromEntity(a));
		}
		return result;
	}
	
	public List<BookDto> fromEntityBook(List<Book> books)
	{
		List<BookDto> result = new ArrayList<BookDto>();
		for (Book b : books) {
			result.add(fromEntity(b));
		}
		return result;
	}

	public Author fromDto(AuthorDto authorDto) throws EntityNotFoundException
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
	
	public Book fromDTO(BookDto bookDto) throws EntityNotFoundException
	{
		Book result = new Book();
		Integer id = bookDto.getId();
		if (Entity.isId(id)) {
			result = bookEAO.findOrFail(id);
		}
		else {
			result = new Book();
		}
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