package com.insta.fjee.library.eao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.junit.Util;

public class TestAuthorEAO {

	private static EntityManager entityManager;

	private static AuthorEAO authorEAO;
	
	private Author author;

    @BeforeClass
    public static void setUpClass() throws Exception {
        entityManager = Util.getEntityManager();
        authorEAO = new AuthorEAO(entityManager);	
    }
    
    @Before
    public void setUp() throws Exception {
        
		// charge la donnée de base
		author = new Author();
		author.setFirstName("Gustave");
		author.setLastName("Flaubert");
		
		entityManager.getTransaction().begin();
		authorEAO.saveOrUpdate(author);
		entityManager.getTransaction().commit();
    }
    
    @After
	public void tearDown() 
	{
		entityManager.getTransaction().begin();
		authorEAO.delete(author);
		entityManager.getTransaction().commit();
	}

    /**
     * 	Test la méthode de l'EAO (Entity Access Object) permettant
     * 	d'ajouter un auteur dans la base de données
     * 
     */
 	@Test	
 	public void createAuthorTestSuccess() 
  	{
		Author author = new Author();
		author.setFirstName("Jules");
		author.setLastName("Verne");
		entityManager.getTransaction().begin();
		authorEAO.saveOrUpdate(author);
		entityManager.getTransaction().commit();
		
		assertNotNull(authorEAO.findAuthorByFirstName("ule"));
		
		entityManager.getTransaction().begin();
		authorEAO.delete(author);
		entityManager.getTransaction().commit();
	} 
 	
    /**
     * 	Test la méthode de l'EAO (Entity Access Object) permettant
     * 	d'ajouter un auteur dans la base de données
     * 	
     * 	La création de l'auteur échoue car l'auteur existe déjà
     * 
     */
    @Test
    public void createAuthorTestFail() 
    {
//    	Author a = new Author();
//    	a.setFirstName("Jules");
//    	a.setLastName("Verne");
//		entityManager.getTransaction().begin();
//    	authorEAO.saveOrUpdate(a);
//		entityManager.getTransaction().commit();
//  	
//    	assertNotNull(authorEAO.findAuthorByFirstName("ule"));
    	
    	//FIXME appliquer une connexion pour la contrainte d'intégrité
    }   

 
  
    @Test 
    public void searchAuthorByFirstNameTest()
    {
    	List<Author> authors = authorEAO.findAuthorByFirstName("Jea");
    	assertEquals(authors.size(), 1);
    }
    
    @Test 
    public void searchAuthorByLastNameTest()
    {
    	List<Author> authors = authorEAO.findAuthorByLastName("fsqdf");
    	assertEquals(authors.size(), 0);
    }
    
    @Test 
    public void searchAuthorByBookNameTest()
    {
    	List<Author> authors = authorEAO.findAuthorByBookName("Test");
    	assertEquals(authors.size(), 0);
    }
}
