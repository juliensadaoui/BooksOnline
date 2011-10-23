package com.insta.fjee.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import javax.persistence.EntityManager;

import com.insta.fjee.library.dto.AuthorDTO;
import com.insta.fjee.library.dto.BookDTO;
import com.insta.fjee.library.eao.AuthorEAO;
import com.insta.fjee.library.eao.BookEAO;
import com.insta.fjee.library.exception.EntityNotFoundException;
import com.insta.fjee.library.junit.Util;
import com.insta.fjee.library.util.Conversion;

public class TestLibraryBean 
{
//	extends TestSuite {
	private EntityManager entityManager;
	
	private LibraryBean serviceBean;

	@Before
	public void setUp() throws Exception
	{
		entityManager = Util.getEntityManager();
		AuthorEAO authorEAO = new AuthorEAO(entityManager);
		BookEAO bookEAO = new BookEAO(entityManager);
		serviceBean = new LibraryBean(authorEAO, bookEAO, new Conversion(authorEAO, bookEAO));
	}
	
//	public static Test suite() {
//		   suite.addTest(new SomeTestCase ("testDoThisFirst";));
//		   suite.addTest(new SomeTestCase ("testDoThisSecond";));
//		   return suite;
//	}
	/**
	 * 	Test le service permettant de créer un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void createAuthorTest()
	{
		// creation d'un auteur
		AuthorDTO author = new AuthorDTO();
		author.setFirstName("julien");
		author.setLastName("sadaoui");
		entityManager.getTransaction().begin();
		author = serviceBean.createAuthor(author);
		entityManager.getTransaction().commit();
		assertNotNull(author);
		
		// valide l'opération de création
		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("jul");
		author = authors.get(0);
		assertNotNull(author);
		assertEquals(author.getFirstName(), "julien");
		assertEquals(author.getLastName(), "sadaoui");
		
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
	
	@Test
	public void findBookByISBNTest()
	{
		BookDTO book = serviceBean.findBookByISBN("ZOL568EMI");
		assertEquals("LA CUREE", book.getName());
		assertEquals("ZOL568EMI", book.getIsbn());
	}
}
