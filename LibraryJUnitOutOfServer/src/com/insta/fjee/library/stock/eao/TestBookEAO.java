package com.insta.fjee.library.stock.eao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.insta.fjee.library.stock.junit.Util;
import com.insta.fjee.library.stock.eao.AuthorEAO;
import com.insta.fjee.library.stock.eao.BookEAO;
import com.insta.fjee.library.stock.entity.Author;
import com.insta.fjee.library.stock.entity.Book;
import com.insta.fjee.library.stock.exception.BookNotFoundException;

public class TestBookEAO {
	
	private static EntityManager entityManager;

	private static BookEAO bookEAO;
	private static AuthorEAO authorEAO;
	
	private Author author;
	private Book book;
	
    @BeforeClass
    public static void setUpClass() throws Exception {
        entityManager = Util.getEntityManager();
        bookEAO = new BookEAO(entityManager);
        authorEAO = new AuthorEAO(entityManager);
    }
    
    @Before
    public void setUp() throws Exception {
        
		// charge l'auteur en BDD
		author = new Author();
		author.setFirstName("Gustave");
		author.setLastName("Flaubert");
		
		entityManager.getTransaction().begin();
		authorEAO.saveOrUpdate(author);
		entityManager.getTransaction().commit();
    	
		// charge lle book en bdd
		book = new Book();
		book.setName("En verve");
		book.setGenre("Roman");
		book.setIsbn("FLA1234GUS");
		book.setAuthor(author);
		book.setExemplary(2);
		
		entityManager.getTransaction().begin();
		bookEAO.saveOrUpdate(book);
		entityManager.getTransaction().commit();
    }
    
    @After
	public void tearDown() 
	{
		entityManager.getTransaction().begin();
		authorEAO.delete(author);
		bookEAO.delete(book);
		entityManager.getTransaction().commit();
	}

    
    @Test
    public void bookCountTest() {
        long n = bookEAO.countBooks();
        assertEquals(5, n);
//        Si ta basse est vide faire test avec 1
//        Si crï¿½e avec script 4 book sont deja insert +1 en Befaore de Junit
//        assertEquals(1, n);
        
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
    	bookEAO.saveOrUpdate(book);
    	entityManager.getTransaction().commit();
    	assertNotNull(bookEAO.findBookByName("Test de yann"));
    
       	entityManager.getTransaction().begin();
    	bookEAO.delete(book);
    	authorEAO.delete(author);
    	entityManager.getTransaction().commit();
    	
    }
    
    @Test
    public void deleatBookTest()
    {
    	
       	entityManager.getTransaction().begin();
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
    	bookEAO.saveOrUpdate(book);
    	
    	bookEAO.delete(book);
    	authorEAO.delete(author);
    	
    	assertTrue(bookEAO.findBookByName("Test de yann").isEmpty());
    	entityManager.getTransaction().commit();
    	
    }
    
    @Test
    public void searchBookByISBNTest()
    {
    	Book book = null;
		try {
			book = bookEAO.findBookByISBN("FLA1234GUS");
	    	assertNotNull(book);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}

    }
    
//    Test quand l'element existe en BDD
    
    @Test 
    public void searchBookByNameTest()
    {
    	List<Book> books = bookEAO.findBookByName("En verve");
    	assertEquals(books.size(), 1);
    }
    
    @Test 
    public void searchBookByGenreTest()
    {
    	List<Book> books = bookEAO.findBookByGenre("Roman");
    	assertEquals(books.size(), 1);
    }
    
    @Test 
    public void searchBookByAuthorTest()
    {
    	List<Book> books = bookEAO.findBookByAuthor(author.getFirstName(), author.getLastName());
    	assertEquals(books.size(), 1);
    }
    
   
//    Test quand l'element est inéxistant en BDD
    
    @Test 
    public void searchBookByNameTestFalse()
    {
    	List<Book> books = bookEAO.findBookByName("bhgr");
    	assertTrue(books.isEmpty());
    }
    
    @Test 
    public void searchBookByGenreTestFalse()
    {
    	List<Book> books = bookEAO.findBookByGenre("SF");
    	assertTrue(books.isEmpty());
    }

    @Test 
    public void searchBookByAuthorTestFalse()
    {
    	List<Book> books = bookEAO.findBookByAuthor("prénom", "nom");
    	assertTrue(books.isEmpty());
    }

}
