package com.insta.fjee.library.core.bo;

import com.insta.fjee.library.stock.service.BookDTO;

public interface IRentBookBO {
	
	public BookDTO rentBook(BookDTO bookDTO);
	public BookDTO returnBook(BookDTO bookDTO);

}
