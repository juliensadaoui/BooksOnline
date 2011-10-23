package com.insta.fjee.library.core.bo.impl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.service.BookDTO;
import com.insta.fjee.library.service.LibraryBean;

public class UserBOImpl implements IUserBO
{
	public LibraryBean getService()
	{
		// call WS
		URL url;
		try {
			url = new URL("http://localhost:8080/LibraryBeanService/LibraryBean?wsdl");
			QName qname = new QName("http://service.library.fjee.insta.com/", "LibraryBeanService");

			// Création d'une fabrique pour le WS
			Service service = Service.create(url, qname);

			// Récupération Proxy pour accéder aux méthodes
			return service.getPort(LibraryBean.class);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getName(String isbn) {
		BookDTO book;
		book = getService().findBookByISBN(isbn);
		return book.getName();	
	}

}
