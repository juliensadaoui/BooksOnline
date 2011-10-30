package com.insta.fjee.library.core.service;

import javax.jws.WebService;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginAlreadyExistException;

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
	
	/**
	 * 	Update a user 
	 * 
	 * @param userDTO - user to update
	 * @return	user updated
	 * @throws SessionException
	 * @throws EntityNotFoundException
	 * @throws LoginAlreadyExistException
	 */
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginAlreadyExistException;
	
//	public boolean rentBook(BookDTO bookDTO);
//	public boolean returnBook(BookDTO bookDTO);
}
