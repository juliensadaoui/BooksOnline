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
//		for (Book book : a.getBooks()){
//			books.add(book.getName());
//		}
		result.setBooks(books);
		return result;
	}
	
	public List<AuthorDto> fromEntity(List<Author> authors)
	{
		List<AuthorDto> result = new ArrayList<AuthorDto>();
		for (Author a : authors) {
			result.add(fromEntity(a));
		}
		return result;
	}

//	public Zip fromDto(ZipDto d) throws EntityNotFoundException
//	{
//		Zip result;
//		Integer id = d.getId();
//		if (Entity.isId(id)) {
//			result = eao.findOrFail(Zip.class, id);
//		}
//		else {
//			result = new Zip();
//		}
//		result.setName(d.getName());
//		result.setCode(d.getCode());
//		result.setCountry(eao.findOrFail(Country.class, d.getCountryId()));
//		return result;
//	}
}