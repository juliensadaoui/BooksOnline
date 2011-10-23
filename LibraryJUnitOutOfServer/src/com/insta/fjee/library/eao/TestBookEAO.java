package com.insta.fjee.library.eao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.junit.Util;

public class TestBookEAO {
	
	private EntityManager entityManager;

	private BookEAO bookDAO;
	private AuthorEAO authorEAO;
	
    @Before
    public void setUp() throws Exception {
        entityManager = Util.getEntityManager();
        bookDAO = new BookEAO(entityManager);
        authorEAO = new AuthorEAO(entityManager);
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
    	entityManager.getTransaction().begin();
    	authorEAO.saveOrUpdate(author);
    	entityManager.getTransaction().commit();
    	
    	book.setAuthor(author);
    	book.setGenre("Test");
    	book.setIsbn("test01test");
    	book.setName("Test de yann");
    	book.setExemplary(1);
    	
    	entityManager.getTransaction().begin();
    	bookDAO.saveOrUpdate(book);
    	entityManager.getTransaction().commit();
    	assertNotNull(bookDAO.findBookByName("Test de yann"));
    
       	entityManager.getTransaction().begin();
    	bookDAO.delete(book);
    	authorEAO.delete(author);
    	entityManager.getTransaction().commit();
    	
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
