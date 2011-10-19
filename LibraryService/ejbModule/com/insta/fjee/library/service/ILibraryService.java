package com.insta.fjee.library.service;
import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.dto.AuthorTypeField;
import com.insta.fjee.library.dto.BookDto;
import com.insta.fjee.library.dto.BookTypeField;

@Remote
public interface ILibraryService { 
	
	public List<BookDto> searchBook(BookTypeField typeField, String value);
	
	public List<AuthorDto> searchAuthor(AuthorTypeField typeField, String value);

	long bookCount();

}
