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
 * 	Tests unitaires réalisés sur le service web du stock de la librairie
 * 	Contient l'ensemble des livres et des auteurs
 * 
 * @author julien
 *
 */
public class TestLibraryBean 
{
	// FIXME 1 - pour les recherches problemes de sensibilité à la casse
	// FIXME 2 - attention à la contrainte d'intégrité un auteur unique(nom et prenom)
	// FIXME 3 - un seul ISBN par livre
	
	
	// service web de l'application de stock de la librairie
	private static LibraryBean serviceBean;
	
	// jeu d'auteurs utilisés pour les tests unitaires
	private static List<AuthorDTO> authors;
	
	// jeu de livres utilisés pour les tests unitaires
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
    	authors = new ArrayList<AuthorDTO>();
    	books = new ArrayList<BookDTO>();
    	
    	String [] authorsList = { "Gustave", "Flaubert",
				  "Victor", "Hugo",
				  "Jules", "Verne",
				  "Henri", "Beyle",
				  "Emile", "ZOLA",
				  "Jean", "Voltaire"};
    	
    	/**
    	 * 	Ajout des auteurs
    	 */
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
				"ZOL573EMI","La Terre","ROMAN",
				"ZOL568EMI","LA CUREE","ROMAN2",
				"ZOL387EMI","GERMINAL","ROMAN2",
				"ZOL897EMI","LA DEBACLE","ROMAN2",
				"VOL765JEA","CANDIDE","ROMAN2"
			};

		/*
		 * 	Ajout des livres
		 */
		for (int i = 0 ; i < booksList.length ; i=i+3)
		{
			
			BookDTO bookDTO = new BookDTO();
			bookDTO.setIsbn(booksList[i]);
			bookDTO.setName(booksList[i+1]);
			bookDTO.setGenre(booksList[i+2]);
			if (i < 15) {
				bookDTO.setAuthorId(authors.get(1).getId());
			}
			else {
				
				bookDTO.setAuthorId(authors.get(4).getId());
			}
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
	 * 	Test le service permettant de metttre à jour un auteur dans la
	 * 		zone de stockage des livres.
	 */
	@Test
	public void updateAuthorTest()
	{
		// recupere un auteur dans la liste
		AuthorDTO authorDTO = null;
		for (AuthorDTO authorDTOTmp : authors)
		{
			if (authorDTOTmp.getLastName().equals("Voltaire"))
			{
				authorDTO = authorDTOTmp;
			}
		}
		assertNotNull(authorDTO);
	
		
		// on modifie l'auteur
		try {
			authorDTO.setFirstName("Robert");
			authorDTO.setLastName("Voltaire1");
			serviceBean.updateAuthor(authorDTO);
		} catch (EntityNotFoundException_Exception e) 
		{
			fail(e.getMessage());
		}

		List<AuthorDTO> authors = serviceBean.searchAuthorByFirstName("Robert");
		assertEquals(authors.size(), 1);
		
		AuthorDTO author = authors.get(0);
		assertNotNull(author);
		assertEquals("Robert", author.getFirstName());
		assertEquals("Voltaire1", author.getLastName());
	}
	
	/**
	 * 	Test la méthode permettant de récupérer le nombre d'exemplaire
	 * 	d'un livre
	 */
	@Test
	public void getExemplaryNumberTest()
	{
		long number = serviceBean.getExemplaryNumber("ZOL570EMI");
		assertEquals(2, number);
	}

	/**
	 * 	Test la méthode permettant de récupérer le nombre d'exemplaire
	 * 	d'un livre avec son isbn
	 */
	@Test
	public void getExemplaryTest()
	{
		try {
			ExemplaryDTO exemplary = serviceBean.getExemplary("ZOL570EMI");
			assertNotNull(exemplary);
			
			assertEquals(2, exemplary.getNb());
			assertEquals("ZOL570EMI", exemplary.getIsbn());
			
		} catch (BookNotFoundException_Exception e) {
			fail(e.getMessage());
		}

		
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
			assertEquals("Emile ZOLA", book.getAuthorName());
			 
		} catch (BookNotFoundException_Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du genre du livre.
	 */
	@Test
	public void searchBookByGenreTestSuccess()
	{
		List<BookDTO> books = serviceBean.searchBookByGenre("ROMAN2");
		assertEquals(books.size(), 4);
		
		BookDTO book = books.get(1);
		assertNotNull(book);
		assertEquals("ZOL387EMI",book.getIsbn());
		assertEquals("GERMINAL", book.getName());
		assertEquals("ROMAN2", book.getGenre());
		assertEquals("Emile ZOLA", book.getAuthorName());
		
		book = books.get(2);
		assertNotNull(book);
		assertEquals("ZOL897EMI",book.getIsbn());
		assertEquals("LA DEBACLE", book.getName());
		assertEquals("ROMAN2", book.getGenre());
		assertEquals("Emile ZOLA", book.getAuthorName());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du genre du livre.
	 */
	@Test
	public void searchBookByGenreTestFail()
	{
		List<BookDTO> books = serviceBean.searchBookByGenre("POLICIER");
		assertNotNull(books);
		assertTrue(books.isEmpty());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du nom du livre
	 */
	@Test
	public void searchBookByNameSuccess()
	{
		List<BookDTO> books = serviceBean.searchBookByName("a Terr");
		assertNotNull(books);
		assertEquals(books.size(), 1);
		
		BookDTO book = books.get(0);
		assertNotNull(book);
		assertEquals("ZOL573EMI",book.getIsbn());
		assertEquals("La Terre", book.getName());
		assertEquals("ROMAN", book.getGenre());
		assertEquals("Victor Hugo", book.getAuthorName());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du nom du livre
	 * 	Recherche d'un livre qui n'existe pas
	 */
	@Test
	public void searchBookByNameFail()
	{
		List<BookDTO> books = serviceBean.searchBookByName("Voyage au centre de la terre");
		assertNotNull(books);
		assertTrue(books.isEmpty());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir de son auteur
	 */
	@Test
	public void searchBookByAuthorSuccess()
	{
		List<BookDTO> books = serviceBean.searchBookByAuthor("Hugo", "Victor");
		assertNotNull(books);
		assertEquals(books.size(), 5);
		
		BookDTO book = books.get(0);
		assertNotNull(book);
		assertEquals("ZOL569EMI",book.getIsbn());
		assertEquals("Le rouge et le noir", book.getName());
		assertEquals("ROMAN", book.getGenre());
		
		
		book = books.get(2);
		assertNotNull(book);
		assertEquals("ZOL571EMI",book.getIsbn());
		assertEquals("Les misérables", book.getName());
		assertEquals("ROMAN", book.getGenre());
		
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir de son auteur
	 * 	Recherche de livres qui n'existent pas
	 */
	@Test
	public void searchBookByAuthorFail()
	{
		List<BookDTO> books = serviceBean.searchBookByAuthor("Lamartine", "Alphonse");
		assertNotNull(books);
		assertTrue(books.isEmpty());
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
