package com.insta.fjee.library.eao;

import java.util.List;

import javax.ejb.Remote;

import com.insta.fjee.library.entity.Author;

@Remote
public interface ILibraryEAO {
	
	/** 
	 * 	Compte le nombre de livres dans la zone de stockage
	 * 
	 * @return - nombre de livres
	 */
	public long countBooks();
	
	/**
	 * 	
	 * 
	 * @param firstName
	 * @return
	 */
	public List<Author> findAuthorByFirstName(String firstName);
}
