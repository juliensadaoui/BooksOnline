package com.insta.fjee.library.core.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


import com.insta.fjee.library.stock.service.LibraryBean;

public class CustomWSSupport 
{
	public static LibraryBean getLibraryBean()
	{
		// call WS
		URL url;
		try {
			url = new URL("http://localhost:8080/LibraryBeanService/LibraryBean?wsdl");
			QName qname = new QName("http://service.stock.library.fjee.insta.com/", "LibraryBeanService");

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
}
