package com.insta.fjee.library.core.service;

import javax.jws.WebService;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.stock.service.BookDTO;

/**
 * 	Services web de l'application accessible par les abonnés
 * 
 * @author julien
 *
 */
@WebService
public interface ISubscriberService 
{
	/**
	 * 	Authentificate a user
	 * 
	 * @param login - login of the user
	 * @param password - password of the user
	 * @return information user, null if fail
	 */
	public UserDTO authentificate(String login, String password);
	
	public boolean rentBook(BookDTO bookDTO);
	public boolean returnBook(BookDTO bookDTO);
}
