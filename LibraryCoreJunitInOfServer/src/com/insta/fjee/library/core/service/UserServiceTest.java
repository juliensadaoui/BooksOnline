package com.insta.fjee.library.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import com.insta.fjee.library.core.service.ws.AdminServiceService;
import com.insta.fjee.library.core.service.ws.LoginAlreadyExistException;
import com.insta.fjee.library.core.service.ws.UserServiceService;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;
import com.insta.fjee.library.stock.service.EntityNotFoundException_Exception;
import com.insta.fjee.library.stock.service.ExemplaryDTO;
import com.insta.fjee.library.stock.service.LibraryBean;
import com.insta.fjee.library.stock.service.LibraryBeanService;

//mvn install:install-file -Dfile=library-core.jar -DgroupId=library -DartifactId=library-core -Dversion=1.0 -Dpackaging=jar
//mvn install:install-file -Dfile=library-stock.jar -DgroupId=library -DartifactId=library-stock -Dversion=1.0 -Dpackaging=jar
/**
 * 	Tests unitaires réalisés sur le service web de la librarie Core 
 * 		accessible par les visiteurs anonymes
 * 
 * @author julien
 *
 */
public class UserServiceTest 
{
	// service web de l'application de stock de la librairie
	private static LibraryBean serviceBean;

	// service web des visiteurs anonymes
	private static com.insta.fjee.library.core.service.ws.IUserService userService;
	
	// service web des administrateurs
	private static com.insta.fjee.library.core.service.ws.IAdminService adminService;
	
	// jeu d'auteurs utilisés pour les tests unitaires
	private static List<AuthorDTO> authors;
	
	// jeu de livres utilisés pour les tests unitaires
	private static List<BookDTO> books;
	
	// jeu de utilisateurs utilisés pour les tests unitaires
	private static List<UserDTO> users;
	private static UserDTO admin;

	@BeforeClass
	public static void setUp() throws Exception
	{
		serviceBean = (new LibraryBeanService()).getLibraryBeanPort();
		userService = (new UserServiceService()).getUserServicePort();
		adminService = (new AdminServiceService()).getAdminServicePort();
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
    	users = new ArrayList<UserDTO>();
    	
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
    		
		/*
		 * 	Ajout d'un utilisateur (administrateur)
		 */
    	try {
    		UserDTO userDTO = new UserDTO();
	    	userDTO.setLogin("ydudicourt");
	    	userDTO.setFirstName("yann");
	    	userDTO.setLastName("dudicourt");
	    	userDTO.setPassword("pass");
	    	userDTO.setAdmin(true);
	    	admin = userService.createUser(userDTO);
		} catch (LoginAlreadyExistException e) {
			fail(e.getMessage());
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

		// supprime tous les utilisateurs
		for (UserDTO user : users) 
		{	
			try {
				adminService.deleteUser(user,admin);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		// supprime l'admin
		try {
			adminService.deleteUser(admin,admin);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	
    }
    
    /**
     * 	Test la méthode permettant d'ajouter un nouveau
     * 		utilisateur
     */
    @Test
    public void createUserTestSuccess()
    {
    	/*
    	 *	Ajout de l'utilisateur en base
    	 */
    	UserDTO userDTO = null;
    	try {
	    	userDTO = new UserDTO();
	    	userDTO.setLogin("jsadaoui");
	    	userDTO.setFirstName("julien");
	    	userDTO.setLastName("sadaoui");
	    	userDTO.setPassword("pass");
	    	userDTO = userService.createUser(userDTO);
	    	users.add(userDTO);
		} catch (LoginAlreadyExistException e) {
			fail(e.getMessage());
		}
    	
    	// vérification de la mise en base de l'utilisateur
    	assertNotNull(userDTO);
    	assertTrue(userDTO.getID()>0);
    	assertEquals(userDTO.getLogin(), "jsadaoui");
    	assertEquals(userDTO.getPassword(), "pass");
    	assertEquals(userDTO.getFirstName(), "julien");
    	assertEquals(userDTO.getLastName(), "sadaoui");
    }
 
    /**
     * 	Test la méthode permettant d'ajouter un nouveau
     * 		utilisateur. On essaye d'ajouter un nouveau
     * 		utilisateur avec un login existant
     */
    @Test
    public void createUserTestFail()
    {
		/*
		 * 	Ajout d'un utilisateur
		 */
    	try {
    		UserDTO userDTO = new UserDTO();
	    	userDTO.setLogin("ydudicourt");
	    	userDTO.setFirstName("francois");
	    	userDTO.setLastName("cardenas");
	    	userDTO.setPassword("password");
	    	userDTO = userService.createUser(userDTO);
	    	fail("Attention deux utilisateurs avec le meme login!");
		} catch (LoginAlreadyExistException e) {
			assertTrue(true);
		}
    }
    
    /**
     * 	Test la méthode permettant d'ajouter un nouveau utilisateur
     * 		Version avec echec: login non renseigné
     */
    @Test
    public void createUserTestFail2()
    {
		/*
		 * 	Ajout d'un utilisateur
		 */
    	try {
    		UserDTO userDTO = new UserDTO();
	    	userDTO.setLogin("");
	    	userDTO.setFirstName("echec1");
	    	userDTO.setLastName("echec2");
	    	userDTO = userService.createUser(userDTO);
	    	fail("Attention Login non renseigné");
		} catch (LoginAlreadyExistException e) {
			assertTrue(true);
		}
    }
    
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du genre du livre.
	 */
	@Test
	public void searchBookByGenreTestSuccess()
	{
		List<BookDTO> books = userService.searchBookByGenre("ROMAN2");
		assertEquals(books.size(), 4);
		
		BookDTO book = books.get(1);
		assertNotNull(book);
		assertEquals("ZOL387EMI",book.getIsbn());
		assertEquals("GERMINAL", book.getName());
		assertEquals("ROMAN2", book.getGenre());
		
		book = books.get(2);
		assertNotNull(book);
		assertEquals("ZOL897EMI",book.getIsbn());
		assertEquals("LA DEBACLE", book.getName());
		assertEquals("ROMAN2", book.getGenre());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du genre du livre.
	 */
	@Test
	public void searchBookByGenreTestFail()
	{
		List<BookDTO> books = userService.searchBookByGenre("POLICIER");
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
		List<BookDTO> books = userService.searchBookByName("a Terr");
		assertNotNull(books);
		assertEquals(books.size(), 1);
		
		BookDTO book = books.get(0);
		assertNotNull(book);
		assertEquals("ZOL573EMI",book.getIsbn());
		assertEquals("La Terre", book.getName());
		assertEquals("ROMAN", book.getGenre());
	}
	
	/**
	 * 	Test la méthode du services web permettant de récuperer une
	 * 		liste de livres à partir du nom du livre
	 * 	Recherche d'un livre qui n'existe pas
	 */
	@Test
	public void searchBookByNameFail()
	{
		List<BookDTO> books = userService.searchBookByName("Voyage au centre de la terre");
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
		List<BookDTO> books = userService.searchBookByAuthor("Hugo", "Victor");
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
		List<BookDTO> books = userService.searchBookByAuthor("Lamartine", "Alphonse");
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
