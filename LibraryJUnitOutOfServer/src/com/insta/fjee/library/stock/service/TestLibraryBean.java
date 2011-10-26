package com.insta.fjee.library.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import javax.persistence.EntityManager;

import com.insta.fjee.library.stock.dto.AuthorDTO;
import com.insta.fjee.library.stock.dto.BookDTO;
import com.insta.fjee.library.stock.dto.ExemplaryDTO;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;
import com.insta.fjee.library.stock.junit.Util;
import com.insta.fjee.library.stock.eao.AuthorEAO;
import com.insta.fjee.library.stock.eao.BookEAO;
import com.insta.fjee.library.stock.service.LibraryBean;
import com.insta.fjee.library.stock.util.Conversion;

public class TestLibraryBean
{
	private EntityManager entityManager;
	
	private LibraryBean serviceBean;
	
	private AuthorDTO authorDTO;
	private BookDTO bookDTO;
	
	@Before
	public void setUp() throws Exception
	{
		entityManager = Util.getEntityManager();
		AuthorEAO authorEAO = new AuthorEAO(entityManager);
		BookEAO bookEAO = new BookEAO(entityManager);
		serviceBean = new LibraryBean(authorEAO, bookEAO, new Conversion(authorEAO, bookEAO));
		
		// charge la donnée de base
		authorDTO = new AuthorDTO();
		authorDTO.setFirstName("Gustave");
		authorDTO.setLastName("Flaubert");
		
		entityManager.getTransaction().begin();
		authorDTO = serviceBean.addAuthor(authorDTO);
		entityManager.getTransaction().commit();
		
		//charge le book en base de donn�e
		bookDTO.setGenre("Drame");
		bookDTO.setAuthorId(authorDTO.getId());
		bookDTO.setIsbn("GUS9878FLA");
		bookDTO.setName("Madame Bovary");
		
		entityManager.getTransaction().begin();
		bookDTO = serviceBean.addBook(bookDTO, 4);
		entityManager.getTransaction().commit();
	}
	
	@After
	public void tearDown() 
	{
		try {
			entityManager.getTransaction().begin();
			serviceBean.deleteAuthor(authorDTO);
			entityManager.getTransaction().commit();
		} catch (EntityNotFoundException e) {
		}
	}
	
	/**
	 * 	Test le service permettant de créer un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void createAuthorTestFail()
	{
		// creation d'un auteur
		entityManager.getTransaction().begin();
		authorDTO = serviceBean.addAuthor(authorDTO);
		entityManager.getTransaction().commit();
		assertNotNull(authorDTO);
		
		// valide l'opération de création
		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("jul");
		authorDTO = authors.get(0);
		assertNotNull(authorDTO);
		assertEquals(authorDTO.getFirstName(), "julien");
		assertEquals(authorDTO.getLastName(), "sadaoui");
		
	}
	
	/**
	 * 	Test le service permettant de supprimer un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void deleteAuthorTest()
	{
		// Recupere l'auteur
		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("jul");
		assertNotNull(authors.get(0));
		
		// supprime l'auteur
		try {
			entityManager.getTransaction().begin();
			serviceBean.deleteAuthor(authors.get(0));
			entityManager.getTransaction().commit();
		} catch (EntityNotFoundException e) {
			fail(e.getMessage());
		}
		assertTrue(serviceBean.searchAuthorByFirstName("jul").isEmpty());
	}
	
	/**
	 * 
	 * Test la recherche d'autheur 
	 * en FCT (firstname/lastname)
	 * 
	 */
	@Test
	public void findAuthorByFirstNameTest()
	{
		List<AuthorDTO> authors;
			authors = serviceBean.searchAuthorByFirstName("Gustave");
			AuthorDTO author = authors.get(0);
			assertEquals(author.getFirstName(),"Gustave");
			assertEquals(author.getLastName(),"Flaubert");

	}
	
	@Test
	public void searchAuthorByLastNameTest()
	{
		List<AuthorDTO> authors;
		authors = serviceBean.searchAuthorByLastName("Flaubert");
		AuthorDTO author = authors.get(0);
		assertEquals(author.getFirstName(),"Gustave");
		assertEquals(author.getLastName(),"Flaubert");
	}
	
	@Test
	public void searchAuthorByBookNameTest()
	{
		List<AuthorDTO> authors;
		authors = serviceBean.searchAuthorByBookName("Madame Bovary");
		AuthorDTO author = authors.get(0);
		assertEquals(author.getFirstName(),"Gustave");
		assertEquals(author.getLastName(),"Flaubert");
	}
	
	/**
	 * 
	 * Test sur les recherche de Book
	 * Fct (Genre/Author/ISBN/Titre)
	 * 
	 */
	
	@Test
	public void findBookByISBNTest()
	{
		BookDTO book;
		try {
			book = serviceBean.findBookByISBN("ZOL568EMI");
			assertEquals("LA CUREE", book.getName());
			assertEquals("ZOL568EMI", book.getIsbn());
		} catch (BookNotFoundException e) {
			fail();
		}
	}
	
	@Test
	public void searchBookByNameTest()
	{
		List<BookDTO> books;
		books = serviceBean.searchBookByName("Madame Bovary");
		BookDTO book = books.get(0);
		assertEquals(book.getName(),"Madame Bovary");
	}
	
	@Test
	public void searchBookByGenreTest()
	{
		List<BookDTO> books;
		books = serviceBean.searchBookByGenre("Drame");
		BookDTO book = books.get(0);
		assertEquals(book.getName(),"Madame Bovary");
	}
	
	@Test
	public void searchBookByAuthorTest()
	{
		List<BookDTO> books;
		books = serviceBean.searchBookByAuthor(authorDTO.getLastName(), authorDTO.getFirstName());
		BookDTO book = books.get(0);
		assertEquals(book.getName(),"Madame Bovary");
	}
	
	@Test
	public void addExemplaryTest()
	{
		ExemplaryDTO exemplaryDTOsend=null;
		exemplaryDTOsend.setIsbn(bookDTO.getIsbn());
		exemplaryDTOsend.setNb(1);

		try {
			ExemplaryDTO exemplaryDTO = serviceBean.addExemplary(exemplaryDTOsend);
			//permet de ne plus dependre du sens d'execution des methode de test
			assertEquals(exemplaryDTO.getNb(),(exemplaryDTO.getNb()+1));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void deleteExemplaryTest()
	{
		ExemplaryDTO exemplaryDTOsend=null;
		exemplaryDTOsend.setIsbn(bookDTO.getIsbn());
		exemplaryDTOsend.setNb(1);
		
		try {
			ExemplaryDTO exemplaryDTO = serviceBean.deleteExemplary(exemplaryDTOsend);
			//permet de ne plus dependre du sens d'execution des methode de test
			assertEquals(exemplaryDTO.getNb(),(exemplaryDTO.getNb()-1));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
