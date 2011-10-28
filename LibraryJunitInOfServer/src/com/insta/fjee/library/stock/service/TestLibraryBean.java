package com.insta.fjee.library.stock.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.EntityNotFoundException_Exception;
import com.insta.fjee.library.stock.service.LibraryBean;

/**
 * 	
 * 
 * @author julien
 *
 */
public class TestLibraryBean 
{
	private static LibraryBean serviceBean;
	
	private static List<AuthorDTO> authors;
	
	private static List<BookDTO> books;

	@BeforeClass
	public static void setUp() throws Exception
	{
		serviceBean = (new LibraryBeanService()).getLibraryBeanPort();
		setUpResources() ;
	}
	
    /**
     * 	Configure les ressources (injecte des données dans la base)
     * 		pour les tests unitaites.
     */
    public static void setUpResources() 
    {   
    	String [] authorsList = { "Gustave", "Flaubert",
    						  "Victor", "Hugo",
    						  "Jules", "Verne",
    						  "Henri", "Beyle" };
    	
    	authors = new ArrayList<AuthorDTO>();
    	books = new ArrayList<BookDTO>();
    		
		for (int i = 0 ; i < authorsList.length ; i=i+2)
		{
			// charge la donnée de base
			AuthorDTO author = new AuthorDTO();
			author.setFirstName(authorsList[i]);
			author.setLastName(authorsList[i+1]);
			authors.add(serviceBean.addAuthor(author));
		}
	
		String [] booksList = 
			{
				"ZOL569EMI","Le rouge et le noir","ROMAN",
				"ZOL570EMI","Le tour du monde en 80 jours","ROMAN",
				"ZOL571EMI","Les misérables","ROMAN",
				"ZOL572EMI","La Débâcle","ROMAN",
				"ZOL573EMI","La Terre","ROMAN"
			};
		
		for (int i = 0 ; i < authorsList.length ; i=i+3)
		{
			BookDTO bookDTO = new BookDTO();
			bookDTO.setIsbn(booksList[i]);
			bookDTO.setName(booksList[i+1]);
			bookDTO.setGenre(booksList[i+2]);
			bookDTO.setAuthorId(authors.get(1).getId());
			try {
				books.add(serviceBean.addBook(bookDTO, 2));
			} catch (EntityNotFoundException_Exception e) {
				fail(e.getMessage());
			}
		}
    		
    }
    
    /**
     * 	Supprime les ressources ajouter en base de données
     */
    @AfterClass
    public static void tearDown()
    {
    	for (BookDTO bookDTO : books) 
    	{
			try {
				ExemplaryDTO exemplaryDTO = new ExemplaryDTO();
				exemplaryDTO.setIsbn(bookDTO.getIsbn());
				exemplaryDTO.setNb(2);
				serviceBean.deleteExemplary(exemplaryDTO);
				
			} catch (BookNotFoundException_Exception e) {
				fail(e.getMessage());
			}
			catch (EntityNotFoundException_Exception e) {
				fail(e.getMessage());
			}
    	}
    	
		for (AuthorDTO authorDTO : authors)
		{
			try {
				serviceBean.deleteAuthor(authorDTO);
			} catch (EntityNotFoundException_Exception e) {
				fail(e.getMessage());
			}
		}
    }
    
	/**
	 * 	Test le service permettant de créer un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void createAuthorTest()
	{
		// creation d'un auteur
		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setFirstName("Guy");
		authorDTO.setLastName("Maupassant");
		authorDTO = serviceBean.addAuthor(authorDTO);
		assertNotNull(authorDTO);
		authors.add(authorDTO);
		
		// valide l'opération de création
		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("uy");
		authorDTO = authors.get(0);
		assertNotNull(authorDTO);
		assertEquals(authorDTO.getFirstName(), "Guy");
		assertEquals(authorDTO.getLastName(), "Maupassant");
		
	}
	
	/**
	 * 	Test le service permettant de supprimer un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void deleteAuthorTest()
	{
		// recupere un auteur dans la liste
		AuthorDTO authorDTO = authors.get(0);
		assertNotNull(authorDTO);
		assertEquals(authorDTO.getFirstName(), "Gustave");
		assertEquals(authorDTO.getLastName(), "Flaubert");
		
		// supprime l'auteur
		try {
			serviceBean.deleteAuthor(authorDTO);
			authors.remove(authorDTO);
		} catch (EntityNotFoundException_Exception e) {
			fail(e.getMessage());
		}

		assertTrue(serviceBean.searchAuthorByFirstName("Gustave").isEmpty());
	}
	
	/**
	 * 	Test de la recherche d'un livre à partir de son ISBN
	 * 
	 */
	@Test
	public void findBookByISBNTest()
	{
		// recherche du livre à partir du web services
		BookDTO book;
		try {
			book = serviceBean.findBookByISBN("ZOL568EMI");
			assertEquals("LA CUREE", book.getName());
			assertEquals("ZOL568EMI", book.getIsbn());
			
		} catch (BookNotFoundException_Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 	Test la méthode du web services permettant de rechercher un author
	 * 		par son nom
	 */
	@Test
	public void searchAuthorByLastNameTest()
	{
		List<AuthorDTO> authors = serviceBean.searchAuthorByLastName("Hugo");
		assertEquals(authors.size(), 1);
		AuthorDTO author = authors.get(0);
		assertEquals(author.getFirstName(), "Victor");
		assertEquals(author.getLastName(), "Hugo");		
	}
	
	/**
	 * 	Test la méthode du web services permettant de rechercher un author
	 * 		par son prenom
	 */
	@Test
	public void searchAuthorByFirstNameTest()
	{
		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("Henri");
		assertEquals(authors.size(), 1);
		AuthorDTO author = authors.get(0);
		assertEquals(author.getFirstName(), "Henri");
		assertEquals(author.getLastName(), "Beyle");		
	}
	
	/**
	 * 	Test la méthode du web services permettant de rechercher un author
	 * 		par un livre
	 */
	@Test
	public void searchAuthorByBookNameTest()
	{
		List<AuthorDTO> authors = serviceBean.searchAuthorByBookName("tour du monde en 80 jo");
		assertEquals(authors.size(), 1);
		AuthorDTO author = authors.get(0);
		assertEquals(author.getFirstName(), "Victor");
		assertEquals(author.getLastName(), "Hugo");	
	}
}
