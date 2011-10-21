package com.insta.fjee.library.eao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.junit.Util;

public class TestBookEAO {
	
	private EntityManager _em;

	private BookEAO bookDAO;
	private AuthorEAO authorEAO;
	
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        bookDAO = new BookEAO(_em);
        authorEAO = new AuthorEAO(_em);
    }
    
    @Test
    public void bookCountTest() {
        long n = bookDAO.countBooks();
        assertEquals(4, n);
    }
    
    @Test
    public void creatBookTest()
    {
    	Book book = new Book();
    	Author author = new Author();
    	
    	author.setFirstName("Yann");
    	author.setLastName("DUDICOURT");
    	_em.getTransaction().begin();
    	authorEAO.saveOrUpdate(author);
    	_em.getTransaction().commit();
    	
    	book.setAuthor(author);
    	book.setGenre("Test");
    	book.setIsbn("test01test");
    	book.setName("Test de yann");
    	book.setExemplary(1);
    	
    	_em.getTransaction().begin();
    	bookDAO.saveOrUpdate(book);
    	_em.getTransaction().commit();
    	assertNotNull(bookDAO.findBookByName("Test de yann"));
    
       	_em.getTransaction().begin();
    	bookDAO.delete(book);
    	authorEAO.delete(author);
    	_em.getTransaction().commit();
    	
    }
    
    @Test
    public void deleatBookTest()
    {
    	Book book = new Book();
    	Author author = new Author();
    	
    	author.setFirstName("Yann");
    	author.setLastName("DUDICOURT");
    	authorEAO.saveOrUpdate(author);
    	
    	book.setAuthor(author);
    	book.setGenre("Test");
    	book.setIsbn("test01test");
    	book.setName("Test de yann");
    	book.setExemplary(1);
    	bookDAO.saveOrUpdate(book);
    	
    	bookDAO.delete(book);
    	authorEAO.delete(author);
    	
    	assertTrue(bookDAO.findBookByName("Test de yann").isEmpty());
    	
    }
    
    @Test
    public void searchBookByISBNTest()
    {
    	Book book;
		try {
			book = bookDAO.findBookByISBN("test");
		} catch (BookNotFoundException e) {
			assertEquals(e.getIsbn(), "test");
		}
    }
    
    @Test 
    public void searchBookByNameTest()
    {
    	List<Book> book = bookDAO.findBookByName("Test");
    	assertEquals(book.size(), 0);
    }
    
    @Test 
    public void searchBookByGenreTest()
    {
    	List<Book> book = bookDAO.findBookByGenre("Test");
    	assertEquals(book.size(), 0);
    }
    
    @Test 
    public void searchBookByAuthorTest()
    {
    	List<Book> book = bookDAO.findBookByAuthor("test","Test");
    	assertEquals(book.size(), 0);
    }

}
