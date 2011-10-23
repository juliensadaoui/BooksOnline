package com.insta.fjee.library.service;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.BeforeClass;
import org.junit.Test;



/**
 * 	
 * 
 * @author julien
 *
 */
public class TestLibraryBean 
{
//	extends TestSuite {
	private static LibraryBean serviceBean;

	@BeforeClass
	public static void setUp() throws Exception
	{
		// call WS
		try {
			URL url = new URL("http://localhost:8080/LibraryBeanService/LibraryBean?wsdl");
			QName qname = new QName("http://service.library.fjee.insta.com/", "LibraryBeanService");
	
			// Création d'une fabrique pour le WS
			Service service = Service.create(url, qname);
	
			// Récupération Proxy pour accéder aux méthodes
			serviceBean = service.getPort(LibraryBean.class);
	
			BookDTO book;
			book = serviceBean.findBookByISBN("ZOL568EMI");
			System.out.println(book.getName());	
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
		author = serviceBean.createAuthor(author);
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
			serviceBean.deleteAuthor(authors.get(0));
		} catch (EntityNotFoundException_Exception e) {
			fail(e.getMessage());
		}

		assertTrue(serviceBean.searchAuthorByFirstName("jul").isEmpty());
	}
	
	/**
	 * 	Test de la recherche d'un livre à partir de son ISBN
	 * 
	 */
	@Test
	public void findBookByISBNTest()
	{
		// recherche du livre à partir du web services
		BookDTO book = serviceBean.findBookByISBN("ZOL568EMI");
		
		
		assertEquals("LA CUREE", book.getName());
		assertEquals("ZOL568EMI", book.getIsbn());
	}
	
//	List<AuthorDto> authors = serviceBean.searchAuthorByFirstName("Jea");
//	assertEquals(authors.size(), 1);
//	for (AuthorDto a : authors) {
//		System.out.println(a.getBooks().get(0));
//	}
}
