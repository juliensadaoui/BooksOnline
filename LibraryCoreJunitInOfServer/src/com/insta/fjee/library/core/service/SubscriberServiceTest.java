package com.insta.fjee.library.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.insta.fjee.library.core.service.ws.AdminServiceService;
import com.insta.fjee.library.core.service.ws.BookNotFoundExceptionException;
import com.insta.fjee.library.core.service.ws.EntityNotFoundException;
import com.insta.fjee.library.core.service.ws.LoginInvalidException;
import com.insta.fjee.library.core.service.ws.NotEnoughtExemplaryException;
import com.insta.fjee.library.core.service.ws.SubscriberServiceService;
import com.insta.fjee.library.core.service.ws.UserServiceService;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;
import com.insta.fjee.library.stock.service.EntityNotFoundException_Exception;
import com.insta.fjee.library.stock.service.ExemplaryDTO;
import com.insta.fjee.library.stock.service.LibraryBean;
import com.insta.fjee.library.stock.service.LibraryBeanService;

public class SubscriberServiceTest 
{
	// service web de l'application de stock de la librairie
	private static LibraryBean serviceBean;

	// service web des visiteurs anonymes
	private static com.insta.fjee.library.core.service.ws.IUserService userService;

	// service web des abonnées
	private static com.insta.fjee.library.core.service.ws.ISubscriberService subscriberService;
	
	// service web des administrateurs
	private static com.insta.fjee.library.core.service.ws.IAdminService adminService;

	// jeu d'auteurs utilisés pour les tests unitaires
	private static List<AuthorDTO> authors;
	
	// jeu de livres utilisés pour les tests unitaires
	private static List<BookDTO> books;
	
	// jeu d'utilisateurs pour les tests unitaires
	private static List<UserDTO> users;
	private static UserDTO admin;

	// jeu de locations pour les tests unitaires
	private static List<RentBookDTO> rentBooks;
	
