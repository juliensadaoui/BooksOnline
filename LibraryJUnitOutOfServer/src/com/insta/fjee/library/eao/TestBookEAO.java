package com.insta.fjee.library.eao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.junit.Util;

public class TestBookEAO {
	
	private EntityManager entityManager;

	private BookEAO bookEAO;
	private AuthorEAO authorEAO;
	
	private Author author;
	private Book book;
	
    @BeforeClass
    public void setUpClass() throws Exception {
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
//        Si crée avec script 4 book sont deja insert +1 en Befaore de Junit
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
    	
    }
    
    @Test
    public void searchBookByISBNTest()
    {
//    	List<Book> books = bookEAO.findBookByISBN("FLA1234GUS");
//    	assertEquals(books.size(), 1);
    }
    
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

}
