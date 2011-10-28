package com.insta.fjee.library.core.service;

import javax.jws.WebService;

import com.insta.fjee.library.stock.service.BookDTO;

//@WebService
public interface ISubscriberService 
{
	public boolean rentBook(BookDTO bookDTO);
	public boolean returnBook(BookDTO bookDTO);
}
