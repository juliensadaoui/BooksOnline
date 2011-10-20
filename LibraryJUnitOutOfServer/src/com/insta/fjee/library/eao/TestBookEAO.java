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

	private BookEAO bookeao;
	private AuthorEAO authoreao;
	
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        bookeao = new BookEAO(_em);
        authoreao = new AuthorEAO(_em);
    }
    
    @Test
    public void bookCountTest() {
        long n = bookeao.countBooks();
        assertEquals(4, n);
    }
    
    @Test
    public void creatBookTest()
    {
    	Book book = new Book();
    	Author author = new Author();
    	
    	author.setFirstName("Yann");
    	author.setLastName("DUDICOURT");
    	authoreao.saveOrUpdate(author);
    	
    	book.setAuthor(author);
    	book.setGenre("Test");
    	book.setIsbn("test01test");
    	book.setName("Test de yann");
    	book.setExemplary(1);
    	
    	bookeao.saveOrUpdate(book);
    	assertNotNull(bookeao.findBookByName("Test de yann"));
    	
    	bookeao.delete(book);
    	authoreao.delete(author);
    	
    }
    
    @Test
    public void deleatBookTest()
    {
    	Book book = new Book();
    	Author author = new Author();
    	
    	author.setFirstName("Yann");
    	author.setLastName("DUDICOURT");
    	authoreao.saveOrUpdate(author);
    	
    	book.setAuthor(author);
    	book.setGenre("Test");
    	book.setIsbn("test01test");
    	book.setName("Test de yann");
    	book.setExemplary(1);
    	bookeao.saveOrUpdate(book);
    	
    	bookeao.delete(book);
    	authoreao.delete(author);
    	
    	assertTrue(bookeao.findBookByName("Test de yann").isEmpty());
    	
    }
    
    @Test
    public void searchBookByISBNTest()
    {
    	Book book;
		try {
			book = bookeao.findBookByISBN("test");
		} catch (BookNotFoundException e) {
			assertEquals(e.getIsbn(), "test");
		}
    }
    
    @Test 
    public void searchBookByNameTest()
    {
    	List<Book> book = bookeao.findBookByName("Test");
    	assertEquals(book.size(), 0);
    }
    
    @Test 
    public void searchBookByGenreTest()
    {
    	List<Book> book = bookeao.findBookByGenre("Test");
    	assertEquals(book.size(), 0);
    }
    
    @Test 
    public void searchBookByAuthorTest()
    {
    	List<Book> book = bookeao.findBookByAuthor("test","Test");
    	assertEquals(book.size(), 0);
    }

}
