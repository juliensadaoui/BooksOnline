package com.insta.fjee.library.core.dao;

import java.util.List;

import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.model.RentBook;

public interface IRentBookDAO {
	
	/**
	 * 	Create a new rent book
	 * 
	 * @param rentBook - new rent book
	 * @return
	 */
	RentBook save(RentBook rentBook);
	
	/**
	 * 	Update a rent book
	 * 
	 * @param rentBook
	 * @return 
	 */
	RentBook update(RentBook rentBook);
	
	/**	
	 * 	Delete a rent book
	 * 
	 * @param rentBook - rentbook to delete
	 */
	void delete(RentBook rentBook);
	
	/**
	 * 	Return the rental number of a book
	 * 
	 * @param isbn - isbn book identifier
	 * @return
	 */
	public long countRentBooks(String isbn);
	
	/**
	 * 	Read a rent 
	 * 
	 * @param id - identifier of the location
	 * @return rent
	 */
	public RentBook find(Integer id) ;
	
	/**
	 * 	Read a rent with exception if not exist
	 * 
	 * @param id - identifier of the location
	 * @return rent
	 * @throws EntityNotFoundException
	 */
	RentBook findOrFail(Integer id) throws EntityNotFoundException;
	
	/**
	 * 	Retourne toutes les locations d'un utilisateur
	 * 
	 * @param login - login de l'utilisateur
	 * @return 
	 */
	List<RentBook> getAllRents(String login);
}
