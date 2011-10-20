package com.insta.fjee.library.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.exception.EntityNotFoundException;

public class BookEAO implements IBookEAO
{
	@PersistenceContext
	EntityManager em;

	public BookEAO() {
	}

	// for unit test
	public BookEAO(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public long countBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveOrUpdate(Book author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book findOrFail(Integer id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookByISBN(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBookByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBookByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBookByAuthor(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
