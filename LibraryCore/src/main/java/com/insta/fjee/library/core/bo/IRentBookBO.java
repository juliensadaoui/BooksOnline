package com.insta.fjee.library.core.bo;

import java.util.List;

import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.exception.NotEnoughtExemplaryException;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;

public interface IRentBookBO {
	
	/**
	 * 	Ajoute une nouvelle location
	 * 
	 * @param userDTO - utilisateur réalisant la location
	 * @param isbn - isbn du livre à louer
	 * @return
	 * @throws EntityNotFoundException
	 * @throws NotEnoughtExemplaryException
	 * @throws BookNotFoundException_Exception
	 */
	public RentBookDTO rentBook(UserDTO userDTO, String isbn) throws EntityNotFoundException, NotEnoughtExemplaryException, BookNotFoundException_Exception, LoginInvalidException;
	
	/**
	 * 	Rend un livre loué
	 * 
	 * @param rentBookDTO - location 
	 * @throws EntityNotFoundException
	 */
	public RentBookDTO returnBook(RentBookDTO rentBookDTO) throws EntityNotFoundException;
	
	/**
	 * 	Supprime une location
	 * 
	 * @param rentBookDTO - location à supprimer
	 * @throws EntityNotFoundException
	 */
	public void deleteRentBook(RentBookDTO rentBookDTO) throws EntityNotFoundException, LoginInvalidException;
	
	/**
	 * 	Retourne toutes les locations d'un utilisateur
	 * 
	 * @param userDTO - utilisateur
	 * @return locations d'un utilisateur
	 * @throws EntityNotFoundException
	 */
	public List<RentBookDTO> getAllRents(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException;
}
