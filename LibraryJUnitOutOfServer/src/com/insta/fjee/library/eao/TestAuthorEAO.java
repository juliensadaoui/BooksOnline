package com.insta.fjee.library.eao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.junit.Util;

public class TestAuthorEAO {

	private EntityManager _em;

	private AuthorEAO authoreao;
	
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        authoreao = new AuthorEAO(_em);
    }
	
//  @Test
//  public void createAuthorTest() 
//  {
//  	Author a = new Author();
//  	a.setFirstName("Jules");
//  	a.setLastName("Verne");
//  	authoreao.persist(a);
//  	
//  	Assert.assertNotNull(eao.findAuthorByFirstName("ule"));
//  }   
    
    @Test 
    public void searchAuthorByFirstNameTest()
    {
    	List<Author> authors = authoreao.findAuthorByFirstName("Jea");
    	assertEquals(authors.size(), 1);
    }
    
    @Test 
    public void searchAuthorByLastNameTest()
    {
    	List<Author> authors = authoreao.findAuthorByLastName("fsqdf");
    	assertEquals(authors.size(), 0);
    }
    
    @Test 
    public void searchAuthorByBookNameTest()
    {
    	List<Author> authors = authoreao.findAuthorByBookName("Test");
    	assertEquals(authors.size(), 0);
    }
}
