package com.insta.fjee.library.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.dto.AuthorDto;
import com.insta.fjee.library.junit.Util;
import com.insta.fjee.library.util.Conversion;


public class TestLibraryBean {

//	private EntityManager _em;
//	private AuthorEAO _eao;
//	private Conversion _conv;
//
//	private LibraryBean serviceBean;
//
//	@Before
//	public void setUp() throws Exception
//	{
//		_em = Util.getEntityManager();
//		_eao = new LibraryEAO(_em);
//		_conv = new Conversion(_eao);
//		serviceBean = new LibraryBean(_eao, _conv);
//	}
//	
//	@Test
//	public void searchAuthorByFirstNameTest()
//	{
//		List<AuthorDto> authors = serviceBean.searchAuthorByFirstName("Jea");
//		assertEquals(authors.size(), 1);
//		for (AuthorDto a : authors) {
//			System.out.println(a.getBooks().get(0));
//		}
//	}
}
