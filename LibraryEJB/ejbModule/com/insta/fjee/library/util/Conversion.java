package com.insta.fjee.library.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.eao.ILibraryEAO;
import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.EntityNotFoundException;
import com.insta.fjee.library.util.jpa.Entity;

@Stateless
public class Conversion
{

	@EJB
	ILibraryEAO eao;

	public Conversion() {
	}

	// for Unit test
	public Conversion(ILibraryEAO eao) {
		this.eao = eao;
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
		Author result;
		Integer id = authorDto.getId();
		if (Entity.isId(id)) {
			result = eao.findOrFail(Author.class, id);
		}
		else {
			result = new Author();
		}
		result.setLastName(authorDto.getLastName());
		result.setFirstName(authorDto.getFirstName());

		return result;
	}
	
	public Book fromDto(BookDto bookDto) throws EntityNotFoundException
	{
		Book result;
		Integer id = bookDto.getId();
		if (Entity.isId(id)) {
			result = eao.findOrFail(Book.class, id);
		}
		else {
			result = new Book();
		}
		result.setName(bookDto.getName());
		result.setGenre(bookDto.getGenre());
//		result.setCountry(eao.findOrFail(BookDto.class, d.getCountryId()));
		return result;
	}
}