	@BeforeClass
	public static void setUp() throws Exception
	{
		serviceBean = (new LibraryBeanService()).getLibraryBeanPort();
		userService = (new UserServiceService()).getUserServicePort();
		subscriberService = (new SubscriberServiceService()).getSubscriberServicePort();
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
    	rentBooks = new ArrayList<RentBookDTO>();
    	
    	String [] authorsList = { "Gustave", "Flaubert",
				  "Victor", "Hugo"};
    	
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
				"ZOL572EMI","Voyage au centre de la terre","ROMAN",
				"ZOL573EMI","Le dernier jour d'un condanne","ROMAN"
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
				bookDTO.setAuthorId(authors.get(0).getId());
			}
			else {
				
				bookDTO.setAuthorId(authors.get(1).getId());
			}
			try {
				books.add(serviceBean.addBook(bookDTO, 2));
			} catch (EntityNotFoundException_Exception e) {
				fail(e.getMessage());
			}
		}
    		
		String [] usersList = 
			{	
				"ydudicourt","pass","yann","dudicourt","true",
				"fcardenas","password","francois","cardenas", null,
				"jsadaoui", "password", "julien", "sadaoui", null,
				"pmallet", "s3cr3t", "paul", "mallet", null,
				"shor", "s3cr3t", "steven", "hor", null,
			};
		
		for (int i = 0 ; i < usersList.length ; i=i+5)
		{
			try {
	    		UserDTO userDTO = new UserDTO();
		    	userDTO.setLogin(usersList[i]);
		    	userDTO.setPassword(usersList[i+1]);
		    	userDTO.setFirstName(usersList[i+2]);
		    	userDTO.setLastName(usersList[i+3]);
		    	if (usersList[i+4] == null) {
		    		userDTO.setAdmin(false);
		    	}
		    	else {
		    		userDTO.setAdmin(true);
		    	}
		    	userDTO = userService.createUser(userDTO);
		    	
		    	if (i == 0) {
		    		admin = userDTO;
		    	}
		    	else {
		    		users.add(userDTO);
		    	}
		    	
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		/*
		 * 	Ajout d'un utilisateur
		 */
    	
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
				exemplaryDTO.setNb(4);
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
		
		// supprime les locations ajoutés dans la base
		for (RentBookDTO rentBookDTO : rentBooks)
		{
			try {
				adminService.deleteRentBook(rentBookDTO, admin);
			} 
			catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		// supprime les utilisateurs ajoutés dans la base pour les tests
		for (UserDTO userDTO : users)
		{
			try {
				adminService.deleteUser(userDTO, admin);
			} 
			catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		try {
			adminService.deleteUser(admin, admin);
		} 
		catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    /**
     * 	Test ma méthode du services web permettant à un
     * 		utilisateur de s'authentifier
     * 	Version sans échec
     */
    @Test
    public void authentificateTestSuccess()
    {
    	UserDTO userDTO = subscriberService.authentificate("ydudicourt", "pass");
    	assertNotNull(userDTO);
    	assertEquals(userDTO.getLogin(),"ydudicourt");
    	assertEquals(userDTO.getPassword(),"pass");
    	assertEquals(userDTO.getFirstName(),"yann");
    	assertEquals(userDTO.getLastName(),"dudicourt");
    	assertTrue(userDTO.isAdmin());
    }
    
    /**
     * 	Test ma méthode du services web permettant à un
     * 		utilisateur de s'authentifier
     * 	Version avec échec (login incorrecte)
     */
    @Test
    public void authentificateTestFail1()
    {
    	UserDTO userDTO = subscriberService.authentificate("ydudi", "pass");
    	assertNull(userDTO);
    }
    
    /**
     * 	Test ma méthode du services web permettant à un
     * 		utilisateur de s'authentifier
     * 	Version avec échec (password incorrecte)
     */
    @Test
    public void authentificateTestFail2()
    {
    	UserDTO userDTO = subscriberService.authentificate("ydudicourt", "password");
    	assertNull(userDTO);
    }
    
    
    /**
     * 	Test la méthode du services web permettant de mettre à jour
     * 		les données d'un utilisateur
     * 	Version sans echec: modif firstname, lastname and password
     */
    @Test
    public void updateUserTestSuccess1()
    {
    	// verifie que le jeu de tests est correcte
    	UserDTO userDTO = users.get(1);
    	assertNotNull(userDTO);
    	assertEquals(userDTO.getLogin(),"jsadaoui");
    	assertEquals(userDTO.getPassword(),"password");
    	assertEquals(userDTO.getFirstName(),"julien");
    	assertEquals(userDTO.getLastName(),"sadaoui");
    	assertFalse(userDTO.isAdmin());
    	
    	try {
	    	userDTO.setFirstName("malik");
	    	userDTO.setLastName("sadaoui1");
	    	userDTO.setPassword("pass2");
	    	userDTO = subscriberService.updateUser(userDTO);
	    	assertNotNull(userDTO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    	
    	userDTO = subscriberService.authentificate("jsadaoui", "pass2");
    	assertNotNull(userDTO);
    	assertEquals(userDTO.getLogin(),"jsadaoui");
    	assertEquals(userDTO.getPassword(),"pass2");
    	assertEquals(userDTO.getFirstName(),"malik");
    	assertEquals(userDTO.getLastName(),"sadaoui1");
    	assertFalse(userDTO.isAdmin());
    }
    
    /**
     * 	Test la méthode du services web permettant de mettre à jour
     * 		les données d'un utilisateur
     * 	Version sans echec: modif admin
     */
    @Test
    public void updateUserTestSuccess2()
    {
    	// verifie que le jeu de tests est correcte
    	UserDTO userDTO = users.get(0);
    	assertNotNull(userDTO);
    	assertEquals(userDTO.getLogin(),"fcardenas");
    	assertEquals(userDTO.getPassword(),"password");
    	assertEquals(userDTO.getFirstName(),"francois");
    	assertEquals(userDTO.getLastName(),"cardenas");
    	assertFalse(userDTO.isAdmin());
    	
    	try {
    		userDTO.setAdmin(true);
	    	userDTO = subscriberService.updateUser(userDTO);
	    	assertNotNull(userDTO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    	
    	userDTO = subscriberService.authentificate("fcardenas", "password");
    	assertNotNull(userDTO);
    	assertEquals(userDTO.getLogin(),"fcardenas");
    	assertEquals(userDTO.getPassword(),"password");
    	assertEquals(userDTO.getFirstName(),"francois");
    	assertEquals(userDTO.getLastName(),"cardenas");
    	assertTrue(userDTO.isAdmin());
    }
    
    /**
     * 	Test la méthode du services web permettant de mettre à jour
     * 		les données d'un utilisateur
     * 	Version avec echec: modif avec des données fausses 
     * 		-> identifiant non correcte
     */
    @Test
    public void updateUserTestFail1()
    {
    	// injection de données fausses
    	UserDTO userDTO = new UserDTO();
    	userDTO.setID(3345543);
    	userDTO.setLogin("fcardenas");
    	userDTO.setPassword("password");
    	userDTO.setFirstName("francois");
    	userDTO.setLastName("cardenas");
    	
    	try {
	    	userDTO = subscriberService.updateUser(userDTO);
	    	fail("identifiant non correct !");
		} catch (EntityNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    /**
     * 	Test la méthode du services web permettant de mettre à jour
     * 		les données d'un utilisateur
     * 	Version avec echec: modif avec des données fausses 
     * 		-> identifiant non correcte
     */
    @Test
    public void updateUserTestFail2()
    {
    	// injection de données fausses
    	UserDTO userDTO = new UserDTO();
    	userDTO.setID(admin.getID());
    	userDTO.setLogin("yduduci");
    	userDTO.setFirstName("yann2");
    	userDTO.setLastName("dudicourt22");
    	
    	try {
	    	userDTO = subscriberService.updateUser(userDTO);
	    	fail("login non correct !");
		} catch (LoginInvalidException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    /**
     * 	Test la méthode du services web permettant d'ajouter une location
     * 	Version sans echec
     */
    @Test
    public void rentBookTestSuccess()
    {
    	RentBookDTO rentBookDTO = null;
    	try {
    		// recupere le book
			BookDTO bookDTO = serviceBean.findBookByISBN("ZOL569EMI");
			assertNotNull(bookDTO);
			assertEquals(bookDTO.getIsbn(),"ZOL569EMI");
			assertEquals(bookDTO.getName(),"Le rouge et le noir");
			assertEquals(bookDTO.getGenre(),"ROMAN");
			
			// recupere l'utilisateur
			UserDTO userDTO = users.get(2);
	    	assertEquals(userDTO.getLogin(),"pmallet");
	    	assertEquals(userDTO.getPassword(),"s3cr3t");
	    	assertEquals(userDTO.getFirstName(),"paul");
	    	assertEquals(userDTO.getLastName(),"mallet");
			
	    	// ajout de la location
			rentBookDTO = subscriberService.rentBook(userDTO, bookDTO.getIsbn());
			assertNotNull(rentBookDTO);
			rentBooks.add(rentBookDTO);
			
		} catch (Exception e) {
			fail(e.getMessage());
		} 
    	// verification de l'ajout de la location
    	assertTrue(rentBookDTO.getID()>0);
    	assertEquals(rentBookDTO.getIsbn(), "ZOL569EMI");
    	assertEquals(rentBookDTO.getLogin(), "pmallet");
    	assertNotNull(rentBookDTO.getStartDate());
    	assertNull(rentBookDTO.getEndDate());
    }
    
    /**
     * 	Test la méthode du services web permettant d'ajouter une location
     * 	Version avec echec: isbn non valide -> le livre n'existe pas dans le stock
     */
    @Test
    public void rentBookTestFail1()
    {
    	try {
			// recupere l'utilisateur
			UserDTO userDTO = users.get(2);
			
	    	// ajout de la location
			subscriberService.rentBook(userDTO, "ZOL56LDLMI");
			fail("Attention!! Ajout d'une location avec un mauvais isbn");
			
    	} catch (BookNotFoundExceptionException e) {
    		assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
    }
    
    /**
     * 	Test la méthode du services web permettant d'ajouter une location
     * 	Version avec echec: identifiant de l'utilisateur incorrecte
     */
    @Test
    public void rentBookTestFail2()
    {
    	try {
			// recupere l'utilisateur
			UserDTO userDTO = new UserDTO();
			userDTO.setID(44544);
			userDTO.setLogin("pmallet");
			userDTO.setPassword("ppas");
			userDTO.setFirstName("paul");
			userDTO.setLastName("mallet");
			
	    	// ajout de la location
			subscriberService.rentBook(userDTO, "ZOL569EMI");
			fail("Attention!! Ajout d'une location avec un mauvais identifiant de l'utilisateur");
			
    	} catch (EntityNotFoundException e) {
    		assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
    }
    
    /**
     * 	Test la méthode du services web permettant d'ajouter une location
     * 	Version avec echec: mauvais login
     */
    @Test
    public void rentBookTestFail3()
    {
    	try {
			// recupere l'utilisateur
			UserDTO userDTO = new UserDTO();
			userDTO.setID(users.get(0).getID());
			userDTO.setLogin("mauvaislogin");
			
	    	// ajout de la location
			subscriberService.rentBook(userDTO, "ZOL569EMI");
			fail("Attention!! Ajout d'une location avec un mauvais identifiant de l'utilisateur");
			
    	} catch (LoginInvalidException e) {
    		assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
    }
    
    /**
     * 	Test la méthode du services web permettant d'ajouter une location
     * 	Version avec echec: exemplaire non disponible
     */
    @Test
    public void rentBookTestFail4()
    {
    	// on verifie les preconditions du test
    	BookDTO bookDTO = books.get(1);
    	assertEquals(bookDTO.getIsbn(), "ZOL570EMI");
    	assertEquals(serviceBean.getExemplaryNumber("ZOL570EMI"),2);
    	assertTrue(users.size()>2);
		
    	try {   	
	    	for (UserDTO userDTO : users)
	    	{
				RentBookDTO rentBookDTO = subscriberService.rentBook(userDTO, "ZOL570EMI");
				rentBooks.add(rentBookDTO);
	    	}
	    	fail("Attention!! Location avec des exemplaires non disponible");
    	}
    	catch (NotEnoughtExemplaryException e) {
			assertTrue(true);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    /**
     * 	Test la méthode permettant de retourne les locations
     * 		d'un utilisateur
     */
    @Test
    public void getAllRentsTest()
    {
    	assertTrue(users.size()>2);
    	UserDTO userDTO = users.get(3);
    	assertEquals(userDTO.getLogin(),"shor");

		Set<String> isbnBooks = new HashSet<String>();
		isbnBooks.add("ZOL571EMI");
		isbnBooks.add("ZOL572EMI");
		isbnBooks.add("ZOL573EMI");
    	try {
    		
	    	for (String isbn : isbnBooks)
	    	{
				RentBookDTO rentBookDTO = subscriberService.rentBook(userDTO, isbn);
				rentBooks.add(rentBookDTO);
	    	}
	    	
	    	List<RentBookDTO> results = subscriberService.getAllRents(userDTO);
	    	for (RentBookDTO rentBookDTO : results)
	    	{
	    		assertTrue(isbnBooks.contains(rentBookDTO.getIsbn()));
	    		assertEquals(rentBookDTO.getLogin(),userDTO.getLogin());
	    	}
    	} 
    	catch (Exception e)
    	{
    		fail(e.getMessage());
    	}
    }
    
    /**
     * 	Test la méthode permettant de retourne les locations
     * 		d'un utilisateur
     * 	Version avec echec: mauvais correspondant identifiant/login
     */
    @Test
    public void getAllRentsTestFail()
    {
    	assertTrue(users.size()>2);
    	UserDTO userDTO = new UserDTO();
    	userDTO.setID(users.get(3).getID());
    	userDTO.setLogin("jpmaldudi");

    	try {
    		subscriberService.getAllRents(userDTO);
    		fail("Attention!! correspondance login/identifiant incorrect pour GetAllRents");
    	} 
    	catch (LoginInvalidException e) 
    	{
    		assertTrue(true);
    	}
    	catch (Exception e)
    	{
    		fail(e.getMessage());
    	}
    }
}
