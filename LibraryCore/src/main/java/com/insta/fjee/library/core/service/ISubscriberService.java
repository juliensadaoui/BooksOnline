package com.insta.fjee.library.core.service;

import java.util.List;

import javax.jws.WebService;

import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginAlreadyExistException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.exception.NotEnoughtExemplaryException;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;

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
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginAlreadyExistException, LoginInvalidException;
	
	/**
	 * 	Ajoute d'une nouvelle location d'un livre
	 * 	
	 * @param userDTO
	 * @param isbn - isbn du livre
	 * @return
	 * @throws BookNotFoundException_Exception
	 * @throws NotEnoughtExemplaryException
	 * @throws EntityNotFoundException
	 */
	public RentBookDTO rentBook(UserDTO userDTO, String isbn)
		throws BookNotFoundException_Exception, NotEnoughtExemplaryException, EntityNotFoundException, LoginInvalidException;

	/**
	 * 	Retourne toutes les locations d'un utilisateur
	 * 
	 * @param userDTO - informations sur l'utilisateur
	 * @return
	 * @throws EntityNotFoundException
	 */
    public List<RentBookDTO> getAllRents(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException;

	/**
	 * 	Rend un livre loué
	 * 
	 * @param rentBookDTO - location 
	 * @throws EntityNotFoundException
	 */
	public RentBookDTO returnBook(RentBookDTO rentBookDTO) throws EntityNotFoundException;

}
