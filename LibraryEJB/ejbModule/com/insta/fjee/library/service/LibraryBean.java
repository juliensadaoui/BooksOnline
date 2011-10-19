package com.insta.fjee.library.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.AuthorTypeField;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.dto.BookTypeField;
import com.insta.fjee.library.eao.ILibraryEAO;
import com.insta.fjee.library.entity.Author;
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
	public List<BookDto> searchBook(BookTypeField typeField, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorDto> searchAuthor(AuthorTypeField typeField, String value) {

		List<Author> authors = null;
		switch (typeField) 
		{
			case FIRST_NAME:
				authors = eao.findAuthorByFirstName(value);
				break;
			case LAST_NAME:
				break;
			case BOOK_NAME:
				break;
		}
		
		if (authors == null) {
			return null;
		}
		return conv.fromEntity(authors);
	}

}
