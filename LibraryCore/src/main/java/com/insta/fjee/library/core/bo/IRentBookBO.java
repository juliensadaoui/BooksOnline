package com.insta.fjee.library.core.bo;

import com.insta.fjee.library.stock.service.BookDTO;

public interface IRentBookBO {
	
	public boolean rentBook(BookDTO bookDTO);
	public boolean returnBook(BookDTO bookDTO);

}
