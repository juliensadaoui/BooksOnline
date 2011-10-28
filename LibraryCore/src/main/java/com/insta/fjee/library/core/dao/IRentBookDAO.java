package com.insta.fjee.library.core.dao;

import com.insta.fjee.library.core.model.RentBook;

public interface IRentBookDAO {
	
	public RentBook rentBook(RentBook rentBook);
	public RentBook returnBook(RentBook rentBook);
	
}
