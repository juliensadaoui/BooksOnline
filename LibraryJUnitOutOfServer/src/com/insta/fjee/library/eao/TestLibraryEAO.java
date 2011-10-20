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

public class TestLibraryEAO {

	private EntityManager _em;

	private LibraryEAO eao;
 
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        eao = new LibraryEAO(_em);
    }
    
    @Test
    public void bookCountTest() {
        long n = eao.countBooks();
        assertEquals(4, n);
    }
    
    @Test 
    public void searchAuthorByFirstNameTest()
    {
    	List<Author> authors = eao.findAuthorByFirstName("Jea");
    	assertEquals(authors.size(), 1);
    }
    
    @Test 
    public void searchAuthorByLastNameTest()
    {
    	List<Author> authors = eao.findAuthorByLastName("fsqdf");
    	assertEquals(authors.size(), 0);
    }
    
    @Test 
    public void searchAuthorByBookNameTest()
    {
    	List<Author> authors = eao.findAuthorByBookName("Test");
    	assertEquals(authors.size(), 0);
    }
    
    @Test
    public void searchBookByISBNTest()
    {
    	Book book;
		try {
			book = eao.findBookByISBN("test");
		} catch (BookNotFoundException e) {
			assertEquals(e.getIsbn(), "test");
		}
    }
}
