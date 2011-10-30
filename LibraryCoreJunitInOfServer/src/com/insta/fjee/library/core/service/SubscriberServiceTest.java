package com.insta.fjee.library.core.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
	// jeu d'auteurs utilisés pour les tests unitaires
	private static List<AuthorDTO> authors;
	
	// jeu de livres utilisés pour les tests unitaires
	private static List<BookDTO> books;

	@BeforeClass
	public static void setUp() throws Exception
	{
		serviceBean = (new LibraryBeanService()).getLibraryBeanPort();
		userService = (new UserServiceService()).getUserServicePort();
		subscriberService = (new SubscriberServiceService()).getSubscriberServicePort();
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
				"ZOL571EMI","Les misérables","ROMAN"
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
				books.add(serviceBean.addBook(bookDTO, 4));
			} catch (EntityNotFoundException_Exception e) {
				fail(e.getMessage());
			}
		}
    		
		/*
		 * 	Ajout d'un utilisateur
		 */
    	try {
    		UserDTO userDTO = new UserDTO();
	    	userDTO.setLogin("ydudicourt");
	    	userDTO.setFirstName("yann");
	    	userDTO.setLastName("dudicourt");
	    	userDTO.setPassword("pass");
	    	userDTO = userService.createUser(userDTO);
		} catch (Exception e) {
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
    }
    
    @Test
    public void updateUserTest()
    {
    	try {
    		UserDTO userDTO = new UserDTO();
	    	userDTO.setLogin("ydudicourt");
	    	userDTO.setFirstName("yann");
	    	userDTO.setLastName("dudicourt");
	    	userDTO.setPassword("pass");
	    	assertNotNull(subscriberService.authentificate("ydudicourt", "pass"));
			subscriberService.updateUser(userDTO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
    }
}
