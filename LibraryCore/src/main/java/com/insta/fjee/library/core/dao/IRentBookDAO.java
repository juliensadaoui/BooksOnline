package com.insta.fjee.library.core.dao;

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
}
