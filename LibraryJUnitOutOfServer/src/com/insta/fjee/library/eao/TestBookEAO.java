package com.insta.fjee.library.eao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.junit.Util;

public class TestBookEAO {
	
	private EntityManager _em;

	private BookEAO bookeao;
	
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        bookeao = new BookEAO(_em);
    }
    
    @Test
    public void bookCountTest() {
        long n = bookeao.countBooks();
        assertEquals(4, n);
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
